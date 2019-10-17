package com.example.diccogweb.controller;

import com.example.diccogweb.service.ExcelInputService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "excels")
public class ExcelInputController {


    private final ExcelInputService excelInputService;

    public ExcelInputController(ExcelInputService excelInputService) {
        this.excelInputService = excelInputService;
    }

    @ApiOperation("학습내용 엑셀로 입력")
    @PostMapping(value = "/excelUpload.do")
    public void filedUpload() throws IOException {
        excelInputService.readExcelFile();
    }

}
