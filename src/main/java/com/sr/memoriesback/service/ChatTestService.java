package com.sr.memoriesback.service;

import org.springframework.http.ResponseEntity;

import com.sr.memoriesback.common.dto.request.openai.ChatTestRequestDto;
import com.sr.memoriesback.common.dto.response.openai.ChatTestResponseDto;

public interface ChatTestService {
  ResponseEntity<ChatTestResponseDto> chat(ChatTestRequestDto dto);
}
