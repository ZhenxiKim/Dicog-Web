package com.example.diccogweb.service;

import com.example.diccogweb.controller.dto.SignUpRequestDto;
import com.example.diccogweb.exception.DataNotFoundException;
import com.example.diccogweb.model.Members;
import com.example.diccogweb.repository.MembersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Service
@Transactional
@Validated
public class MembersService {

    private final MembersRepository membersRepository;

    public MembersService(MembersRepository membersRepository) {
        this.membersRepository = membersRepository;
    }

    public void signUpMember(SignUpRequestDto signUpRequestDto) {
        Members members = new Members(signUpRequestDto);
        membersRepository.save(members);
    }

    public Members isExistMemberId(@NotBlank String memId) throws DataNotFoundException {
        //@NotBlank 공백 및 null 체크
        //회원이 이미 존재하는지 db 조회
        Members members = membersRepository.findByMemId(memId)
                .orElseThrow(DataNotFoundException::new);
        return members;
    }

}
