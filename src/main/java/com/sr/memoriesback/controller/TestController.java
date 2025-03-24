package com.sr.memoriesback.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sr.memoriesback.common.dto.request.test.PostConcentrationRequestDto;
import com.sr.memoriesback.common.dto.request.test.PostMemoryRequestDto;
import com.sr.memoriesback.common.dto.response.ResponseDto;
import com.sr.memoriesback.common.dto.response.test.GetConcentrationResponseDto;
import com.sr.memoriesback.common.dto.response.test.GetMemoryResponseDto;
import com.sr.memoriesback.service.TestService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestController {
  
  private final TestService testService;

  @PostMapping("/memory")
  public ResponseEntity<ResponseDto> postMemory(
    @RequestBody @Valid PostMemoryRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ) {
    ResponseEntity<ResponseDto> response = testService.postMemory(requestBody, userId);
    return response;
  }              
  
  @PostMapping("/concentration")
  public ResponseEntity<ResponseDto> postConcentration(
    @RequestBody @Valid PostConcentrationRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ) {
    ResponseEntity<ResponseDto> response = testService.postConcentration(requestBody, userId);
    return response;
  }

  @GetMapping("/memory")
  public ResponseEntity<? super GetMemoryResponseDto> getMemory(
    @AuthenticationPrincipal String userId
  ) {
    ResponseEntity<? super GetMemoryResponseDto> response = testService.getMemory(userId);
    return response;
  }

  @GetMapping("/concentration")
  public ResponseEntity<? super GetConcentrationResponseDto> getConcentration(
    @AuthenticationPrincipal String userId
  ) {
    ResponseEntity<? super GetConcentrationResponseDto> response = testService.getConcentration(userId);
    return response;
  }


}