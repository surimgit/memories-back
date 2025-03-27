package com.sr.memoriesback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sr.memoriesback.common.entity.CommentEntity;



@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
  List<CommentEntity> findByDiaryNumberOrderByWriteDateDesc(Integer diaryNumber);

  @Transactional
  void deleteByDiaryNumber(Integer diaryNumber);
}