package com.babybloom.web.service;

import com.babybloom.web.annotation.GrabAbnormal;
import com.babybloom.web.constant.BusinessErrorId;
import com.babybloom.web.constant.CommonErrorId;
import com.babybloom.web.constant.WeixinConstant;
import com.babybloom.web.exception.ApplicationException;
import com.babybloom.web.helper.HttpHelper;
import com.babybloom.web.model.bo.WeixinDecryptionBo;
import com.babybloom.web.model.bo.WeixinOpenIdSessionKeyBo;
import com.babybloom.web.utility.JsonUtility;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;

@Service
public class WeixinService implements WeixinConstant {

    /**
     * 网络请求微信获取openId和sessionKey
     *
     * @param jsCode
     * @return
     */
    @GrabAbnormal
    public WeixinOpenIdSessionKeyBo getSessionKeyAndOpenId(String jsCode) {
        StringBuilder openIdUrl = new StringBuilder();
        openIdUrl.append(OPEN_ID_URL);
        openIdUrl.append("appid=");
        openIdUrl.append(APP_ID);
        openIdUrl.append("&secret=");
        openIdUrl.append(SECRET);
        openIdUrl.append("&js_code=");
        openIdUrl.append(jsCode);
        String rsl = HttpHelper.httpGet(openIdUrl.toString());
        WeixinOpenIdSessionKeyBo downEntity = JsonUtility.json2Obj(rsl, WeixinOpenIdSessionKeyBo.class);
        if (downEntity.getErrcode() != 0) {
            throw new ApplicationException(CommonErrorId.ERROR_REMOTE_CALL, rsl);
        }
        return downEntity;
    }

    /**
     * 解密出unionId
     *
     * @param encryptedData
     * @param sessionKey
     * @param iv
     */
    @GrabAbnormal
    public WeixinDecryptionBo decryptionUserInfo(String encryptedData, String sessionKey, String iv) {
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JsonUtility.json2Obj(result, WeixinDecryptionBo.class);
            }
        } catch (Exception e) {
            throw new ApplicationException(BusinessErrorId.ERROR_WEIXIN_DECRYPT_FAIL, e.getMessage());
        }
        return null;
    }
}
