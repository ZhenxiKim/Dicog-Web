package com.example.diccogweb.service;

import com.example.diccogweb.model.Members;
import com.example.diccogweb.model.requestDto.MembersRequestDto;
import com.example.diccogweb.repository.MembersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MembersService {

    private final MembersRepository membersRepository;

    public MembersService(MembersRepository membersRepository) {
        this.membersRepository = membersRepository;
    }


    public void signUp(MembersRequestDto membersRequestDto) {
        Members members = new Members(membersRequestDto);
        membersRepository.save(members);
    }

    public String checkId(MembersRequestDto membersRequestDto) {
        String newMemId = membersRequestDto.getMemId();

        Members members = membersRepository.findByMemId(newMemId);
        if (members.getMemId() == newMemId) {
            //true :회원존재
            return "이미 존재하는 회원";
        } else {
            //false :회원가입가능
            return "회원가입 가능";
        }
    }


    public String signIn(MembersRequestDto membersRequestDto) {
        Members members = membersRepository.findByMemId(membersRequestDto.getMemId());
        //기존에 저장된 비밀번호와 입력된 비밀번호 비교
        if(membersRequestDto.getMemPwd().equals(members.getMemPassword())){
            return "로그인 성공";
        }else{
            return "로그인 실패";
        }
    }
}
