package com.example.diccogweb.model;

import com.example.diccogweb.controller.dto.DictationExcelDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="dictation")
public class Dictation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dicId;
    //private String category;//주제
    //private String step;//초중급 단계
    //private int levelNum;//숫자 레벨
    private String contents;

    @OneToMany
    @JoinColumn(name="fileNo")
    private List<Files> files;

    public Dictation(DictationExcelDto dictationDto) {
//        this.category = dictationDto.getCategory();
//        this.step = dictationDto.getStep();
//        this.levelNum = dictationDto.getLevelNum();
        this.contents = dictationDto.getContents();
    }
}
