package com.example.diccogweb.service;

import com.example.diccogweb.model.Dictation;
import com.example.diccogweb.controller.dto.DicStartRequestDto;
import com.example.diccogweb.controller.dto.DictationExcelDto;
import com.example.diccogweb.repository.DictationRepository;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DictationService {
    @Autowired
    private DictationRepository dictationRepository;

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

    public void getInfo(DicStartRequestDto dicStartRequestDto) {
//        dictationRepository.findBy(dicStartRequestDto.getStep());
        dictationRepository.findById(dicStartRequestDto.getDicId());

    }
}
