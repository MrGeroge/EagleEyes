package com.ckey.shijing.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ckey.shijing.domain.Account;
import com.ckey.shijing.domain.Point;
import com.ckey.shijing.domain.PointRecord;
import com.ckey.shijing.repository.AccountRepository;
import com.ckey.shijing.repository.PointRecordRepository;
import com.ckey.shijing.repository.PointRepository;
@Service
public class PointUtil {
	@Autowired
	private PointRecordRepository pointRecordRes;
	@Autowired
	private AccountRepository accountRes;
	@Autowired
	private PointRepository pointRes;
	public boolean consume(String action,int userId){
		
		Point point =pointRes.findByAction(action);
		Account account = accountRes.findById(userId);
		if(point==null){
			return false;
		}
		accountRes.updateAccountRest(account.getRest()+point.getCost(), account.getId());
		PointRecord pointRecord = new PointRecord();
		pointRecord.setAccount(account);
		pointRecord.setBehave(action);
		pointRecord.setGrade(point.getCost());
		pointRecordRes.save(pointRecord);
		return true;
	}
}
