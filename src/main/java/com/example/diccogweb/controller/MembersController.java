package com.example.diccogweb.controller;

import com.example.diccogweb.model.requestDto.MembersRequestDto;
import com.example.diccogweb.model.responseDto.MembersResponseDto;
import com.example.diccogweb.service.MembersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class MembersController {

    private final MembersService membersService;

    public MembersController(MembersService membersService) {
        this.membersService = membersService;
    }

    @ApiOperation(value="회원가입")
    @PostMapping("/signUp")
    public MembersResponseDto createMember(@RequestBody MembersRequestDto membersRequestDto) {
        membersService.signUp(membersRequestDto);
        MembersResponseDto membersResponseDto = new MembersResponseDto(membersRequestDto);
        return membersResponseDto;
    }

    @ApiOperation(value="회원존재여부확인")
    @PostMapping("/idCheck")
    public String checkId(@RequestBody MembersRequestDto membersRequestDto) {
        String result = membersService.checkId(membersRequestDto);
        return result;
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/signIn")
    public MembersResponseDto signIn(@RequestBody MembersRequestDto membersRequestDto){
        MembersResponseDto membersResponseDto = new MembersResponseDto(membersRequestDto);
        membersService.signIn(membersRequestDto);
        //message입력
        membersResponseDto.setMessage(membersService.signIn(membersRequestDto));
        return membersResponseDto;
    }

}
