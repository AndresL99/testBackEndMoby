package com.alerner.testBackEnd.service;

import com.alerner.testBackEnd.domain.Candidate;
import com.alerner.testBackEnd.domain.CandidateForTechnology;
import com.alerner.testBackEnd.domain.Technology;
import com.alerner.testBackEnd.domain.dto.TechnologyExperienceDto;
import com.alerner.testBackEnd.exception.CandidateForTechnologyExistException;
import com.alerner.testBackEnd.repository.CandidateForTechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.alerner.testBackEnd.domain.converter.TechnologyExperienceDtoToCft.convert;
import static java.util.Objects.isNull;

@Service
public class CandidateForTechnologyService
{
    @Autowired
    private CandidateForTechnologyRepository candidateForTechnologyRepository;

    public CandidateForTechnologyService(CandidateForTechnologyRepository candidateForTechnologyRepository) {
        this.candidateForTechnologyRepository = candidateForTechnologyRepository;
    }

    public void addCandidateForTechnology(Candidate candidate, Technology technology, Integer yearsOfExperience)throws CandidateForTechnologyExistException
    {
        CandidateForTechnology candidateForTechnology = candidateForTechnologyRepository.findByCandidateAndTechnology(candidate,technology);
        if(!isNull(candidateForTechnology))
        {
            throw new CandidateForTechnologyExistException("Already exist!");
        }
        else
        {
            candidateForTechnologyRepository.save(CandidateForTechnology.builder().candidate(candidate).technology(technology).yearsOfExperience(yearsOfExperience).build());
        }
    }

    public List<CandidateForTechnology> getCandidateForTechnologyByNameOfTechnology(String name)
    {
        return candidateForTechnologyRepository.findByTechnologyName(name);
    }

   public List<CandidateForTechnology> getTechnologyForTechnologyByTechnology(Technology technology)
   {
       return candidateForTechnologyRepository.findByTechnology(technology);
   }

   public List<CandidateForTechnology> getCandidateForTechnologyByCandidate(Candidate candidate)
   {
       return candidateForTechnologyRepository.findByCandidate(candidate);
   }

   public List<TechnologyExperienceDto>getTechnologyExperienceByCandidate(Candidate candidate)
   {
       List<TechnologyExperienceDto>technologyExperienceDtos = new ArrayList<>();
       for(CandidateForTechnology cft : candidateForTechnologyRepository.findByCandidate(candidate))
       {
           technologyExperienceDtos.add(convert(cft));
       }
       return technologyExperienceDtos;
   }

}
