package com.example.diccogweb.repository;

import com.example.diccogweb.model.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembersRepository extends JpaRepository<Members,Long> {
    Members findByMemId(String memId);
}
