package com.ckey.shijing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ckey.shijing.domain.Account;
import com.ckey.shijing.domain.Path;
import com.ckey.shijing.repository.AccountRepository;
import com.ckey.shijing.repository.PathRepository;
import com.ckey.shijing.vo.PathVO;

@RestController
@RequestMapping(value = "/path")
public class PathController {
	@Autowired
	PathRepository pathRes;
	@Autowired
	private AccountRepository accountRes;

	@RequestMapping(value = "my", method = RequestMethod.POST)
	public List<PathVO> myPath(@RequestParam("userId") int userId,
			@RequestParam(value = "token") String token) {
		Account account = accountRes.findOne(userId);
		List<PathVO> path = new ArrayList<PathVO>();
		List<Path> items = pathRes.findByAccount(account);
		for (Path item : items) {
			PathVO data = new PathVO();
			data.setFlag(item.getFlag());
			data.setLatitude(item.getLatitude());
			data.setLongitude(item.getLongitude());
			data.setName(item.getDes());
			data.setImgPath(item.getMidSize());
			path.add(data);
		}
		return path;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object setGoal(@RequestParam("userId") int userId,
			@RequestParam(value = "token") String token,
			@RequestParam(value = "description") String description,
			@RequestParam(value = "latitude") String latitude,
			@RequestParam(value = "longitude") String longitude,
			@RequestParam(value = "maxSize") String maxSize) {
		Account account = accountRes.findById(userId);
		Path path = new Path();
		path.setDes(description);
		path.setFlag(false);
		path.setLatitude(latitude);
		path.setLongitude(longitude);
		path.setMidSize(maxSize);
		path.setAccount(account);
		pathRes.save(path);
		return path.getId();

	}
}
