package com.sr.memoriesback.common.dto.request.test;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostConcentrationRequestDto {
  @NotNull
  @Min(0)
  private Integer measurementScore;
  @Min(0)
  @NotNull
  private Integer errorCount;
}
