package com.sr.memoriesback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sr.memoriesback.common.entity.MemoryTestEntity;
import com.sr.memoriesback.common.entity.pk.MemoryTestPk;


@Repository
public interface MemoryTestRepository extends JpaRepository<MemoryTestEntity, MemoryTestPk>{
  int countByUserId(String userId);

  MemoryTestEntity findByUserIdAndSequence(String userId, Integer sequence);

  List<MemoryTestEntity> findByUserIdOrderBySequenceDesc(String userId);

}
