package com.example.diccogweb.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DicStartRequestDto {
    private Long dicId;
    private String category;
    private String step;
    private String level;
    private int levelNum;
}
