package com.example.diccogweb.controller;

import com.example.diccogweb.service.ProgressService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/progresses", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class ProgressController {
    //맞춘거체크,진행상황체크
    private final ProgressService progressService;
    public ProgressController(ProgressService progressService){
        this.progressService = progressService;
    }

//    @ApiOperation(value="받아쓰기 진행상황")
//    @GetMapping("/{memSn}")
//    public ResponseEntity<?> checkProgress(@PathVariable Long memSn){
////        progressService.getDictationProgress(memSn);
//
//    }
}
