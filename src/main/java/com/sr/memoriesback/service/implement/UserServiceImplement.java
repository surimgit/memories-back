package com.sr.memoriesback.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sr.memoriesback.common.dto.request.user.PatchUserRequestDto;
import com.sr.memoriesback.common.dto.response.ResponseDto;
import com.sr.memoriesback.common.dto.response.user.GetSignInUserResponseDto;
import com.sr.memoriesback.common.entity.UserEntity;
import com.sr.memoriesback.repository.UserRepository;
import com.sr.memoriesback.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService{

  private final UserRepository userRepository;

  @Override
  public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String userId) {
    UserEntity userEntity = null;

    try {
      userEntity = userRepository.findByUserId(userId);
      
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetSignInUserResponseDto.success(userEntity);
  }

  @Override
  public ResponseEntity<ResponseDto> patchUser(PatchUserRequestDto dto, String userId) {
    try {
      UserEntity userEntity = userRepository.findByUserId(userId);
      userEntity.Patch(dto);
      userRepository.save(userEntity);
      
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.OK);
  }
  
}
