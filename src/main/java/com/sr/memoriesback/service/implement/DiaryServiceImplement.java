package com.sr.memoriesback.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sr.memoriesback.common.dto.request.diary.PatchDiaryRequestDto;
import com.sr.memoriesback.common.dto.request.diary.PostCommentRequestDto;
import com.sr.memoriesback.common.dto.request.diary.PostDiaryRequestDto;
import com.sr.memoriesback.common.dto.response.ResponseDto;
import com.sr.memoriesback.common.dto.response.diary.GetCommentResponseDto;
import com.sr.memoriesback.common.dto.response.diary.GetDiaryResponseDto;
import com.sr.memoriesback.common.dto.response.diary.GetEmpathyResponseDto;
import com.sr.memoriesback.common.dto.response.diary.GetMyDiaryResponseDto;
import com.sr.memoriesback.common.entity.CommentEntity;
import com.sr.memoriesback.common.entity.DiaryEntity;
import com.sr.memoriesback.common.entity.EmpathyEntity;
import com.sr.memoriesback.repository.CommentRepository;
import com.sr.memoriesback.repository.DiaryRepository;
import com.sr.memoriesback.repository.EmpathyRepository;
import com.sr.memoriesback.service.DiaryService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class DiaryServiceImplement implements DiaryService {

  private final DiaryRepository diaryRepository;
  private final EmpathyRepository empathyRepository;
  private final CommentRepository commentRepository;

  @Override
  public ResponseEntity<ResponseDto> postDiary(PostDiaryRequestDto dto, String userId) {

    try {

      DiaryEntity diaryEntity = new DiaryEntity(dto, userId);
      diaryRepository.save(diaryEntity);

    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.CREATED);

  }

  @Override
  public ResponseEntity<? super GetMyDiaryResponseDto> getMyDiary(String userId) {

    List<DiaryEntity> diaryEntities = new ArrayList<>();
    
    try {

      diaryEntities = diaryRepository.findByOrderByDiaryNumberDesc();

    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetMyDiaryResponseDto.success(diaryEntities);

  }

  @Override
  public ResponseEntity<? super GetDiaryResponseDto> getDiary(Integer diaryNumber) {

    DiaryEntity diaryEntity = null;
    
    try {

      diaryEntity = diaryRepository.findByDiaryNumber(diaryNumber);
      if (diaryEntity == null) return ResponseDto.noExistDiary();
      
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetDiaryResponseDto.success(diaryEntity);

  }

  @Override
  public ResponseEntity<ResponseDto> patchDiary(PatchDiaryRequestDto dto, Integer diaryNumber, String userId) {
    
    try {

      DiaryEntity diaryEntity = diaryRepository.findByDiaryNumber(diaryNumber);
      if (diaryEntity == null) return ResponseDto.noExistDiary();

      String writerId = diaryEntity.getUserId();
      boolean isWriter = writerId.equals(userId);
      if (!isWriter) return ResponseDto.noPermission();

      diaryEntity.patch(dto);
      diaryRepository.save(diaryEntity);
      
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.OK);

  }

  @Override
  public ResponseEntity<ResponseDto> deleteDiary(Integer diaryNumber, String userId) {
    
    try {
      
      DiaryEntity diaryEntity = diaryRepository.findByDiaryNumber(diaryNumber);
      if (diaryEntity == null) return ResponseDto.noExistDiary();

      String writerId = diaryEntity.getUserId();
      boolean isWriter = writerId.equals(userId);
      if (!isWriter) return ResponseDto.noPermission();

      empathyRepository.deleteByDiaryNumber(diaryNumber);
      commentRepository.deleteByDiaryNumber(diaryNumber);
      diaryRepository.delete(diaryEntity);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.OK);

  }

  @Override
  public ResponseEntity<? super GetEmpathyResponseDto> getEmpathy(Integer diaryNumber) {

    List<EmpathyEntity> empathyEntities = new ArrayList<>();
    
    try {
      
      empathyEntities = empathyRepository.findByDiaryNumber(diaryNumber);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetEmpathyResponseDto.success(empathyEntities);

  }

  @Override
  public ResponseEntity<ResponseDto> putEmpathy(Integer diaryNumber, String userId) {
    
    try {

      boolean existDiary = diaryRepository.existsByDiaryNumber(diaryNumber);
      if(!existDiary) return ResponseDto.noExistDiary();

      EmpathyEntity empathyEntity = empathyRepository.findByUserIdAndDiaryNumber(userId, diaryNumber);
      if (empathyEntity == null) {
        empathyEntity = new EmpathyEntity(userId, diaryNumber);
        empathyRepository.save(empathyEntity);
      } else {
        empathyRepository.delete(empathyEntity);
      }

    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.OK);

  }

  @Override
  public ResponseEntity<ResponseDto> postComment(PostCommentRequestDto dto, Integer diaryNumber, String userId) {

    try {
      boolean existDiary = diaryRepository.existsByDiaryNumber(diaryNumber);
      if(!existDiary) return ResponseDto.noExistDiary();

      CommentEntity commentEntity = new CommentEntity(dto, diaryNumber, userId);
      commentRepository.save(commentEntity);
      
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<? super GetCommentResponseDto> getComment(Integer diaryNumber) {
    List<CommentEntity> CommentEntities = new ArrayList<>(); 

    try {
      CommentEntities = commentRepository.findByDiaryNumberOrderByWriteDateDesc(diaryNumber);
      
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return GetCommentResponseDto.success(CommentEntities);
  }


  
}