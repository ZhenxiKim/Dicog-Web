package com.example.diccogweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="dictation")
public class Dictation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dicId;
    private String dicName;
    private String category;
    private String step;
    private String contents;

    @OneToMany
    @JoinColumn(name="fileNo")
    private List<Files> files;
}
