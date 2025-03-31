package com.sr.memoriesback.service.implement;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sr.memoriesback.common.dto.request.openai.ChatRequestDto;
import com.sr.memoriesback.common.dto.request.openai.ChatTestRequestDto;
import com.sr.memoriesback.common.dto.response.openai.ChatResponseDto;
import com.sr.memoriesback.common.dto.response.openai.ChatTestResponseDto;
import com.sr.memoriesback.common.vo.GptMessageVO;
import com.sr.memoriesback.service.ChatTestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatTestServiceImplement implements ChatTestService{

  private final WebClient webClient;
  
  
  @Override
  public ResponseEntity<ChatTestResponseDto> chat(ChatTestRequestDto dto) {
    
    String question = dto.getQuestion();
    List<GptMessageVO> message = List.of(new GptMessageVO("user", question));
    ChatRequestDto requestBody = new ChatRequestDto("gpt-4o-mini", message);

    ChatResponseDto responseBody = webClient.post()
      .uri("/chat/completions")
      .bodyValue(requestBody)
      .retrieve()
      .bodyToMono(ChatResponseDto.class)
      .block();
    
    String answer = null;

    if(responseBody == null || responseBody.getChoices() == null || responseBody.getChoices().isEmpty()){
      return null;
    }

    answer = responseBody.getChoices().get(0).getMessage().getContent();
    return ChatTestResponseDto.success(answer);
  }


}
