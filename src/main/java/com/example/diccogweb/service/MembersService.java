package com.example.diccogweb.service;

import com.example.diccogweb.controller.dto.SignUpRequestDto;
import com.example.diccogweb.exception.DataNotFoundException;
import com.example.diccogweb.model.Members;
import com.example.diccogweb.model.Points;
import com.example.diccogweb.model.dto.MemberInfoResponseDto;
import com.example.diccogweb.repository.MembersRepository;
import com.example.diccogweb.repository.PointsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Service
@Transactional
@Validated
public class MembersService {

    private final MembersRepository membersRepository;
    private final PointsRepository pointsRepository;


    public MembersService(MembersRepository membersRepository,PointsRepository pointsRepository) {
        this.membersRepository = membersRepository;
        this.pointsRepository = pointsRepository;
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

    public MemberInfoResponseDto getMemberInfo(Long memSn) throws DataNotFoundException{
        Members members = membersRepository.findById(memSn).orElseThrow(DataNotFoundException::new);
        int totalPoint =0;
        //int totalPoint = pointsRepository.sumPoints(memSn);
        List<Points> pointList = pointsRepository.findAllByMembersLike(members);
        //List<Points> pointList = pointsRepository.sumPoints(memSn);

        for(int i=0;i<pointList.size();i++){
            totalPoint += pointList.get(i).getPointNum();
        }

        MemberInfoResponseDto memberInfoResponseDto = new MemberInfoResponseDto();

        memberInfoResponseDto.setMemId(members.getMemId());//회원아이디
        memberInfoResponseDto.setMemName(members.getMemName());//회원이름
        memberInfoResponseDto.setRegDate(members.getMemRegisterDate());//회원등록날짜
        memberInfoResponseDto.setMemAge(members.getMemAge());//회원나이
        memberInfoResponseDto.setMemPoint(totalPoint);//회원 누적 포인트

        return memberInfoResponseDto;
    }

}
