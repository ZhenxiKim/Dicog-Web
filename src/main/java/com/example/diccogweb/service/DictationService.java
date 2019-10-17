package com.example.diccogweb.service;

import com.example.diccogweb.controller.dto.Answer;
import com.example.diccogweb.controller.dto.AnswerRequestDto;
import com.example.diccogweb.exception.DataNotFoundException;
import com.example.diccogweb.model.Dictation;
import com.example.diccogweb.model.Grade;
import com.example.diccogweb.model.Members;
import com.example.diccogweb.model.responseDto.AnswerResult;
import com.example.diccogweb.model.responseDto.DictationResponseDto;
import com.example.diccogweb.repository.DictationRepository;
import com.example.diccogweb.repository.GradeRepository;
import com.example.diccogweb.repository.MembersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DictationService {

    private DictationRepository dictationRepository;
    private GradeRepository gradeRepository;
    private MembersRepository membersRepository;

    public DictationService(DictationRepository dictationRepository, GradeRepository gradeRepository, MembersRepository membersRepository) {
        this.dictationRepository = dictationRepository;
        this.gradeRepository = gradeRepository;
        this.membersRepository = membersRepository;
    }

    public DictationResponseDto getDictationContents(@NotBlank String category, String step, int levelNum) {
        List<Dictation> dictationList = dictationRepository.findAllByCategoryAndStepAndLevelNum(category, step, levelNum);
        //TODO 찾아온 데이터가 존재하지 않을경우 exception 처리

//        List randomList = new ArrayList();
        DictationResponseDto dictationResponseDto = new DictationResponseDto();
//        for (int i = 0; i < 5; i++) {
//            double value = Math.random();
//            int randomNum = (int) (value * 10) + 1;
//            randomList.add(randomNum);
//        }
        dictationResponseDto.setDictationList(dictationList);
  //      dictationResponseDto.setRandomIntList(randomList);

        return dictationResponseDto;
    }

    public List<AnswerResult> checkDictationAnswer(AnswerRequestDto answerRequestDto) throws DataNotFoundException {
        //정답 확인
        Members members = membersRepository.findByMemSn(answerRequestDto.getMemSn());//받아쓰기한 회원
        Boolean answerResult;//정답결과
        List<Answer> answerData = answerRequestDto.getAnswerData();
        Long gradeId = 0L;

        for (int i = 0; i < answerData.size(); i++) {

            Long dicId = answerData.get(i).getDicId();
            String answer = answerData.get(i).getAnswer();
            Dictation dictation = dictationRepository.findByDicId(dicId);

            if (answer.equals(dictation.getContents())) {
                //정답
                answerResult = true;

            } else {
                //오답
                answerResult = false;
            }
            //정답확인후 grade 테이블에 기록
            Grade gradeResult = gradeRepository.save(new Grade(dictation, members, answerResult));
             gradeId = gradeResult.getGradeId();
        }
        //정답결과 보내기
        //받아쓰기 결과 조회
        //객체에 받아쓰기 결과 담기
        //동물,초급,1
        List<AnswerResult> answerResultList = (List<AnswerResult>) gradeRepository.findById(gradeId).orElseThrow(DataNotFoundException::new);
        return answerResultList;
    }
}
