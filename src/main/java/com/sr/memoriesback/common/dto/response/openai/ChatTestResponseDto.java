package com.sr.memoriesback.common.dto.response.openai;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

@Getter
public class ChatTestResponseDto {
  private String answer;

  private ChatTestResponseDto(String answer){
    this.answer = answer;
  }

  public static ResponseEntity<ChatTestResponseDto> success(String answer){
    ChatTestResponseDto body = new ChatTestResponseDto(answer);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }

}
