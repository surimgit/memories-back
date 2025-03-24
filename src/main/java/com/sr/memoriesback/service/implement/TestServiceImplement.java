package com.sr.memoriesback.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sr.memoriesback.common.dto.request.test.PostConcentrationRequestDto;
import com.sr.memoriesback.common.dto.request.test.PostMemoryRequestDto;
import com.sr.memoriesback.common.dto.response.ResponseDto;
import com.sr.memoriesback.common.dto.response.test.GetConcentrationResponseDto;
import com.sr.memoriesback.common.dto.response.test.GetMemoryResponseDto;
import com.sr.memoriesback.common.entity.ConcentrationTestEntity;
import com.sr.memoriesback.common.entity.MemoryTestEntity;
import com.sr.memoriesback.repository.ConcentrationTestRepository;
import com.sr.memoriesback.repository.MemoryTestRepository;
import com.sr.memoriesback.service.TestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestServiceImplement implements TestService{
  private final MemoryTestRepository memoryTestRepository;
  private final ConcentrationTestRepository concentrationTestRepository;

  @Override
  public ResponseEntity<ResponseDto> postMemory(PostMemoryRequestDto dto, String userId) {
    try {
      MemoryTestEntity memoryTestEntity = null;

      Integer preSequence = memoryTestRepository.countByUserId(userId);
      if (preSequence == 0) {
        memoryTestEntity = new MemoryTestEntity(dto, userId);
      } else {
        MemoryTestEntity preMemoryTestEntity = memoryTestRepository.findByUserIdAndSequence(userId, preSequence);
        memoryTestEntity = new MemoryTestEntity(dto, preMemoryTestEntity, userId);
      }

      memoryTestRepository.save(memoryTestEntity);
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<? super GetMemoryResponseDto> getMemory(String userId) {
    List<MemoryTestEntity> memoryTestEntities = new ArrayList<>();
    try {
      memoryTestEntities = memoryTestRepository.findByUserIdOrderBySequenceDesc(userId);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return GetMemoryResponseDto.success(memoryTestEntities);
  }

  @Override
  public ResponseEntity<ResponseDto> postConcentration(PostConcentrationRequestDto dto, String userId) {
    try {
      ConcentrationTestEntity concentrationTestEntity = null;
      Integer preSequence = concentrationTestRepository.countByUserId(userId);
      if(preSequence == 0) {
        concentrationTestEntity = new ConcentrationTestEntity(dto, userId);
      }else{
        ConcentrationTestEntity preConcentrationTestEntity = concentrationTestRepository.findByUserIdAndSequence(userId, preSequence);
        concentrationTestEntity = new ConcentrationTestEntity(dto, preConcentrationTestEntity, userId);
      }

      concentrationTestRepository.save(concentrationTestEntity);
 
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return ResponseDto.success(HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<? super GetConcentrationResponseDto> getConcentration(String userId) {
    List<ConcentrationTestEntity> concentrationTestEntities = new ArrayList<>();
    try {
      concentrationTestEntities = concentrationTestRepository.findByUserIdOrderBySequenceDesc(userId);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetConcentrationResponseDto.success(concentrationTestEntities);
  }
  
}
