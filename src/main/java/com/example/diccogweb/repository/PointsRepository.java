package com.example.diccogweb.repository;

import com.example.diccogweb.model.Members;
import com.example.diccogweb.model.Points;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointsRepository extends JpaRepository<Points,Long> {

//    @Query( "SELECT SUM(p.pointNum),P.members FROM Points p WHERE p.members = ?1 GROUP BY  p.members")
//    int sumPoints(@Param("memSn") Long memSn);

    List<Points> findAllByMembersLike(Members members);
}
