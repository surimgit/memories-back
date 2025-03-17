package com.sr.memoriesback.service;

import org.springframework.http.ResponseEntity;

import com.sr.memoriesback.common.dto.request.auth.IdCheckRequestDto;
import com.sr.memoriesback.common.dto.request.auth.SignInRequestDto;
import com.sr.memoriesback.common.dto.request.auth.SignUpRequestDto;
import com.sr.memoriesback.common.dto.response.ResponseDto;
import com.sr.memoriesback.common.dto.response.auth.SignInResponseDto;

public interface AuthService {
  ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto);
  ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto);
  ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}