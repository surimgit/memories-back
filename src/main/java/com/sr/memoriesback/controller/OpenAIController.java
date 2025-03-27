package com.sr.memoriesback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sr.memoriesback.common.dto.request.openai.GetWayRequestDto;
import com.sr.memoriesback.common.dto.response.openai.GetWayResponseDto;
import com.sr.memoriesback.service.OpenAIService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/open-ai")
@RequiredArgsConstructor
public class OpenAIController {
  private final OpenAIService openAIService;

  @PostMapping("/way")
  public ResponseEntity<? super GetWayResponseDto> getWay(
    @RequestBody @Valid GetWayRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ) {
    return openAIService.getWay(requestBody, userId);
  }
}
