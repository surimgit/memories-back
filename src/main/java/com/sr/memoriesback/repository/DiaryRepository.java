package com.sr.memoriesback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sr.memoriesback.common.entity.DiaryEntity;


@Repository
public interface DiaryRepository extends JpaRepository<DiaryEntity, Integer> {
  
  DiaryEntity findByDiaryNumber(Integer diaryNumber);

  boolean existsByDiaryNumber(Integer diaryNumber);
  
  List<DiaryEntity> findByUserIdOrderByWriteDateDesc(String userId);
  List<DiaryEntity> findByOrderByDiaryNumberDesc();

}