package com.example.diccogweb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictationResponseDto {
    //private List randomIntList;//학습내용 꺼내올 랜덤 index
    private List dictationList;//학습내용 리스트
}
