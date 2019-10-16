package com.example.diccogweb.repository;

import com.example.diccogweb.model.Dictation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DictationRepository extends JpaRepository<Dictation,Long>{
        List<Dictation> findAllByCategoryAndStepAndLevelNum(String category, String step, int levelNum);
        Dictation findByDicId(Long dicId);


}
