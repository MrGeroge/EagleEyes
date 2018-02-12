package com.ckey.shijing;

import com.ckey.shijing.util.Md5Utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {
	public static void main(String args[]) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(200);
		config.setMaxTotal(1024);
		config.setMaxWaitMillis(1000);
		config.setTestOnBorrow(true);
		config.setTestOnReturn(true);
		JedisPool pool = new JedisPool(config, "127.0.0.1", 6379);
		Jedis jedis = pool.getResource();
		String token = Md5Utils.EncodeToken(3);
		jedis.set(String.valueOf(3),token);
		pool.returnResource(jedis);
		System.out.println(jedis.get(String.valueOf(3)));
	}
}
