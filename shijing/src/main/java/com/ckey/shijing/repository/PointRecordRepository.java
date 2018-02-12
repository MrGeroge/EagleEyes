package com.ckey.shijing.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ckey.shijing.domain.Account;
import com.ckey.shijing.domain.PointRecord;

public interface PointRecordRepository extends JpaRepository<PointRecord, Integer> {
	Page<PointRecord> findPointRecordByAccount(Account account,Pageable page);
	PointRecord save(PointRecord pointRecord);
	@Modifying
	@Transactional
	@Query("update point_record pr set pr.time = ?1 where pr.id = ?2")
	void updatePointRecordTime(Date time,int id);
}
