package com.example.diccogweb.model.dto;

import com.example.diccogweb.controller.dto.MembersRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembersResponseDto {
    private String memId;
    private String memPwd;
    private String memName;
    private int memAge;
    private String message;


    public MembersResponseDto(MembersRequestDto membersRequestDto) {
        this.memId = membersRequestDto.getMemId();
        this.memPwd = membersRequestDto.getMemPwd();
        this.memName = membersRequestDto.getMemName();
        this.memAge = membersRequestDto.getMemAge();

    }
}
