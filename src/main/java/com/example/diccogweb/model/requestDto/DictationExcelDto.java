package com.example.diccogweb.model.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictationExcelDto {

    private String category;
    private String step;
    private int levelNum;
    private String contents;


}
