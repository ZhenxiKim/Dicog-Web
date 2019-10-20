package com.example.diccogweb.service;

import com.example.diccogweb.exception.DataNotFoundException;
import com.example.diccogweb.model.Grade;
import com.example.diccogweb.model.Members;
import com.example.diccogweb.model.dto.Progress;
import com.example.diccogweb.repository.DictationRepository;
import com.example.diccogweb.repository.GradeRepository;
import com.example.diccogweb.repository.MembersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProgressService {

    private final MembersRepository membersRepository;
    private final DictationRepository dictationRepository;
    private final GradeRepository gradeRepository;

    public ProgressService(MembersRepository membersRepository, DictationRepository dictationRepository, GradeRepository gradeRepository) {
        this.membersRepository = membersRepository;
        this.dictationRepository = dictationRepository;
        this.gradeRepository = gradeRepository;
    }

    public List<Progress> getDictationProgress(@NotNull Members members) throws DataNotFoundException {

        int lowCount = 0;
        int middleCount = 0;
        int highCount = 0;
        //글로벌 변수로 선언 시 서비스 호출로 인해 계속 초기화 될 수있음.


        //회원 받아쓰기 성적 가져오기
        List<Grade> gradeList = gradeRepository.findAllByMembers(members);
        List<Progress> progressList = new ArrayList();

        for (Grade grade : gradeList) {

            Long dictationId = grade.getDictation().getDicId();

            //회원 받아쓰기 성적 row로 dictation row조회
            String step = dictationRepository.findById(dictationId).orElseThrow(DataNotFoundException::new).getStep();

            //dictation row의 Step
            if ("초급".equals(step)) {//step이 null일경우 대
                lowCount += 1;
            } else if ("중급".equals(step)) {
                middleCount += 1;
            } else if ("고급".equals(step)) {
                highCount += 1;
            }
        }

        //퍼센트값으로 변환
        progressList.add(new Progress("초급",(int) (((double) lowCount / 10) * 100)));
        progressList.add(new Progress("중급",(int) (((double) middleCount / 10) * 100)));
        progressList.add(new Progress("고급",(int) (((double) highCount / 10) * 100)));

        return progressList;
    }


}
