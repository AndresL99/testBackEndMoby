package com.alerner.testBackEnd.controller;

import com.alerner.testBackEnd.domain.Candidate;
import com.alerner.testBackEnd.domain.Technology;
import com.alerner.testBackEnd.exception.CandidateExistException;
import com.alerner.testBackEnd.exception.CandidateForTechnologyExistException;
import com.alerner.testBackEnd.exception.CandidateNotExistException;
import com.alerner.testBackEnd.exception.TechnologyExistException;
import com.alerner.testBackEnd.service.CandidateService;
import com.alerner.testBackEnd.service.TechnologyService;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/candidate")
@Log4j2
public class CandidateController
{

    private CandidateService candidateService;
    private TechnologyService technologyService;

    public CandidateController(CandidateService candidateService, TechnologyService technologyService) {
        this.candidateService = candidateService;
        this.technologyService = technologyService;
    }

    @PostMapping
    public ResponseEntity<Candidate>addCandidate(@RequestBody Candidate candidate)
    {
        try
        {
            return new ResponseEntity<>(candidateService.addCandidate(candidate), HttpStatus.CREATED);
        }
        catch (CandidateExistException ex)
        {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{idCandidate}")
    public ResponseEntity<Candidate>getCandidateById(@PathVariable Long idCandidate)
    {
        try
        {
            return ResponseEntity.ok(candidateService.getCandidateById(idCandidate));
        }
        catch (CandidateNotExistException e)
        {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping()
    public ResponseEntity<List<Candidate>> getAllCandidate()
    {
        return ResponseEntity.ok(candidateService.getAllCandidate());
    }

    @DeleteMapping("/{idCandidate}")
    public void deleteCandidateById(@PathVariable Long idCandidate)
    {
        candidateService.deleteCandidateById(idCandidate);
    }

    @PutMapping("/{idCandidate}")
    public void updateCandidate(@PathVariable Long idCandidate,@RequestBody Candidate candidate)
    {
        candidateService.updateCandidate(idCandidate,candidate);
    }

    @PutMapping("/{idCandidate}/technologies/{idTechnology}")
    public ResponseEntity<Candidate>addTechnologyAndCandidate(@PathVariable Long idCandidate, @PathVariable Long idTechnology, @RequestParam Integer yearsOfExperience)
    {
        try
        {
            return new ResponseEntity<Candidate>(candidateService.addTechnologyAndCandidate(idCandidate,idTechnology,yearsOfExperience),HttpStatus.OK);
        }
        catch (CandidateForTechnologyExistException | TechnologyExistException | CandidateExistException exception)
        {
            log.error(exception.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/technologies/{name}")
    public ResponseEntity<List<Candidate>>getTechnologyByName(@PathVariable String name)
    {
        return ResponseEntity.ok(candidateService.getNameOfTechnology(name));
    }
}
