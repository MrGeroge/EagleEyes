package com.ckey.shijing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ckey.shijing.domain.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

}
