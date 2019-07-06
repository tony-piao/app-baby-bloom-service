package com.babybloom.web.service;

import com.babybloom.web.annotation.ApplicationTransaction;
import com.babybloom.web.annotation.GrabAbnormal;
import com.babybloom.web.constant.Constant;
import com.babybloom.web.manager.RedisManager;
import com.babybloom.web.mapper.UserAccountMapper;
import com.babybloom.web.mapper.UserInfoMapper;
import com.babybloom.web.model.bo.WeixinDecryptionBo;
import com.babybloom.web.model.bo.WeixinOpenIdSessionKeyBo;
import com.babybloom.web.model.dto.UserDetailDto;
import com.babybloom.web.model.dto.WeixinLoginUpDto;
import com.babybloom.web.model.po.UserAccount;
import com.babybloom.web.model.po.UserAccountExample;
import com.babybloom.web.model.po.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService extends ServiceBase {

    @Autowired
    private WeixinService weixinService;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private IdService idservice;
    @Autowired
    private RedisManager redisManager;


    @GrabAbnormal
    @ApplicationTransaction
    public UserDetailDto weixinLogin(WeixinLoginUpDto weixinLoginUpDto) {
        checkParamNotNull(weixinLoginUpDto, "upEntity");
        String jsCode = weixinLoginUpDto.getJsCode();
        String encryptedData = weixinLoginUpDto.getEncryptedData();
        String iv = weixinLoginUpDto.getIv();
        String rawData = weixinLoginUpDto.getRawData();
        String signature = weixinLoginUpDto.getSignature();

        checkParamNotNull(jsCode, "jsCode");
        checkParamNotNull(encryptedData, "encryptedData");
        checkParamNotNull(iv, "iv");
        checkParamNotNull(rawData, "rawData");
        checkParamNotNull(signature, "signature");

        // step1 根据jsCode 获取openId
        WeixinOpenIdSessionKeyBo openIdBo = weixinService.getSessionKeyAndOpenId(jsCode);
        String openid = openIdBo.getOpenid();

        // step2 查询用户是否存在 目前以openId作为用户名 不存在就自动注册
        UserDetailDto userDetail = getUserByOpenId(openid);
        if (userDetail == null) {
            // 解密出unionId
            WeixinDecryptionBo weixinDecryptionBo = weixinService.decryptionUserInfo(encryptedData, openIdBo.getSession_key(), iv);

            UserAccount userAccount = new UserAccount();
            userAccount.setOpenId(openid);
            userAccount.setUnionId(weixinDecryptionBo.getUnionId());

            UserInfo userInfo = new UserInfo();
            userInfo.setNickName(weixinDecryptionBo.getNickName());
            userInfo.setImgUrl(weixinDecryptionBo.getAvatarUrl());
            userInfo.setCountry(weixinDecryptionBo.getCountry());
            userInfo.setProvince(weixinDecryptionBo.getProvince());
            userInfo.setCity(weixinDecryptionBo.getCity());
            userInfo.setGender(weixinDecryptionBo.getGender());

            Long guid = registerAccount(userAccount, userInfo);
            userDetail = getUserByGuid(guid);
        }

        // step3 生成token
        String token = redisManager.saveLoginToken(userDetail.getGuid());
        userDetail.setToken(token);

        return userDetail;
    }

    /**
     * 注册账号
     *
     * @param userAccount
     * @param userInfo
     */
    private Long registerAccount(UserAccount userAccount, UserInfo userInfo) {
        Long guid = getSnowflakeNextId();
        Date date = new Date();
        String userNumber = idservice.getUserNumber();
        userAccount.setGuid(guid);
        userAccount.setLifeFlag(Constant.LIFE_TRUE);
        userAccount.setUserNumber(userNumber);
        userAccount.setUpdateTime(date);
        userAccountMapper.insertSelective(userAccount);

        userInfo.setGuid(guid);
        userInfo.setRegistrationTime(date);
        userInfo.setUpdateTime(date);
        userInfoMapper.insertSelective(userInfo);
        return guid;
    }


    /**
     * 根据openId查询账号
     *
     * @param guid
     * @return
     */
    @GrabAbnormal
    public UserDetailDto getUserByGuid(Long guid) {
        checkParamNotNull(guid, "guid");
        UserAccount userAccount = userAccountMapper.selectByPrimaryKey(guid);
        if (userAccount == null || (Constant.LIFE_TRUE != (byte) userAccount.getLifeFlag())) {
            return null;
        }
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(guid);
        return buildUserDetail(userAccount, userInfo);
    }

    /**
     * 根据openId查询账号
     *
     * @param openId
     * @return
     */
    @GrabAbnormal
    public UserDetailDto getUserByOpenId(String openId) {
        checkParamNotNull(openId, "openId");
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andOpenIdEqualTo(openId).andLifeFlagEqualTo(Constant.LIFE_TRUE);
        UserAccount userAccount = userAccountMapper.selectOneByExample(example);
        if (userAccount == null) {
            return null;
        }
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userAccount.getGuid());
        return buildUserDetail(userAccount, userInfo);
    }


    /**
     * 根据userNumber查询账号
     *
     * @param userNumber
     * @return
     */
    @GrabAbnormal
    public UserDetailDto getUserByUserNumber(String userNumber) {
        checkParamNotNull(userNumber, "userNumber");
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andUserNumberEqualTo(userNumber).andLifeFlagEqualTo(Constant.LIFE_TRUE);
        UserAccount userAccount = userAccountMapper.selectOneByExample(example);
        if (userAccount == null) {
            return null;
        }
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userAccount.getGuid());
        return buildUserDetail(userAccount, userInfo);
    }

    /**
     * 根据userName查询账号
     *
     * @param userName
     * @return
     */
    @GrabAbnormal
    public UserDetailDto getUserByUserName(String userName) {
        checkParamNotNull(userName, "userName");
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andUserNameEqualTo(userName).andLifeFlagEqualTo(Constant.LIFE_TRUE);
        UserAccount userAccount = userAccountMapper.selectOneByExample(example);
        if (userAccount == null) {
            return null;
        }
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userAccount.getGuid());
        return buildUserDetail(userAccount, userInfo);
    }

    /**
     * 根据userAccount和userInfo构建userDetail
     *
     * @param userAccount
     * @param userInfo
     * @return
     */
    private UserDetailDto buildUserDetail(UserAccount userAccount, UserInfo userInfo) {
        UserDetailDto userDetailDto = new UserDetailDto();
        userDetailDto.setGuid(userAccount.getGuid());
        userDetailDto.setUserNumber(userAccount.getUserNumber());
        userDetailDto.setNickName(userInfo.getNickName());
        userDetailDto.setImgUrl(userInfo.getImgUrl());
        userDetailDto.setGender(userInfo.getGender());
        userDetailDto.setCity(userInfo.getCity());
        userDetailDto.setProvince(userInfo.getProvince());
        userDetailDto.setCountry(userInfo.getCountry());
        userDetailDto.setRegistrationTime(userInfo.getRegistrationTime());
        return userDetailDto;
    }
}
