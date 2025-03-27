package com.sr.memoriesback.common.dto.response.diary;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sr.memoriesback.common.dto.response.ResponseDto;
import com.sr.memoriesback.common.entity.CommentEntity;
import com.sr.memoriesback.common.vo.CommentVO;

import lombok.Getter;

@Getter
public class GetCommentResponseDto extends ResponseDto {
  private List<CommentVO> comments;

  private GetCommentResponseDto(List<CommentEntity> commentEntities){
    this.comments = CommentVO.getList(commentEntities);
  }

  public static ResponseEntity<GetCommentResponseDto> success(List<CommentEntity> commentEntities){
    GetCommentResponseDto body = new GetCommentResponseDto(commentEntities);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
