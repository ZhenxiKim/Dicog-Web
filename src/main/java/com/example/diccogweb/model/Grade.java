package com.example.diccogweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Data
@Entity//엔티티 클래스임을 지정하며 테이블과 매핑
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "grade")//엔티티가 매핑될 테이블을 지정하고 생략 시 엔티티 클래스 명과 동일한 테이블로 제
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeId;
    //@Convert(converter = BooleanToYNConverter.class)
    private boolean gradeCheck;
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "memSn", referencedColumnName = "memSn")
    private Members members;

    @ManyToOne
    @JoinColumn(name = "dicId", referencedColumnName = "dicId")
    private Dictation dictation;


    public Grade(Dictation dictation, Members members, Boolean answerResult) {
        //TODO optional객체로 or 일반 객체? null체크
        this.members = members;
        this.dictation = dictation;
        this.gradeCheck = answerResult;
        this.localDateTime = LocalDateTime.now();
    }

    public boolean isGradeCheck() {
        return gradeCheck;
    }

    public void setGradeCheck(boolean gradeCheck) {
        this.gradeCheck = gradeCheck;
    }


}
