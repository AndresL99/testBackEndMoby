package com.alerner.testBackEnd.service;

import com.alerner.testBackEnd.domain.Candidate;
import com.alerner.testBackEnd.domain.dto.CandidateDto;
import com.alerner.testBackEnd.exception.CandidateExistException;
import com.alerner.testBackEnd.exception.CandidateNotExistException;
import com.alerner.testBackEnd.repository.CandidateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.alerner.testBackEnd.utils.TestEntityFactory.getCandidate;
import static com.alerner.testBackEnd.utils.TestEntityFactory.getCandidateForTechnologyList;
import static com.alerner.testBackEnd.utils.TestEntityFactory.getCandidateList;
import static com.alerner.testBackEnd.utils.TestEntityFactory.getTechnology;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CandidateServiceTest
{
    private CandidateService candidateService;
    private CandidateRepository candidateRepository;
    private CandidateForTechnologyService candidateForTechnologyService;
    private TechnologyService technologyService;

    @BeforeEach
    void setUp()
    {
        candidateRepository = mock(CandidateRepository.class);
        candidateForTechnologyService = mock(CandidateForTechnologyService.class);
        technologyService = mock(TechnologyService.class);
        candidateService = new CandidateService(candidateRepository,candidateForTechnologyService,technologyService);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addCandidateTestOk()
    {
        Long idCandidate = getCandidate().getIdCandidate();
        when(candidateRepository.existsById(idCandidate)).thenReturn(false);
        when(candidateRepository.save(getCandidate())).thenReturn(getCandidate());

        Candidate candidate = candidateService.addCandidate(getCandidate());

        assertEquals(getCandidate().getDocument(),candidate.getDocument());

    }

    @Test
    void addCandidateTestFail()
    {
        when(candidateRepository.existsById(getCandidate().getIdCandidate())).thenReturn(true);
        when(candidateRepository.save(getCandidate())).thenThrow(CandidateExistException.class);

        assertThrows(CandidateExistException.class,()->candidateService.addCandidate(getCandidate()));
    }

    @Test
    void getCandidateByIdTestOk()
    {
        Long idCandidate = getCandidate().getIdCandidate();
        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(getCandidate()));
        Candidate candidate = candidateService.getCandidateById(idCandidate);
        verify(candidateRepository,times(1)).findById(idCandidate);
    }

    @Test
    void getCandidateByIdTestFail()
    {
        when(candidateRepository.findById(4L)).thenThrow(CandidateNotExistException.class);
        assertThrows(CandidateNotExistException.class,()->candidateService.getCandidateById(4L));
    }

    @Test
    void getAllCandidateTest()
    {
        when(candidateRepository.findAll()).thenReturn(getCandidateList());
        List<Candidate>candidates = candidateService.getAllCandidate();

        assertEquals(getCandidateList().size(),candidates.size());

        verify(candidateRepository,times(1)).findAll();
    }

    @Test
    void updateCandidateTest()
    {
        when(candidateRepository.findById(getCandidate().getIdCandidate())).thenReturn(Optional.of(getCandidate()));
        when(candidateRepository.save(getCandidate())).thenReturn(getCandidate());

        candidateService.updateCandidate(getCandidate().getIdCandidate(),getCandidate());

        verify(candidateRepository,times(1)).findById(getCandidate().getIdCandidate());
    }

    @Test
    void deleteCandidateTest()
    {
        doNothing().when(candidateRepository).deleteById(getCandidate().getIdCandidate());

        candidateService.deleteCandidateById(getCandidate().getIdCandidate());

        verify(candidateRepository,times(1)).deleteById(getCandidate().getIdCandidate());
    }

    @Test
    void addTechnologyAndCandidateTest()
    {
        when(technologyService.getTechnologyById(getTechnology().getIdTechnology())).thenReturn(getTechnology());
        doNothing().when(candidateForTechnologyService).addCandidateForTechnology(getCandidate(),getTechnology(),2);

        Candidate candidate = candidateService.addTechnologyAndCandidate(getCandidate().getIdCandidate(), getTechnology().getIdTechnology(), 2);

        assertNotNull(candidate);
    }

    @Test
    void getNameOfTechnologyTest()
    {
        when(candidateForTechnologyService.getCandidateForTechnologyByNameOfTechnology("Java")).thenReturn(getCandidateForTechnologyList());
        when(candidateForTechnologyService.getTechnologyExperienceByCandidate(getCandidate()));

        List<CandidateDto>candidates = candidateService.getNameOfTechnology("Java");

        assertNotNull(candidates);
    }
}
