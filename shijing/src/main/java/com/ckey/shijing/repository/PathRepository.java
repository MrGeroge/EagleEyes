package com.ckey.shijing.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ckey.shijing.domain.Account;
import com.ckey.shijing.domain.Path;

@Repository
public interface PathRepository extends JpaRepository<Path, Integer>{
	List<Path> findByAccount(Account account);
	@Modifying
	@Transactional
	@Query("update Path a set a.flag= ?1 where a.id= ?2")
	void updateFlag(boolean flag,int id);
}
