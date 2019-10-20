package com.example.diccogweb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponseDto {
    private Long memSn;
    private List<AnswerResult> answerResult;
}
