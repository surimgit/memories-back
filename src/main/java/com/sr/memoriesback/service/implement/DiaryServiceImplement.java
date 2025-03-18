package com.sr.memoriesback.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sr.memoriesback.common.dto.request.diary.PatchDiaryRequestDto;
import com.sr.memoriesback.common.dto.request.diary.PostDiaryRequestDto;
import com.sr.memoriesback.common.dto.response.ResponseDto;
import com.sr.memoriesback.common.dto.response.diary.GetDiaryResponseDto;
import com.sr.memoriesback.common.dto.response.diary.GetMyDiaryResponseDto;
import com.sr.memoriesback.common.entity.DiaryEntity;
import com.sr.memoriesback.repository.DiaryRepository;
import com.sr.memoriesback.service.DiaryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiaryServiceImplement implements DiaryService {

  private final DiaryRepository diaryRepository;

  @Override
  public ResponseEntity<ResponseDto> postDiary(PostDiaryRequestDto dto, String userId) {
    try {
      DiaryEntity diaryEntity = new DiaryEntity(dto, userId);
      diaryRepository.save(diaryEntity);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return ResponseDto.success(HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<? super GetMyDiaryResponseDto> getMyDiary(String userId) {

    List<DiaryEntity> diaryEntities = new ArrayList<>();

    try {
      diaryEntities = diaryRepository.findByUserId(userId);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();  
    }

    return GetMyDiaryResponseDto.success(diaryEntities);
  }

  @Override
  public ResponseEntity<? super GetDiaryResponseDto> getDiary(Integer diaryNumber) {
    DiaryEntity diaryEntity = null;
    try {
      diaryEntity = diaryRepository.findByDiaryNumber(diaryNumber);
      if(diaryEntity == null) return ResponseDto.noExistDiary();
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return GetDiaryResponseDto.success(diaryEntity);
  }

  @Override
  public ResponseEntity<ResponseDto> patchDairy(PatchDiaryRequestDto dto, Integer diaryNumber, String userId) {
    try {
      DiaryEntity diaryEntity = diaryRepository.findByDiaryNumber(diaryNumber);
      if(diaryEntity == null) return ResponseDto.noExistDiary();
      String writerId = diaryEntity.getUserId();
      boolean isWriter = writerId.equals(userId);
      if(!isWriter) return ResponseDto.noPermission();
      
      diaryEntity.patch(dto);
      diaryRepository.save(diaryEntity);

    } catch (Exception e) {
      e.printStackTrace();
      ResponseDto.databaseError();
    }
    return ResponseDto.success(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ResponseDto> deleteDairy(Integer diaryNumber, String userId) {
    try {
      DiaryEntity diaryEntity = diaryRepository.findByDiaryNumber(diaryNumber);
      if(diaryEntity == null) return ResponseDto.noExistDiary();
      String writerId = diaryEntity.getUserId();
      boolean isWriter = writerId.equals(userId);
      if(!isWriter) return ResponseDto.noPermission();
      diaryRepository.delete(diaryEntity);
    } catch (Exception e) {
      e.printStackTrace();
      ResponseDto.databaseError();
    }
    return ResponseDto.success(HttpStatus.OK);
  }
  
}
