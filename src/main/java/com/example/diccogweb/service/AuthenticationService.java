package com.example.diccogweb.service;

import com.example.diccogweb.controller.dto.AuthenticationRequestDto;
import com.example.diccogweb.exception.DataNotFoundException;
import com.example.diccogweb.model.Members;
import com.example.diccogweb.repository.MembersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthenticationService {
    private final MembersRepository membersRepository;
    public AuthenticationService(MembersRepository membersRepository){
        this.membersRepository = membersRepository;
    }

    public Members signInMember(AuthenticationRequestDto authenticationRequestDto) throws DataNotFoundException {
        Members members = membersRepository.findByMemId(authenticationRequestDto.getMemId()).orElseThrow(DataNotFoundException::new);
        //기존에 저장된 비밀번호와 입력된 비밀번호 비교
        if (authenticationRequestDto.getMemPwd().equals(members.getMemPassword())) {
            return members;
        } else {
            throw new DataNotFoundException();
        }
    }
}
