package com.ckey.shijing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ckey.shijing.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer>{

}
