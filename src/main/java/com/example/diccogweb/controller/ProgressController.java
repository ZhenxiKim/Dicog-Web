//package com.example.diccogweb.controller;
//
//import com.example.diccogweb.exception.DataNotFoundException;
//import com.example.diccogweb.model.Members;
//import com.example.diccogweb.model.dto.Progress;
//import com.example.diccogweb.repository.MembersRepository;
//import com.example.diccogweb.service.ProgressService;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(value = "/progresses")
//public class ProgressController {
//    //맞춘거체크,진행상황체크
//    private final ProgressService progressService;
//    private final MembersRepository membersRepository;
//
//    public ProgressController(ProgressService progressService, MembersRepository membersRepository) {
//        this.progressService = progressService;
//        this.membersRepository = membersRepository;
//    }
//
//    @ApiOperation(value = "받아쓰기 진행상황")
//    @GetMapping("/{memSn}")
//    public ResponseEntity<?> checkProgress(@PathVariable Long memSn) {
//
//        try {
//            Members members = membersRepository.findById(memSn).orElseThrow(DataNotFoundException::new);
//            List<Progress> dictationProgress = progressService.getDictationProgress(members);
//            return ResponseEntity.ok(dictationProgress);
//
//        } catch (DataNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        }
//
//    }
//}
