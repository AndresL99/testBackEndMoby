package com.alerner.testBackEnd.domain.converter;

import com.alerner.testBackEnd.domain.CandidateForTechnology;
import com.alerner.testBackEnd.domain.dto.TechnologyExperienceDto;

public class TechnologyExperienceDtoToCft
{
    public static TechnologyExperienceDto convert(CandidateForTechnology candidateForTechnology)
    {
        return TechnologyExperienceDto.builder()
                .name(candidateForTechnology.getTechnology().getName())
                .version(candidateForTechnology.getTechnology().getVersion())
                .yearExperience(candidateForTechnology.getYearsOfExperience())
                .build();
    }
}
