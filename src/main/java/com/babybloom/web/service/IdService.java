package com.babybloom.web.service;

import com.babybloom.web.annotation.ApplicationTransaction;
import com.babybloom.web.annotation.GrabAbnormal;
import com.babybloom.web.configuration.PrimaryKeyProperties;
import com.babybloom.web.mapper.UserIdMapper;
import com.babybloom.web.utility.LogUtility;
import com.babybloom.web.utility.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class IdService {

    private SnowflakeIdWorker snowflake;

    @Autowired
    private PrimaryKeyProperties primaryKeyProperties;

    @Autowired
    private UserIdMapper userIdMapper;

    @PostConstruct
    public void initSnowflake() {
        snowflake = new SnowflakeIdWorker(primaryKeyProperties.getDatacenter(), primaryKeyProperties.getWorker());
        LogUtility.businessLog().info("初始化id生成器成功，数据中心:{} 节点:{}", primaryKeyProperties.getDatacenter(), primaryKeyProperties.getWorker());
    }

    public long nextId() {
        return snowflake.nextId();
    }


    /**
     * 初始化user_id
     */
    @GrabAbnormal
    public void initUserId() {
        LogUtility.businessLog().info("开始初始化用户id");
        List<String> guid = new ArrayList<>(1000);
        List<String> selfId = new ArrayList<>();
        selfId.add("1111111");
        selfId.add("2222222");
        selfId.add("3333333");
        selfId.add("4444444");
        selfId.add("5555555");
        selfId.add("6666666");
        selfId.add("7777777");
        selfId.add("8888888");
        selfId.add("9999999");
        selfId.add("1234567");
        selfId.add("2345678");
        selfId.add("3456789");
        selfId.add("5201314");
        selfId.add("1314520");

        int count = 0;
        for (int i = 1001; i < 10000000; i++) {
            String id = String.format("%07d", i);
            if (selfId.contains(id)) {
                continue;
            }
            guid.add(id);
            ++count;
            if (count == 1000) {
                userIdMapper.insertBatch(guid);
                count = 0;
                guid.clear();
            }
        }
        if (count > 0 && guid.size() > 0) {
            userIdMapper.insertBatch(guid);
        }

        LogUtility.businessLog().info("初始化用户id成功");
    }

    /**
     * 获取userNumber
     * @return
     */
    @ApplicationTransaction
    public String getUserNumber() {
        Random rand = new Random();
        int i = rand.nextInt(5);
        int randNum = rand.nextInt(10000);
        String guid = userIdMapper.getId(i, randNum);
        userIdMapper.del(guid);
        return guid;
    }
}
