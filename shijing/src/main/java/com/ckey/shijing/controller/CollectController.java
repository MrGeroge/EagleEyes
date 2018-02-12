package com.ckey.shijing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ckey.shijing.domain.Account;
import com.ckey.shijing.domain.Collection;
import com.ckey.shijing.repository.AccountRepository;
import com.ckey.shijing.repository.CollectionRepository;
import com.ckey.shijing.vo.CollectVO;

@RestController
@RequestMapping(value = "/collection")
public class CollectController extends BaseController {
	@Autowired
	CollectionRepository collectRes;
	@Autowired
	AccountRepository accountRes;

	@RequestMapping(method = RequestMethod.POST)
	public Object collect(@RequestParam(value = "userId") int userId,
			@RequestParam(value = "description") String description,
			@RequestParam(value = "latitude") String latitude,
			@RequestParam(value = "longitude") String longitude,
			@RequestParam(value = "token") String token) {
		Account account = accountRes.findById(userId);
		Collection collection = new Collection();
		collection.setAccount(account);
		collection.setDescription(description);
		collection.setLongitude(longitude);
		collection.setLatitude(latitude);
		collectRes.save(collection);
		String result = "success";
		return result;
	}

	@RequestMapping(value = "/my", method = RequestMethod.POST)
	public List<CollectVO> index(@RequestParam(value = "userId") int userId,
			@RequestParam(value = "token") String token) throws Exception {
		Account account = new Account();
		List<CollectVO> collection = new ArrayList<CollectVO>();
		List<Collection> collections = new ArrayList<Collection>();
		account = accountRes.findById(userId);
		collections = collectRes.findByAccount(account);
		for (Collection c : collections) {
			CollectVO collect = new CollectVO();
			collect.setLatitude(c.getLatitude());
			collect.setLongitude(c.getLongitude());
			collect.setName(c.getDescription());
			collection.add(collect);
		}
		return collection;
	}
}
