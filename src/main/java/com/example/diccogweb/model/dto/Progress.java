package com.example.diccogweb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NotNull
public class Progress {
    private String step;
    private int percent;
}
