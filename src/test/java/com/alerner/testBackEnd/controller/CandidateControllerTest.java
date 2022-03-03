package com.alerner.testBackEnd.controller;

import com.alerner.testBackEnd.domain.Candidate;
import com.alerner.testBackEnd.service.CandidateService;
import com.alerner.testBackEnd.service.TechnologyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import static com.alerner.testBackEnd.utils.TestEntityFactory.getCandidate;
import static com.alerner.testBackEnd.utils.TestEntityFactory.getCandidateList;
import static com.alerner.testBackEnd.utils.TestEntityFactory.getTechnology;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CandidateControllerTest extends AbstractControllerTest
{
    private CandidateController candidateController;
    private CandidateService candidateService;
    private TechnologyService technologyService;

    @BeforeEach
    void setUp()
    {
        candidateService = mock(CandidateService.class);
        technologyService = mock(TechnologyService.class);
        candidateController = new CandidateController(candidateService,technologyService);
    }

    @Test
    void addCandidateTest()
    {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));

        when(candidateService.addCandidate(getCandidate())).thenReturn(getCandidate());

        ResponseEntity<Candidate>candidateResponseEntity = candidateController.addCandidate(getCandidate());

        assertEquals(HttpStatus.CREATED.value(),candidateResponseEntity.getStatusCodeValue());
    }

    @Test
    void getCandidateByIdTest()
    {
        Long idCandidate = getCandidate().getIdCandidate();

        when(candidateService.getCandidateById(idCandidate)).thenReturn(getCandidate());

        ResponseEntity<Candidate>responseEntity = candidateController.getCandidateById(idCandidate);

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(getCandidate().getIdCandidate(),responseEntity.getBody().getIdCandidate());
    }

    @Test
    void getAllCandidateTest()
    {
        when(candidateService.getAllCandidate()).thenReturn(getCandidateList());

        ResponseEntity<List<Candidate>>listResponseEntity = candidateController.getAllCandidate();

        assertEquals(HttpStatus.OK,listResponseEntity.getStatusCode());
        assertEquals(getCandidateList().get(0).getDocument(),listResponseEntity.getBody().get(0).getDocument());
    }

    @Test
    void deleteCandidateByIdTest()
    {
        doNothing().when(candidateService).deleteCandidateById(getCandidate().getIdCandidate());
        candidateController.deleteCandidateById(getCandidate().getIdCandidate());
        verify(candidateService,times(1)).deleteCandidateById(getCandidate().getIdCandidate());
    }

    @Test
    void updateCandidateTest()
    {
        Long idCandidate = getCandidate().getIdCandidate();
        doNothing().when(candidateService).updateCandidate(idCandidate,getCandidate());
        candidateController.updateCandidate(idCandidate,getCandidate());
        verify(candidateService,times(1)).updateCandidate(idCandidate, getCandidate());
    }

    @Test
    void addTechnologyAndCandidateTest()
    {
        Long idCandidate = getCandidate().getIdCandidate();
        Long idTechnology = getTechnology().getIdTechnology();
        when(candidateService.addTechnologyAndCandidate(idCandidate,idTechnology,5)).thenReturn(getCandidate());
        ResponseEntity<Candidate>candidateResponseEntity = candidateController.addTechnologyAndCandidate(idCandidate,idTechnology,5);
        assertEquals(HttpStatus.OK,candidateResponseEntity.getStatusCode());
        assertEquals(getCandidate().getDocument(),candidateResponseEntity.getBody().getDocument());
    }

    @Test
    void getTechnologyByNameTest()
    {
        when(candidateService.getNameOfTechnology("NodeJs")).thenReturn(getCandidateList());
        ResponseEntity<List<Candidate>>listResponseEntity = candidateController.getTechnologyByName("NodeJs");
        assertEquals(HttpStatus.OK,listResponseEntity.getStatusCode());
        assertEquals(getCandidateList().get(0).getDocument(),listResponseEntity.getBody().get(0).getDocument());
    }
}
