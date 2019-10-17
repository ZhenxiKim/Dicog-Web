package com.example.diccogweb.service;

import com.example.diccogweb.controller.dto.Answer;
import com.example.diccogweb.controller.dto.AnswerRequestDto;
import com.example.diccogweb.exception.DataNotFoundException;
import com.example.diccogweb.model.Dictation;
import com.example.diccogweb.model.Grade;
import com.example.diccogweb.model.Members;
import com.example.diccogweb.model.Points;
import com.example.diccogweb.model.responseDto.AnswerResult;
import com.example.diccogweb.model.responseDto.DictationResponseDto;
import com.example.diccogweb.repository.DictationRepository;
import com.example.diccogweb.repository.GradeRepository;
import com.example.diccogweb.repository.MembersRepository;
import com.example.diccogweb.repository.PointsRepository;
import javassist.NotFoundException;
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
    private PointsRepository pointsRepository;

    public DictationService(DictationRepository dictationRepository, GradeRepository gradeRepository,
                            MembersRepository membersRepository,PointsRepository pointsRepository) {
        this.dictationRepository = dictationRepository;
        this.gradeRepository = gradeRepository;
        this.membersRepository = membersRepository;
        this.pointsRepository = pointsRepository;
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

    //리스트에 담는 작업
    public List<AnswerResult> checkDictationAnswer(AnswerRequestDto answerRequestDto) throws DataNotFoundException {
        //사용할 변수 초기화
        Long gradeId = 0L;
        int pointNum = 0;

        //정답 확인
        //TODO optional객체로 변경시 확인
        Members members = membersRepository.findById(answerRequestDto.getMemSn()).orElseThrow(DataNotFoundException::new);;//받아쓰기한 회원
        Boolean answerResult;//정답결과

        List<Answer> answerData = answerRequestDto.getAnswerData();//값을 체크하기위해 회원이 request한 데이터 가져오기기
        List<AnswerResult> answerResultList = new ArrayList<>();//성적결과를 담을 리스트

        //값을 하나씩 가져와서 db학습내용과 비교
        for (int i = 0; i < answerData.size(); i++) {

            Long dicId = answerData.get(i).getDicId();//문제번호
            String answer = answerData.get(i).getAnswer();//회원이 작성한 답
            Dictation dictation = dictationRepository.findByDicId(dicId);//문제번호를 바탕으로 학습내용 조회

            if (answer.equals(dictation.getContents())) {
                //정답
                answerResult = true;
                pointNum = 10;

            } else {
                //오답
                answerResult = false;
            }

            //정답확인후 grade 테이블에 성적 insert
            Grade gradeResult = gradeRepository.save(new Grade(dictation, members, answerResult));

            //작성된 성적테이블 gradeId값 가져오기
            gradeId = gradeResult.getGradeId();
            Grade grade = gradeRepository.findById(gradeId).orElseThrow(DataNotFoundException::new);
            AnswerResult result = new AnswerResult();

            result.setDicId(grade.getDictation().getDicId());
            result.setAnswer(grade.isGradeCheck());

            answerResultList.add(result);
            if(grade.isGradeCheck()){
                pointsRepository.save(new Points(pointNum,grade,members));
            }

        }

        return answerResultList;
    }

}
