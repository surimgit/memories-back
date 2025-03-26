package com.sr.memoriesback.service;

import org.springframework.http.ResponseEntity;

import com.sr.memoriesback.common.dto.request.test.PostConcentrationRequestDto;
import com.sr.memoriesback.common.dto.request.test.PostMemoryRequestDto;
import com.sr.memoriesback.common.dto.response.ResponseDto;
import com.sr.memoriesback.common.dto.response.test.GetConcentrationResponseDto;
import com.sr.memoriesback.common.dto.response.test.GetMemoryResponseDto;
import com.sr.memoriesback.common.dto.response.test.GetRecentlyConcentrationResponseDto;
import com.sr.memoriesback.common.dto.response.test.GetRecentlyMemoryResponseDto;

public interface TestService {
  ResponseEntity<ResponseDto> postMemory(PostMemoryRequestDto dto, String userId);
  ResponseEntity<ResponseDto> postConcentration(PostConcentrationRequestDto dto, String userId);
  ResponseEntity<? super GetMemoryResponseDto> getMemory(String userId);
  ResponseEntity<? super GetConcentrationResponseDto> getConcentration(String userId);
  ResponseEntity<? super GetRecentlyMemoryResponseDto> getRecentlyMemory(String userId);
  ResponseEntity<? super GetRecentlyConcentrationResponseDto> getRecentlyConcentration(String userId);
}
