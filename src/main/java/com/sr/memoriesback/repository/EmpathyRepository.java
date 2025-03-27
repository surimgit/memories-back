package com.sr.memoriesback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sr.memoriesback.common.entity.EmpathyEntity;
import com.sr.memoriesback.common.entity.pk.EmpathyPk;


@Repository
public interface EmpathyRepository extends JpaRepository<EmpathyEntity, EmpathyPk> {
  
  EmpathyEntity findByUserIdAndDiaryNumber(String userId, Integer diaryNumber);
  List<EmpathyEntity> findByDiaryNumber(Integer diaryNumber);

  @Transactional
  void deleteByDiaryNumber(Integer diaryNumber);

}