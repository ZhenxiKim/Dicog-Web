package com.example.diccogweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="points")
public class Points {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointId;
    private int pointNum;

    @ManyToOne
    @JoinColumn(name = "memSn", referencedColumnName = "memSn")
    private Members members;

    @ManyToOne
    @JoinColumn(name = "gradeId", referencedColumnName = "gradeId")
    private Grade grade;


}
