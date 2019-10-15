package com.example.diccogweb.model;

import com.example.diccogweb.model.requestDto.MembersRequestDto;
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
    private int memPoint;

    //signUp
    public Members(MembersRequestDto membersRequestDto) {
        this.memId = membersRequestDto.getMemId();
        this.memName = membersRequestDto.getMemName();
        this.memPassword = membersRequestDto.getMemPwd();
        this.memAge = membersRequestDto.getMemAge();
        this.memRegisterDate = LocalDateTime.now();
    }
}
