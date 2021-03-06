package com.alerner.testBackEnd.service;


import com.alerner.testBackEnd.domain.Candidate;
import com.alerner.testBackEnd.domain.CandidateForTechnology;
import com.alerner.testBackEnd.domain.Technology;
import com.alerner.testBackEnd.domain.dto.CandidateDto;
import com.alerner.testBackEnd.domain.dto.TechnologyExperienceDto;
import com.alerner.testBackEnd.exception.CandidateExistException;
import com.alerner.testBackEnd.exception.CandidateForTechnologyExistException;
import com.alerner.testBackEnd.exception.CandidateNotExistException;
import com.alerner.testBackEnd.exception.TechnologyExistException;
import com.alerner.testBackEnd.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.alerner.testBackEnd.domain.converter.CandidateToDtoAndTechnologies.convertCandidateAndTechnology;

@Service
public class CandidateService
{
    @Autowired
    private CandidateRepository candidateRepository;
    private CandidateForTechnologyService candidateForTechnologyService;
    private TechnologyService technologyService;

    public CandidateService(CandidateRepository candidateRepository, CandidateForTechnologyService candidateForTechnologyService, TechnologyService technologyService) {
        this.candidateRepository = candidateRepository;
        this.candidateForTechnologyService = candidateForTechnologyService;
        this.technologyService = technologyService;
    }

    public Candidate addCandidate(Candidate source)throws CandidateExistException
    {
        if(candidateRepository.existsById(source.getIdCandidate()))
        {
            throw new CandidateExistException("This Candidate is already exist");
        }
        else
        {
            return candidateRepository.save(source);
        }
    }

    public Candidate getCandidateById(Long idCandidate)throws CandidateNotExistException
    {
        return candidateRepository.findById(idCandidate).orElseThrow(() -> new CandidateNotExistException("This candidate not exist"));
    }

    public List<Candidate>getAllCandidate()
    {
        return candidateRepository.findAll();
    }

    public void deleteCandidateById(Long idCandidate)
    {
        candidateRepository.deleteById(idCandidate);
    }

    public void updateCandidate(Long idCandidate, Candidate source)
    {
        Optional<Candidate>candidate = candidateRepository.findById(idCandidate);
        source.setIdCandidate(candidate.get().getIdCandidate());
        candidateRepository.save(source);
    }

    public Candidate addTechnologyAndCandidate(Long idCandidate, Long idTechnology, Integer yearExperience)throws CandidateExistException, TechnologyExistException, CandidateForTechnologyExistException
    {
        Candidate candidate = getCandidateById(idCandidate);
        Technology technology = technologyService.getTechnologyById(idTechnology);
        candidateForTechnologyService.addCandidateForTechnology(candidate,technology,yearExperience);
        return candidate;
    }

    public List<CandidateDto>getNameOfTechnology(String name)
    {
        List<CandidateDto>candidates = new ArrayList<>();
        List<CandidateForTechnology>candidateForTechnologies = candidateForTechnologyService.getCandidateForTechnologyByNameOfTechnology(name);

        for(CandidateForTechnology candidateForTechnology : candidateForTechnologies)
        {
            List<TechnologyExperienceDto>dtoList = new ArrayList<>();
            for(TechnologyExperienceDto technologyExperienceDto : candidateForTechnologyService.getTechnologyExperienceByCandidate(candidateForTechnology.getCandidate()))
            {
                if(technologyExperienceDto.getName().equalsIgnoreCase(name)) {
                    dtoList.add(technologyExperienceDto);
                }
            }
            candidates.add(convertCandidateAndTechnology(candidateForTechnology.getCandidate(),dtoList));
        }
        return candidates;
    }

}
