package com.ckey.shijing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ckey.shijing.domain.Account;
import com.ckey.shijing.domain.IndexData;
import com.ckey.shijing.repository.AccountRepository;
import com.ckey.shijing.repository.IndexDataRepository;
import com.ckey.shijing.vo.IndexVO;
@RestController
@RequestMapping("/index")
public class IndexController {
	@Autowired
	private IndexDataRepository indexRes;
	@Autowired
	private AccountRepository accountRes;
	@RequestMapping(method=RequestMethod.POST)
	public IndexVO index(@RequestParam("userId") int userId,
			@RequestParam(value = "token") String token){
		IndexVO index= new IndexVO();
		PageRequest pageParam = new PageRequest(0, 5,
				Sort.Direction.DESC, "id");
		Page<IndexData> items = indexRes.findAll(pageParam);
		for(IndexData item:items)
			index.getIndex().add(item);
		Account account  = accountRes.findOne(userId);
		index.setImgPath(account.getImgPath());
		index.setRest(account.getRest());
		index.setUserName(account.getNickname());
		return index;
	} 
	
}
