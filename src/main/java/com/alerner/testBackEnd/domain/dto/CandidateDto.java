package com.alerner.testBackEnd.domain.dto;

import com.alerner.testBackEnd.domain.enums.TypeOfDocument;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDto
{
    String firstName;
    String lastName;
    String document;
    TypeOfDocument typeOfDocument;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", locale = "es-Arg", timezone = "America/Buenos Aires")
    Date dateOfBirth;

    List<TechnologyExperienceDto>technologyExperienceDtos;
}
