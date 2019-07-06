package com.babybloom.web.algorithm;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

public class UserIdTableShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        String logicTableName = preciseShardingValue.getLogicTableName();
        int value = Integer.valueOf(preciseShardingValue.getValue());
        return logicTableName + "_" + (value % 5);
    }
}
