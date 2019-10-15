package com.example.diccogweb.model.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembersRequestDto {
    private String memId;
    private String memPwd;
    private String memName;
    private int memAge;

}
