package com.ckey.shijing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ckey.shijing.domain.Account;
import com.ckey.shijing.domain.Picture;

public interface PictureRepository extends JpaRepository<Picture, Integer> {
	
	Picture findById(int id);
	List<Picture> findByAccount(Account account);

}
