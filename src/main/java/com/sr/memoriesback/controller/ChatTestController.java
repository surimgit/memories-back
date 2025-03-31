package com.sr.memoriesback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sr.memoriesback.common.dto.request.openai.ChatTestRequestDto;
import com.sr.memoriesback.common.dto.response.openai.ChatTestResponseDto;
import com.sr.memoriesback.service.ChatTestService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/openai")
@RequiredArgsConstructor
public class ChatTestController {
  private final ChatTestService chatTestService;

  @PostMapping("/chat")
  public ResponseEntity<ChatTestResponseDto> chat(
    @RequestBody @Valid ChatTestRequestDto requestBody
  ) {
    return chatTestService.chat(requestBody);
  }
}
