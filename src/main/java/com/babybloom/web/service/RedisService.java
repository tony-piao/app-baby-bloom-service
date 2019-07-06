package com.babybloom.web.service;

import com.babybloom.web.configuration.RedisConfigProperties;
import com.babybloom.web.utility.LogUtility;
import com.babybloom.web.utility.StringUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;

/**
 * 单链接的redis连接池,可拓展为不同ip:port 多链接型连接池
 * 
 * @author zz
 *
 */
@Service
public class RedisService {

	@Autowired
	private RedisConfigProperties redisConfig;
	// jedis 连接池
	private JedisPool jedisPool;

	/**
	 * 初始化连接池,交给Spring管理
	 *
	 */
	@PostConstruct
	public void init() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(redisConfig.getMaxActive());
		jedisPoolConfig.setMaxIdle(redisConfig.getMaxIdle());
		jedisPoolConfig.setMaxWaitMillis(redisConfig.getMaxWait());
		jedisPoolConfig.setTestOnBorrow(false);
		jedisPoolConfig.setTestOnReturn(false);
		jedisPoolConfig.setTestOnCreate(true);
		// 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
		jedisPoolConfig.setBlockWhenExhausted(true);
		// 是否启用pool的jmx管理功能, 默认true
		jedisPoolConfig.setJmxEnabled(true);

		String ip = redisConfig.getHost();
		Integer port = redisConfig.getPort();
		try {
			// 连接默认超时时间2s
			if (StringUtility.isNullOrEmpty(redisConfig.getPassword()))
				jedisPool = new JedisPool(jedisPoolConfig, ip, port,redisConfig.getTimeout());
			else
				jedisPool = new JedisPool(jedisPoolConfig, ip, port, redisConfig.getTimeout(), redisConfig.getPassword());
			ping();
			LogUtility.businessLog().info("==================");
			LogUtility.businessLog().info("REDIS INIT SUCCESS");
			LogUtility.businessLog().info("==================");
		} catch (Exception e) {
			LogUtility.businessLog().error("Redis Init Error:" + e.getMessage());
		}
	}

	public String ping() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.ping();
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public void set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public void setex(String key, int seconds, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.setex(key, seconds, value);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public String get(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.get(key);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public void del(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(key);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public void expire(String key, int seconds) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.expire(key, seconds);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}
}
