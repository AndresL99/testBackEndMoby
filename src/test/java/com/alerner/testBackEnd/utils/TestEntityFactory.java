package com.alerner.testBackEnd.utils;

import com.alerner.testBackEnd.domain.Candidate;
import com.alerner.testBackEnd.domain.CandidateForTechnology;
import com.alerner.testBackEnd.domain.Technology;
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
}
