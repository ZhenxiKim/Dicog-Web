package com.example.diccogweb.model.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerResult {
    private Long dicId;
    private boolean answer;
}
