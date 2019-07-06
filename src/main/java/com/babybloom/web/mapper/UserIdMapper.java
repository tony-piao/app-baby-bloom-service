package com.babybloom.web.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserIdMapper {
    void insertBatch(List<String> list);

    String getId(@Param("tableSuffix") Integer tableSuffix, @Param("offset") Integer offset);

    void del(String guid);
}
