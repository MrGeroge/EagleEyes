package com.ckey.shijing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ckey.shijing.domain.Account;
import com.ckey.shijing.domain.PointRecord;
import com.ckey.shijing.repository.AccountRepository;
import com.ckey.shijing.repository.PathRepository;
import com.ckey.shijing.repository.PointRecordRepository;
import com.ckey.shijing.util.PointUtil;
import com.ckey.shijing.vo.PointRecordVO;

@RestController
@RequestMapping("/point")
public class PointRecordController extends BaseController {
	@Autowired
	private PointRecordRepository pointRecordRes;
	@Autowired
	private AccountRepository accountRes;
	@Autowired
	PointUtil pointController;
	@Autowired
	private PathRepository pathRes;

	@RequestMapping(value = "/{userId}/{page}/{count}", method = RequestMethod.GET)
	public List<PointRecordVO> get(@PathVariable int page,
			@PathVariable int count, @PathVariable int userId) {
		PageRequest pageParam = new PageRequest(page, count,
				Sort.Direction.DESC, "time");
		Account account = accountRes.findById(userId);
		Page<PointRecord> records = pointRecordRes.findPointRecordByAccount(
				account, pageParam);
		List<PointRecordVO> pointRecords = new ArrayList<PointRecordVO>();
		for (PointRecord record : records) {
			PointRecordVO prVO = new PointRecordVO();
			prVO.setBehave(record.getBehave());
			prVO.setTime(record.getTime());
			prVO.setUsername(record.getAccount().getNickname());
			prVO.setGrade(record.getGrade());
			pointRecords.add(prVO);
		}
		return pointRecords;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object sign(@RequestParam(value = "userId") int userId,
			@RequestParam(value = "token") String token,
			@RequestParam(value = "pathId") int pathId) {
		pointController.consume("sign", userId);
		pathRes.updateFlag(true, pathId);
		String result = "success";
		return result;
	}
}
