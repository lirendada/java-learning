package com.liren.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectionFactory {
    private static JedisPool jedisPool;

    static {
        // 配置连接池
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(8);
        config.setMaxIdle(8);
        config.setMinIdle(0);
        config.setMaxWaitMillis(1000);

        // 创建连接池对象，参数：连接池配置、服务端ip、服务端端口、超时时间、密码
        jedisPool = new JedisPool(config, "127.0.0.1", 6379, 1000, "root", "123123");
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
