package com.alerner.testBackEnd.utils;

import com.alerner.testBackEnd.domain.Candidate;
import com.alerner.testBackEnd.domain.CandidateForTechnology;
import com.alerner.testBackEnd.domain.Technology;
import com.alerner.testBackEnd.domain.User;
import com.alerner.testBackEnd.domain.dto.CandidateDto;
import com.alerner.testBackEnd.domain.dto.TechnologyExperienceDto;
import com.alerner.testBackEnd.domain.enums.TypeOfDocument;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestEntityFactory
{
    public static Candidate getCandidate()
    {
        return Candidate.builder()
                .idCandidate(1L)
                .firstName("Andres")
                .lastName("Lerner")
                .typeOfDocument(TypeOfDocument.DNI)
                .document("41555666")
                .dateOfBirth(new Date())
                .build();
    }

    public static Technology getTechnology()
    {
        return Technology.builder()
                .idTechnology(1L)
                .name("Java")
                .version("V15.0")
                .build();
    }

    public static CandidateForTechnology getCandidateForTechnology()
    {
        return CandidateForTechnology.builder()
                .idCandidateForTechnology(1L)
                .candidate(getCandidate())
                .technology(getTechnology())
                .yearsOfExperience(4)
                .build();
    }

    public static List<Candidate>getCandidateList()
    {
        List<Candidate>candidates = new ArrayList<>();
        candidates.add(getCandidate());
        return candidates;
    }

    public static List<Technology>getTechnologyList()
    {
        List<Technology>technologies = new ArrayList<>();
        technologies.add(getTechnology());
        return technologies;
    }

    public static List<CandidateForTechnology>getCandidateForTechnologyList()
    {
        List<CandidateForTechnology>candidateForTechnologies = new ArrayList<>();
        candidateForTechnologies.add(getCandidateForTechnology());
        return candidateForTechnologies;
    }

    public static User getUser()
    {
        return User.builder().idUser(1L).email("andres@gmail.com").username("andres99").password("andres123").build();
    }

    public static List<User>getUserList()
    {
        List<User>users = new ArrayList<>();
        users.add(getUser());
        return users;
    }

    public static TechnologyExperienceDto getTechnologyExperienceDto()
    {
        return TechnologyExperienceDto.builder()
                .name("Java")
                .version("v15.0")
                .yearExperience(2)
                .build();
    }

    public static List<TechnologyExperienceDto>getListTechnologyExperienceDto()
    {
        List<TechnologyExperienceDto>dtoList = new ArrayList<>();
        dtoList.add(getTechnologyExperienceDto());
        return dtoList;
    }

    public static CandidateDto getCandidateDto()
    {
        return CandidateDto.builder()
                .firstName("Andres")
                .lastName("Lerner")
                .document("41555666")
                .typeOfDocument(TypeOfDocument.DNI)
                .dateOfBirth(new Date())
                .technologyExperienceDtos(getListTechnologyExperienceDto())
                .build();
    }

    public static List<CandidateDto>getListCandidateDto()
    {
        List<CandidateDto>candidateDtos = new ArrayList<>();
        candidateDtos.add(getCandidateDto());
        return candidateDtos;
    }

}
