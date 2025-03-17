package com.sr.memoriesback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sr.memoriesback.common.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
  boolean existsByUserId(String userId);
  UserEntity findByUserId(String userId);
  UserEntity findByJoinTypeAndSnsId(String joinType, String snsId);
}
