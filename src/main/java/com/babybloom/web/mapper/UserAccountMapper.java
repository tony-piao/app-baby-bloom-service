package com.babybloom.web.mapper;

import com.babybloom.web.model.po.UserAccount;
import com.babybloom.web.model.po.UserAccountExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAccountMapper {
    long countByExample(UserAccountExample example);

    int deleteByExample(UserAccountExample example);

    int deleteByPrimaryKey(Long guid);

    int insert(UserAccount record);

    int insertSelective(UserAccount record);

    UserAccount selectOneByExample(UserAccountExample example);

    List<UserAccount> selectByExample(UserAccountExample example);

    UserAccount selectByPrimaryKey(Long guid);

    int updateByExampleSelective(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    int updateByExample(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    int updateByPrimaryKeySelective(UserAccount record);

    int updateByPrimaryKey(UserAccount record);

    int batchInsert(@Param("list") List<UserAccount> list);

    int batchInsertSelective(@Param("list") List<UserAccount> list, @Param("selective") UserAccount.Column... selective);
}