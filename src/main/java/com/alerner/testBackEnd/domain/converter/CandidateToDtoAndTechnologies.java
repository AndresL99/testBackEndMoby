package com.alerner.testBackEnd.domain.converter;

import com.alerner.testBackEnd.domain.Candidate;
import com.alerner.testBackEnd.domain.dto.CandidateDto;
import com.alerner.testBackEnd.domain.dto.TechnologyExperienceDto;

import java.util.List;

public class CandidateToDtoAndTechnologies
{
    public static CandidateDto convertCandidateAndTechnology(Candidate c, List<TechnologyExperienceDto> technologyExperienceDto)
    {
        return CandidateDto.builder()
                .firstName(c.getFirstName())
                .lastName(c.getLastName())
                .document(c.getDocument())
                .typeOfDocument(c.getTypeOfDocument())
                .dateOfBirth(c.getDateOfBirth())
                .technologyExperienceDtos(technologyExperienceDto)
                .build();
    }
}
