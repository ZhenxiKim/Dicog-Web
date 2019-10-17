package com.example.diccogweb.controller;

import com.example.diccogweb.controller.dto.CheckRequestDto;
import com.example.diccogweb.controller.dto.SignUpRequestDto;
import com.example.diccogweb.exception.DataNotFoundException;
import com.example.diccogweb.exception.MemberNotExistException;
import com.example.diccogweb.model.Members;
import com.example.diccogweb.model.responseDto.MemberInfoResponseDto;
import com.example.diccogweb.service.MembersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/members", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class MembersController {

    private final MembersService membersService;

    public MembersController(MembersService membersService) {
        this.membersService = membersService;
    }

    @ApiOperation(value = "회원가입")
    @PostMapping()
    public ResponseEntity<?> createMember(@RequestBody SignUpRequestDto signUpRequestDto) {
        membersService.signUpMember(signUpRequestDto);
        return ResponseEntity.ok(signUpRequestDto);
    }

    @ApiOperation(value = "회원존재여부확인")
    @GetMapping("")
    public ResponseEntity<?> isExistMemberId(@RequestBody CheckRequestDto checkRequestDto) {

        //request로 들어온 멤버아이디 정보 조회
        try {
            Members member = membersService.isExistMemberId(checkRequestDto.getMemId());
            return ResponseEntity.ok(member);
        }catch(DataNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @ApiOperation(value="마이페이지 정보 조회")
    @GetMapping("/{memSn}/membersinfo")
    public ResponseEntity<?> getMembersInfo(@PathVariable Long memSn ) throws DataNotFoundException {
        return ResponseEntity.ok(membersService.getMemberInfo(memSn));
    }

}
