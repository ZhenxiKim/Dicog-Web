package com.example.diccogweb.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictationRequestDto {

    private String category;//주제
    private String step;//초중급 단계
    private int levelNum;//레벨
}
