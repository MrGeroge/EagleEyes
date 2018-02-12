package com.ckey.shijing.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ckey.shijing.domain.Account;
import com.ckey.shijing.domain.Comment;
import com.ckey.shijing.domain.Picture;
import com.ckey.shijing.repository.AccountRepository;
import com.ckey.shijing.repository.CommentRepository;
import com.ckey.shijing.repository.PictureRepository;
import com.ckey.shijing.util.PointUtil;

@RestController
public class CommentController extends BaseController {
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	PictureRepository pictureRepository;

	@RequestMapping("/comment")
	public Object comment(@RequestParam(value = "userId") int userId,
			@RequestParam("content") String content,
			@RequestParam("pictureId") int picture_id,
			@RequestParam("status") boolean status,
			@RequestParam(value = "token") String token) {
		Account account = new Account();
		Picture picture = new Picture();
		Comment comment = new Comment();
		account = accountRepository.findById(userId);
		picture = pictureRepository.findById(picture_id);
		comment.setAccount(account);
		comment.setContent(content);
		comment.setPicture(picture);
		comment.setTime(new Date());
		PointUtil pu = new PointUtil();
		if (status) { // 悬赏的
			pu.consume("comment", userId);
		} else { //
			pu.consume("commented", userId);
		}
		String result = "success";
		return result;
	}

}
