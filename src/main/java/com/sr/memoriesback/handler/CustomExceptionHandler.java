package com.sr.memoriesback.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sr.memoriesback.common.dto.response.ResponseDto;

@RestControllerAdvice
public class CustomExceptionHandler {
  
  //description: MethodArgumentNotValidException - 유효성 검사 실패 //
  //description: HttpMessageNotWritableException - RequestBody가 필요한데 존재하지 않을 때 //
  @ExceptionHandler({
    MethodArgumentNotValidException.class,
    HttpMessageNotReadableException.class
  })
  public ResponseEntity<ResponseDto> validExceptionHandler(Exception exception){
    exception.printStackTrace();
    return ResponseDto.validationFail();
  }

}
