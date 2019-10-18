package com.example.diccogweb.controller;

import com.example.diccogweb.controller.dto.AnswerRequestDto;
import com.example.diccogweb.controller.dto.DictationRequestDto;
import com.example.diccogweb.exception.DataNotFoundException;
import com.example.diccogweb.model.Grade;
import com.example.diccogweb.model.responseDto.AnswerResponseDto;
import com.example.diccogweb.model.responseDto.AnswerResult;
import com.example.diccogweb.model.responseDto.DictationResponseDto;
import com.example.diccogweb.service.DictationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "dictations", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class DictationController {

    @Autowired
    private DictationService dictationService;
    //TODO autowired로 했을때 차이 정리하기

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
    public ResponseEntity<?> checkDictationAnswer(@RequestBody AnswerRequestDto answerRequestDto) throws DataNotFoundException {
        List<AnswerResult> answerResultList = dictationService.checkDictationAnswer(answerRequestDto);
        AnswerResponseDto answerResponseDto = new AnswerResponseDto();
        answerResponseDto.setMemSn(answerRequestDto.getMemSn());
        answerResponseDto.setAnswerResult(answerResultList);
        return ResponseEntity.ok(answerResponseDto);
    }

    @GetMapping("/getPicture/{fileId}")
    public ResponseEntity<Resource> getPicture(@PathVariable(value = "fileId") Long fileId) throws Throwable{
        org.springframework.core.io.Resource resource = dictationService.loadFileAsResource(fileId);
        String contentType = "image/"+resource.getFilename().split("\\.")[1];

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
