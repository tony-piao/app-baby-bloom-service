package com.babybloom.web.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.babybloom.web.algorithm.UserIdTableShardingAlgorithm;
import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import lombok.Data;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Data
@Configuration
@ConfigurationProperties(prefix = "baby.datasource.druid")
@MapperScan(basePackages = GaiaDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "babySqlSessionFactory")
public class GaiaDataSourceConfig {
    /**
     * dao层的包路径
     */
    static final String PACKAGE = "com.babybloom.web.mapper";

    /**
     * mapper文件的相对路径
     */
    private static final String MAPPER_LOCATION = "classpath:mybatis/mappers/*.xml";

    private String filters;
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private int initialSize;
    private int minIdle;
    private int maxActive;
    private long maxWait;
    private long timeBetweenEvictionRunsMillis;
    private long minEvictableIdleTimeMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean poolPreparedStatements;
    private int maxPoolPreparedStatementPerConnectionSize;


    @Bean(name = "shardingDataSource")
    public DataSource getShardingDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getUserTableRuleConfiguration());
        //如果有多个表，可以用逗号“,”分隔 ，比如user_info,t_order
        shardingRuleConfig.getBindingTableGroups().add("t_user_id");

        //设置分片策略，自定义算法来实现分片规则
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("guid", new UserIdTableShardingAlgorithm()));

        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, new ConcurrentHashMap(), new Properties());
    }

    /**
     * 配置表规则
     *
     * @return
     */
    @Bean
    public TableRuleConfiguration getUserTableRuleConfiguration() {
        TableRuleConfiguration userIdTableRuleConfig = new TableRuleConfiguration();
        //配置表名
        userIdTableRuleConfig.setLogicTable("t_user_id");
        //配置真实的数据节点，即数据库中真实存在的节点，由数据源名 + 表名组成
        userIdTableRuleConfig.setActualDataNodes("gaia.t_user_id_${0..4}");//user_${0..1}分库，user_info_${0..1}分表
        return userIdTableRuleConfig;
    }

    /**
     * 创建sharding jdbc数据源
     *
     * @return
     */
    private Map<String, DataSource> createDataSourceMap() throws SQLException {
        Map<String, DataSource> result = new HashMap<>();
        result.put("gaia", gaiaDataSource());
        return result;
    }

    // 主数据源使用@Primary注解进行标识
    @Primary
    @Bean(name = "gaiaDataSource")
    public DataSource gaiaDataSource() throws SQLException {
        DruidDataSource druid = new DruidDataSource();
        // 监控统计拦截的filters
        druid.setFilters(filters);

        // 配置基本属性
        druid.setDriverClassName(driverClassName);
        druid.setUsername(username);
        druid.setPassword(password);
        druid.setUrl(url);

        //初始化时建立物理连接的个数
        druid.setInitialSize(initialSize);
        //最大连接池数量
        druid.setMaxActive(maxActive);
        //最小连接池数量
        druid.setMinIdle(minIdle);
        //获取连接时最大等待时间，单位毫秒。
        druid.setMaxWait(maxWait);
        //间隔多久进行一次检测，检测需要关闭的空闲连接
        druid.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        //一个连接在池中最小生存的时间
        druid.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        //用来检测连接是否有效的sql
        druid.setValidationQuery(validationQuery);
        //建议配置为true，不影响性能，并且保证安全性。
        druid.setTestWhileIdle(testWhileIdle);
        //申请连接时执行validationQuery检测连接是否有效
        druid.setTestOnBorrow(testOnBorrow);
        druid.setTestOnReturn(testOnReturn);
        //是否缓存preparedStatement，也就是PSCache，oracle设为true，mysql设为false。分库分表较多推荐设置为false
        druid.setPoolPreparedStatements(poolPreparedStatements);
        // 打开PSCache时，指定每个连接上PSCache的大小
        druid.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

        return druid;
    }

    // 创建该数据源的事务管理
    @Primary
    @Bean(name = "gaiaTransactionManager")
    public DataSourceTransactionManager primaryTransactionManager() throws SQLException {
        return new DataSourceTransactionManager(getShardingDataSource());
    }

    // 创建Mybatis的连接会话工厂实例
    @Primary
    @Bean(name = "babySqlSessionFactory")
    public SqlSessionFactory babySqlSessionFactory(@Qualifier("shardingDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);  // 设置数据源bean
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATION));  // 设置mapper文件路径
        sessionFactory.setConfigLocation(new ClassPathResource("/mybatis/mybatis-config.xml"));
        return sessionFactory.getObject();
    }
}
