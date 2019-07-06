package com.babybloom.web.mapper;

import com.babybloom.web.model.po.UserInfo;
import com.babybloom.web.model.po.UserInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper {
    long countByExample(UserInfoExample example);

    int deleteByExample(UserInfoExample example);

    int deleteByPrimaryKey(Long guid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectOneByExample(UserInfoExample example);

    List<UserInfo> selectByExample(UserInfoExample example);

    UserInfo selectByPrimaryKey(Long guid);

    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    int batchInsert(@Param("list") List<UserInfo> list);

    int batchInsertSelective(@Param("list") List<UserInfo> list, @Param("selective") UserInfo.Column... selective);
}