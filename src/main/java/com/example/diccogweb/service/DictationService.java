package com.example.diccogweb.service;

import com.example.diccogweb.controller.dto.Answer;
import com.example.diccogweb.controller.dto.AnswerDto;
import com.example.diccogweb.controller.dto.AnswerRequestDto;
import com.example.diccogweb.exception.DataNotFoundException;
import com.example.diccogweb.model.Dictation;
import com.example.diccogweb.controller.dto.DictationRequestDto;
import com.example.diccogweb.controller.dto.DictationExcelDto;
import com.example.diccogweb.model.Grade;
import com.example.diccogweb.model.Members;
import com.example.diccogweb.model.responseDto.DictationResponseDto;
import com.example.diccogweb.repository.DictationRepository;
import com.example.diccogweb.repository.GradeRepository;
import com.example.diccogweb.repository.MembersRepository;
import com.sun.istack.NotNull;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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


    public void readExcelFile() throws IOException {
        DictationExcelDto dictationDto = new DictationExcelDto();
        List<DictationExcelDto> list = new ArrayList<DictationExcelDto>();


        try {
            FileInputStream file = new FileInputStream("C:/Users/user/Downloads/inputexcel.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            int rowindex = 0;
            int columnindex = 0;
            //시트 수 (첫번째에만 존재하므로 0을 준다)
            //만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
            XSSFSheet sheet = workbook.getSheetAt(0);
            //행의 수
            int rows = sheet.getPhysicalNumberOfRows();

            for (rowindex = 0; rowindex < rows; rowindex++) {
                //행을읽는다
                XSSFRow row = sheet.getRow(rowindex);

                if (row != null) {

                    dictationDto.setStep(row.getCell(0).getStringCellValue());
                    dictationDto.setCategory(row.getCell(1).getStringCellValue());
                    dictationDto.setLevelNum((int) row.getCell(2).getNumericCellValue());
                    dictationDto.setContents(row.getCell(3).getStringCellValue());

                }
                list.add(dictationDto);
                dictationRepository.save(new Dictation(dictationDto));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public DictationResponseDto getDictationContents(@NotBlank String category, String step, int levelNum) {
        List<Dictation> dictationList = dictationRepository.findAllByCategoryAndStepAndLevelNum(category, step, levelNum);
        //TODO 찾아온 데이터가 존재하지 않을경우 exception 처리

        List randomList = new ArrayList();
        DictationResponseDto dictationResponseDto = new DictationResponseDto();
        for (int i = 0; i < 5; i++) {
            double value = Math.random();
            int randomNum = (int) (value * 10) + 1;
            randomList.add(randomNum);
        }
        dictationResponseDto.setDictationList(dictationList);
        dictationResponseDto.setRandomIntList(randomList);

        return dictationResponseDto;
    }

    public void checkDictationAnswer(AnswerRequestDto answerRequestDto) {
        //정답 확인
        Members members = membersRepository.findByMemSn(answerRequestDto.getMemSn());//받아쓰기한 회원
        Boolean answerResult;//정답결과
        List<Answer> answerData = answerRequestDto.getAnswerData();

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
            gradeRepository.save(new Grade(dictation, members, answerResult));
        }


    }
}
