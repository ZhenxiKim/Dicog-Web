package com.example.diccogweb.service;

import com.example.diccogweb.exception.PasswordNotMatchException;
import com.example.diccogweb.model.Members;
import com.example.diccogweb.controller.dto.MembersRequestDto;
import com.example.diccogweb.repository.MembersRepository;
import com.sun.istack.NotNull;
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

    public void signUpMember(MembersRequestDto membersRequestDto) {
        Members members = new Members(membersRequestDto);
        membersRepository.save(members);
    }

    public boolean isExistMemberId(@NotBlank String memId) {
        Members members = membersRepository.findByMemId(memId);
        if (members.getMemId() == memId) {
            //true :회원존재
            return true;
        } else {
            //false :회원가입가능
            return false;
        }
    }


    public Members signIn(MembersRequestDto membersRequestDto) throws PasswordNotMatchException {
        Members members = membersRepository.findByMemId(membersRequestDto.getMemId());
        //기존에 저장된 비밀번호와 입력된 비밀번호 비교
        if (membersRequestDto.getMemPwd().equals(members.getMemPassword())) {

            return members;
        } else {
            throw new PasswordNotMatchException();
        }
    }
}
