package com.ckey.shijing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ckey.shijing.domain.Point;

public interface PointRepository extends JpaRepository<Point,Integer>{
	@Modifying
	@Transactional
	@Query("update point p set p.cost=?1 where p.action=?2")
	void updateCostByAction(int cost,String action);
	Point findByAction(String action);
}
