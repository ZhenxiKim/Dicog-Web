package com.example.diccogweb.repository;

import com.example.diccogweb.model.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<Files,Long> {

}
