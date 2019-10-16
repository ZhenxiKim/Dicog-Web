package com.example.diccogweb.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInMemberRequestDto {
    private String memId;
    private String memPwd;
}
