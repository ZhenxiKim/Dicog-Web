package com.example.diccogweb.model;

import com.example.diccogweb.controller.dto.SignUpRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "members")
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memSn;
    @Column(nullable = false, length = 20, unique = true)
    private String memId;
    private String memName;
    private String memPassword;
    private LocalDateTime memRegisterDate;
    private int memAge;

    //signUpMember
    public Members(SignUpRequestDto signUpRequestDto) {
        this.memId = signUpRequestDto.getMemId();
        this.memName = signUpRequestDto.getMemName();
        this.memPassword = signUpRequestDto.getMemPwd();
        this.memAge = signUpRequestDto.getMemAge();
        this.memRegisterDate = LocalDateTime.now();
    }
}
