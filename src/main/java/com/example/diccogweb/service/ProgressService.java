package com.example.diccogweb.service;

import com.example.diccogweb.exception.DataNotFoundException;
import com.example.diccogweb.model.Dictation;
import com.example.diccogweb.model.Grade;
import com.example.diccogweb.model.Members;
import com.example.diccogweb.repository.DictationRepository;
import com.example.diccogweb.repository.GradeRepository;
import com.example.diccogweb.repository.MembersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProgressService {
    //전체문제수
    private final int low = 10;
    private final int middle = 10;
    private final int high = 10;

    int lowCount = 0;
    int middleCount = 0;
    int highCount = 0;

    private final MembersRepository membersRepository;
    private final DictationRepository dictationRepository;
    private final GradeRepository gradeRepository;

    public ProgressService(MembersRepository membersRepository, DictationRepository dictationRepository, GradeRepository gradeRepository) {
        this.membersRepository = membersRepository;
        this.dictationRepository = dictationRepository;
        this.gradeRepository = gradeRepository;
    }

    public void getDictationProgress(Long memSn) throws DataNotFoundException {
//        //Members members = membersRepository.findById(memSn).orElseThrow(DataNotFoundException::new);
//        //회원 받아쓰기 기록 가져오기
//        List<Grade> grade = gradeRepository.findByMemSn(memSn);
//        for (int i = 0; i < grade.size(); i++) {
//
//            Long dictationId = grade.get(i).getDictation().getDicId();
//
//            //회원 받아쓰기 성적row로 dictation row조회
//            String step = dictationRepository.findById(dictationId).get().getStep();
//
//            //dictation row의 Step
//            if (step == "초급") {
//
//
//                lowCount +=
//            } else if (step == "중급") {
//
//            } else {
//
//            }
//        }


    }
}
