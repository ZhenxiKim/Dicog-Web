package com.example.diccogweb.model.responseDto;

import com.example.diccogweb.model.Grade;
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
