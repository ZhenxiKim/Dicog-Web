package com.example.diccogweb.controller;

import com.example.diccogweb.controller.dto.MembersRequestDto;
import com.example.diccogweb.exception.PasswordNotMatchException;
import com.example.diccogweb.model.Members;
import com.example.diccogweb.model.responseDto.MembersResponseDto;
import com.example.diccogweb.service.MembersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/members", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class MembersController {

    private final MembersService membersService;

    public MembersController(MembersService membersService) {
        this.membersService = membersService;
    }

    @ApiOperation(value = "회원가입")
    @PostMapping()
    public MembersResponseDto createMember(@RequestBody MembersRequestDto membersRequestDto) {
        membersService.signUpMember(membersRequestDto);
        MembersResponseDto membersResponseDto = new MembersResponseDto(membersRequestDto);
        return membersResponseDto;
    }

    @ApiOperation(value = "회원존재여부확인")
    @PostMapping("/idCheck")
    public String checkId(@RequestBody MembersRequestDto membersRequestDto) {
        boolean result = membersService.isExistMemberId(membersRequestDto.getMemId());
        //TODO restapi 방식이 아니다 이건!
        //FIXME restapi code로 변경해야함.
        return result ? "회원이미존재" : "회원 존재하지 않";
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody MembersRequestDto membersRequestDto) {
        try {
            //TODO login api return값 정의 필요
            //TODO login api controller분리
            Members loginMember = membersService.signIn(membersRequestDto);
            return ResponseEntity.ok(loginMember);
        } catch (PasswordNotMatchException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
