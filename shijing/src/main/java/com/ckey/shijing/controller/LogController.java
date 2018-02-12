package com.ckey.shijing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.JedisPool;

import com.ckey.shijing.domain.Account;
import com.ckey.shijing.exception.LoginException;
import com.ckey.shijing.repository.AccountRepository;
import com.ckey.shijing.repository.CollectionRepository;
import com.ckey.shijing.repository.PictureRepository;
import com.ckey.shijing.service.JedisService;
import com.ckey.shijing.util.Md5Utils;
import com.ckey.shijing.vo.Result;
import com.ckey.shijing.vo.TokenVO;

@RestController
public class LogController {
	private static JedisPool pool = null; // jedis的一个连接池

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	AccountRepository accountRes;
	@Autowired
	private JedisService jedis;

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public Object login(
			@RequestParam(value = "phone", required = true) String phone,
			@RequestParam(value = "password", required = true) String password)
			throws Exception {
		Account account = accountRes.findByPhone(phone);
		if (Md5Utils.EncodePass(password).equals(account.getPassword())) {
			TokenVO token = new TokenVO();
			token.setUserId(account.getId());
			token.setToken(jedis.authoriz(account.getId()));
			return token;
		}
		throw new LoginException();
	}
}
