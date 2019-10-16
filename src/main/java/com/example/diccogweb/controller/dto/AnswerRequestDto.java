package com.example.diccogweb.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRequestDto {
    private Long memSn;
    private List<Answer> answerData;
}
