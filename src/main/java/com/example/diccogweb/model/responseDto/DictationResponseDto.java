package com.example.diccogweb.model.responseDto;

import com.example.diccogweb.model.Dictation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictationResponseDto {
    private List<Dictation> dictationList;
}
