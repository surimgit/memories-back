package com.sr.memoriesback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sr.memoriesback.common.entity.ConcentrationTestEntity;
import com.sr.memoriesback.common.entity.pk.ConcentrationTestPk;

@Repository
public interface ConcentrationTestRepository extends JpaRepository<ConcentrationTestEntity, ConcentrationTestPk>{
  Integer countByUserId(String userId);
  ConcentrationTestEntity findByUserIdAndSequence(String userId, Integer sequence);
  List<ConcentrationTestEntity> findByUserIdOrderBySequenceDesc(String userId);
}
