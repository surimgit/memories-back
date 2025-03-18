package com.sr.memoriesback.service;

import org.springframework.http.ResponseEntity;

import com.sr.memoriesback.common.dto.request.diary.PatchDiaryRequestDto;
import com.sr.memoriesback.common.dto.request.diary.PostDiaryRequestDto;
import com.sr.memoriesback.common.dto.response.ResponseDto;
import com.sr.memoriesback.common.dto.response.diary.GetDiaryResponseDto;
import com.sr.memoriesback.common.dto.response.diary.GetMyDiaryResponseDto;

public interface DiaryService {
  ResponseEntity<ResponseDto> postDiary(PostDiaryRequestDto dto, String userId);
  ResponseEntity<? super GetMyDiaryResponseDto> getMyDiary(String userId);
  ResponseEntity<? super GetDiaryResponseDto> getDiary(Integer diaryNumber);
  ResponseEntity<ResponseDto> patchDairy(PatchDiaryRequestDto dto, Integer diaryNumber, String userId);
  ResponseEntity<ResponseDto> deleteDairy(Integer diaryNumber, String userId);  
}

