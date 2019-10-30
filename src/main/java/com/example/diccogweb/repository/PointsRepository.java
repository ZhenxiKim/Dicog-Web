package com.example.diccogweb.repository;

import com.example.diccogweb.model.Members;
import com.example.diccogweb.model.Points;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointsRepository extends JpaRepository<Points,Long> {

//    @Query( "SELECT SUM(P.pointNum),P.members FROM Points P WHERE P.members LIKE %:memSn% GROUP BY  P.members")
//    List<Points> sumPoints(@Param("memSn") Long memSn);

    List<Points> findAllByMembersLike(Members members);
}
