package com.alerner.testBackEnd.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechnologyExperienceDto
{
    private String name;
    private String version;
    private Integer yearExperience;
}
