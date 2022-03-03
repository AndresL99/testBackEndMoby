package com.alerner.testBackEnd.service;

import com.alerner.testBackEnd.domain.CandidateForTechnology;
import com.alerner.testBackEnd.repository.CandidateForTechnologyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.alerner.testBackEnd.utils.TestEntityFactory.getCandidate;
import static com.alerner.testBackEnd.utils.TestEntityFactory.getCandidateForTechnology;
import static com.alerner.testBackEnd.utils.TestEntityFactory.getCandidateForTechnologyList;
import static com.alerner.testBackEnd.utils.TestEntityFactory.getTechnology;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CandidateForTechnologyServiceTest
{
    private CandidateForTechnologyService candidateForTechnologyService;
    private CandidateForTechnologyRepository candidateForTechnologyRepository;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
        candidateForTechnologyRepository = mock(CandidateForTechnologyRepository.class);
        candidateForTechnologyService = new CandidateForTechnologyService(candidateForTechnologyRepository);
    }

    @Test
    void getCandidateForTechnologyByNameOfTechnologyTest()
    {
        when(candidateForTechnologyRepository.findByTechnologyName("Java")).thenReturn(getCandidateForTechnologyList());

        List<CandidateForTechnology>candidateForTechnologies = candidateForTechnologyService.getCandidateForTechnologyByNameOfTechnology("Java");

        assertEquals(getCandidateForTechnologyList().size(),candidateForTechnologies.size());
    }

    @Test
    void getTechnologyForTechnologyByTechnologyTest()
    {
        when(candidateForTechnologyRepository.findByTechnology(getTechnology())).thenReturn(getCandidateForTechnologyList());

        List<CandidateForTechnology>candidateForTechnologies = candidateForTechnologyService.getTechnologyForTechnologyByTechnology(getTechnology());

        assertEquals(getCandidateForTechnologyList().size(),candidateForTechnologies.size());
    }

    @Test
    void getCandidateForTechnologyByCandidateTest()
    {
        when(candidateForTechnologyRepository.findByCandidate(getCandidate())).thenReturn(getCandidateForTechnologyList());

        List<CandidateForTechnology>candidateForTechnologies = candidateForTechnologyService.getCandidateForTechnologyByCandidate(getCandidate());

        assertEquals(getCandidateForTechnologyList().size(),candidateForTechnologies.size());
    }

    @Test
    void addCandidateForTechnologyTestOk()
    {
        when(candidateForTechnologyRepository.findByCandidateAndTechnology(getCandidate(),getTechnology())).thenReturn(getCandidateForTechnology());

        candidateForTechnologyService.addCandidateForTechnology(getCandidate(),getTechnology(),2);

        verify(candidateForTechnologyRepository,times(1)).findByCandidateAndTechnology(getCandidate(),getTechnology());
    }

    @Test
    void getTechnologyExperienceByCandidate()
    {

    }
}
