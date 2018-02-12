package com.ckey.shijing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ckey.shijing.domain.Account;
import com.ckey.shijing.domain.Collection;

public interface CollectionRepository extends
		JpaRepository<Collection, Integer> {
	List<Collection> findByAccount(Account account);
}
