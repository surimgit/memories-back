package com.sr.memoriesback.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sr.memoriesback.common.entity.UserEntity;

public interface UserTestRepository extends JpaRepository<UserEntity, String>{
  UserEntity findByUserName(String userName);
  UserEntity findAllOrderByUserBirthDesc();
  UserEntity findByUserGenderAndUserAddressContainingOrderByUserBirth(String userGender, String userAddress);
  int countByUserGenderAndUserAddressBetween(String userGenter, LocalDate start, LocalDate end);
}
