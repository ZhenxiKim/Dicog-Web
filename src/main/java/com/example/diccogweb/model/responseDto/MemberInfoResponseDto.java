package com.example.diccogweb.model.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoResponseDto {

    private String memId;
    private String memName;
    private LocalDateTime regDate;
    private int memPoint;
    private int memAge;
}
