package com.ckey.shijing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ckey.shijing.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	Account findById(int id);
	@Modifying
	@Transactional
	@Query("update account a set a.rest= ?1 where a.id= ?2")
	void updateAccountRest(double rest,int id);
	Account save(Account account);
	@Modifying
	@Transactional
	void deleteAccountById(int id);
	@Modifying
	@Transactional
	@Query("update account a set a.password= ?1 where a.id= ?2")
	void updateAccountPass(String pass,int id);
	Account findByPhone(String phone);
	@Modifying
	@Transactional
	@Query("update account a set a.imgPath= ?1 where a.id= ?2")
	void updataeAccountImgPath(String imgPath,int id);
}
