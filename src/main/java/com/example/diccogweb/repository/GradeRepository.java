package com.example.diccogweb.repository;

import com.example.diccogweb.model.Grade;
import com.example.diccogweb.model.responseDto.AnswerResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade,Long> {
    Optional<Grade> findById(Long gradeId);

}
