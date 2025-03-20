package com.sr.memoriesback.service;

import org.springframework.http.ResponseEntity;

import com.sr.memoriesback.common.dto.request.user.PatchUserRequestDto;
import com.sr.memoriesback.common.dto.response.ResponseDto;
import com.sr.memoriesback.common.dto.response.user.GetSignInUserResponseDto;

public interface UserService {
  ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String userId);
  ResponseEntity<ResponseDto> patchUser(PatchUserRequestDto dto, String userId);
}
