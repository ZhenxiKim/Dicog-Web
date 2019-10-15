package com.example.diccogweb.controller;

import com.example.diccogweb.model.requestDto.DicStartRequestDto;
import com.example.diccogweb.model.responseDto.DictationResponseDto;
import com.example.diccogweb.service.DictationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class DictationController {

    @Autowired
    private DictationService dictationService;

    @ApiOperation("학습내용 엑셀로 입력")
    @PostMapping(value = "/excelUpload.do")
    public void filedUpload() throws IOException {
        dictationService.readExcelFile();
    }

    @ApiOperation("받아쓰기내용 가져오기")
    @GetMapping(value="getDicInfo")
    public DictationResponseDto getDictationInfo(@RequestBody DicStartRequestDto dicStartRequestDto){
        dictationService.getInfo(dicStartRequestDto);
        DictationResponseDto dictationResponseDto = new DictationResponseDto();
        return dictationResponseDto;
    }
}
