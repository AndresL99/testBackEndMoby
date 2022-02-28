package com.alerner.testBackEnd.service;


import com.alerner.testBackEnd.domain.Candidate;
import com.alerner.testBackEnd.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateService
{
    @Autowired
    private CandidateRepository candidateRepository;


    public Candidate addCandidate(Candidate source)
    {
        if(candidateRepository.existsById(source.getIdCandidate()))
        {
            throw new EntityExistsException();
        }
        else
        {
            return candidateRepository.save(source);
        }
    }

    public Candidate getCandidateById(Long idCandidate)
    {
        return candidateRepository.findById(idCandidate).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
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
}
