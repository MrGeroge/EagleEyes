package com.ckey.shijing.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ckey.shijing.domain.Account;
import com.ckey.shijing.domain.Point;
import com.ckey.shijing.domain.PointRecord;
import com.ckey.shijing.repository.AccountRepository;
import com.ckey.shijing.repository.PointRecordRepository;
import com.ckey.shijing.repository.PointRepository;
import com.ckey.shijing.util.PointUtil;
import com.ckey.shijing.util.UploadTools;

@RestController
@RequestMapping("/test")
public class Test extends BaseController {
	@Autowired
	AccountRepository accountRes;
	@Autowired
	PointRecordRepository pointRecordRes;
	@Autowired
	PointRepository pointRes;
	@Autowired
	PointUtil pointController;

	@RequestMapping(method = RequestMethod.POST)
	public Account doubi(
			@RequestParam(value = "phone", required = true) String phone,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "nickname", required = true) String nickname) {
		Account account = new Account();
		account.setNickname(nickname);
		account.setPassword(password);
		account.setPhone(phone);
		account.setRest(50);
		accountRes.save(account);
		return account;
	}

	@RequestMapping(value = "/delete/account/{id}", method = RequestMethod.GET)
	public Object deleteAccount(@PathVariable int id) {
		accountRes.delete(id);
		String result = "success";
		return result;
	}
	@RequestMapping(value = "/get/account/{id}")
	public Account getAccount(@PathVariable int id) {
		return accountRes.findById(id);
	}

	@RequestMapping(value = "/add/pointRecord/{count}/account/{userId}")
	public String addPointRecord(@PathVariable int count,
			@PathVariable int userId) {
		Account account = accountRes.findById(userId);
		for (int i = 0; i < count; i++) {
			PointRecord pr = new PointRecord();
			pr.setAccount(account);
			pr.setBehave("sign");
			pr.setGrade(2);
			Date date = new Date();
			pointRecordRes.save(pr);
			pointRecordRes.updatePointRecordTime(new Date(date.getTime() + i
					* 1000), pr.getId());
		}
		return "cha ru wan cheng";
	}

	@RequestMapping(value = "/delete/account/all")
	public String deleteAllAccount() {
		accountRes.deleteAll();
		return "终了";
	}

	@RequestMapping(value = "/delete/pointRecord/all")
	public String deleteAllPointRecord() {
		pointRecordRes.deleteAll();
		return "OVER";
	}

	@RequestMapping(value = "/add/point", method = RequestMethod.POST)
	public Point addPoint(@RequestParam(value = "cost") int cost,
			@RequestParam(value = "action") String action) {
		Point point = new Point();
		point.setAction(action);
		point.setCost(cost);
		pointRes.save(point);
		return point;
	}

	@RequestMapping(value = "/point", method = RequestMethod.POST)
	public String consume(@RequestParam(value = "userId") int userId,
			@RequestParam(value = "action") String action) {
		if (pointController.consume(action, userId))
			return "congratitulation";
		return "failed";
	}

	
}
