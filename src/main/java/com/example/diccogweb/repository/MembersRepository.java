package com.example.diccogweb.repository;

import com.example.diccogweb.model.Members;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembersRepository extends JpaRepository<Members,Long> {
    Optional<Members> findByMemId(String memId);
    Optional<Members> findById(Long memSn);
    Members findByMemSn(Long memSn);
}
