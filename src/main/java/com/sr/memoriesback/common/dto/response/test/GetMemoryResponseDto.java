package com.sr.memoriesback.common.dto.response.test;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sr.memoriesback.common.dto.response.ResponseDto;
import com.sr.memoriesback.common.entity.MemoryTestEntity;
import com.sr.memoriesback.common.vo.MemoryTestVO;

import lombok.Getter;
@Getter
public class GetMemoryResponseDto extends ResponseDto {
  
  private List<MemoryTestVO> memoryTests;

  private GetMemoryResponseDto(List<MemoryTestEntity> memoryTestEntities) {
    this.memoryTests = MemoryTestVO.getList(memoryTestEntities);
  }

  public static ResponseEntity<GetMemoryResponseDto> success(List<MemoryTestEntity> memoryTestEntities) {
    GetMemoryResponseDto body = new GetMemoryResponseDto(memoryTestEntities);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }

}