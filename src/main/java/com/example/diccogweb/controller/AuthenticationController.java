package com.example.diccogweb.controller;

import com.example.diccogweb.controller.dto.AuthenticationRequestDto;
import com.example.diccogweb.controller.dto.MembersRequestDto;
import com.example.diccogweb.controller.dto.SignUpRequestDto;
import com.example.diccogweb.exception.DataNotFoundException;
import com.example.diccogweb.model.Members;
import com.example.diccogweb.service.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/authentications", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @ApiOperation(value = "로그인")
    @GetMapping()
    public ResponseEntity<?> signInMember(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        try {
            //TODO login api return값 정의 필요
            Members loginMember = authenticationService.signInMember(authenticationRequestDto);
            return ResponseEntity.ok(loginMember);
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
