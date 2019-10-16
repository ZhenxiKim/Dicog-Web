package com.example.diccogweb.controller;

import com.example.diccogweb.controller.dto.AnswerRequestDto;
import com.example.diccogweb.controller.dto.DictationRequestDto;
import com.example.diccogweb.model.responseDto.DictationResponseDto;
import com.example.diccogweb.service.DictationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "dictations", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class DictationController {

    @Autowired
    private DictationService dictationService;
    //TODO autowired로 했을때 차이 정리하기

    //TODO 엑셀입력 컨트롤러 분리 필요
    @ApiOperation("학습내용 엑셀로 입력")
    @PostMapping(value = "/excelUpload.do")
    public void filedUpload() throws IOException {
        dictationService.readExcelFile();
    }

    @ApiOperation("받아쓰기내용 가져오기")
    @GetMapping()
    public ResponseEntity<?> getDictationContents(@RequestBody DictationRequestDto dictationRequestDto) {
        String category = dictationRequestDto.getCategory();//주제
        String step = dictationRequestDto.getStep();//초중급 단계
        int levelNum = dictationRequestDto.getLevelNum();//레벨

        DictationResponseDto dictationResponseDto = dictationService.getDictationContents(category, step, levelNum);

        return ResponseEntity.ok(dictationResponseDto);
    }

    @ApiOperation("받아쓰기 정답확인")
    @PostMapping("/answers")
    public ResponseEntity<?> checkDictationAnswer(@RequestBody AnswerRequestDto answerRequestDto){
        dictationService.checkDictationAnswer(answerRequestDto);
    }
}
