package com.alerner.testBackEnd.controller;

import com.alerner.testBackEnd.domain.Candidate;
import com.alerner.testBackEnd.service.CandidateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/candidate")
public class CandidateController
{
    private CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping
    public ResponseEntity<Candidate>addCandidate(@RequestBody Candidate candidate)
    {
        return new ResponseEntity<>(candidateService.addCandidate(candidate), HttpStatus.CREATED);
    }

    @GetMapping("/{idCandidate}")
    public ResponseEntity<Candidate>getCandidateById(@PathVariable Long idCandidate)
    {
        return ResponseEntity.ok(candidateService.getCandidateById(idCandidate));
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
}
