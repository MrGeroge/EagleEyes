package com.ckey.shijing.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import com.ckey.shijing.PersistenceConfig;
import com.ckey.shijing.util.Md5Utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service("JedisService")
public class JedisService {
	private JedisPool pool;

	public JedisService() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				PersistenceConfig.class);
		pool = applicationContext.getBean("JedisPool", JedisPool.class);
	}

	/**
	 * 验证是否为此用户
	 * 
	 * @param userId
	 *            用户id
	 * @param token
	 *            token信息
	 * @return true表示验证通过，false表示验证失败
	 */
	public boolean validate(int userId, String token) {
		Jedis jedis = pool.getResource();
		String serverToken = jedis.get(String.valueOf(userId));
		if (token.equals(serverToken)) {
			pool.returnResource(jedis);
			return true;
		}
		pool.returnResource(jedis);
		return false;
	}

	/**
	 * 授权
	 * 
	 * @param userId
	 *            用户id
	 * @return token信息
	 */
	public String authoriz(int userId) {
		String token = Md5Utils.EncodeToken(userId);
		Jedis jedis = pool.getResource();
		if (this.validate(userId, token))
			this.delete(userId);
		jedis.set(String.valueOf(userId), token);
		pool.returnResource(jedis);
		return token;
	}

	/**
	 * 删除此key
	 * 
	 * @param key
	 *            要删除的对应的key
	 */
	public void delete(String key) {
		Jedis jedis = pool.getResource();
		jedis.del(key);
		pool.returnResource(jedis);
	}

	public void delete(int key) {
		this.delete(String.valueOf(key));
	}

	public boolean validate(String userId, String token) {
		return this.validate(Integer.parseInt(userId), token);
	}
}