package com.alerner.testBackEnd.controller;

import com.alerner.testBackEnd.domain.Technology;
import com.alerner.testBackEnd.exception.TechnologyExistException;
import com.alerner.testBackEnd.exception.TechnologyNotExistException;
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

import static com.alerner.testBackEnd.utils.TestEntityFactory.getTechnology;
import static com.alerner.testBackEnd.utils.TestEntityFactory.getTechnologyList;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TechnologyControllerTest
{
    private TechnologyService technologyService;
    private TechnologyController technologyController;

    @BeforeEach
    public void setUp()
    {
        technologyService = mock(TechnologyService.class);
        technologyController = new TechnologyController(technologyService);
    }

    @Test
    void addTechnologyTest()
    {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));

        when(technologyService.addTechnology(getTechnology())).thenReturn(getTechnology());

        ResponseEntity<Technology>technologyResponseEntity = technologyController.addTechnology(getTechnology());

        assertEquals(HttpStatus.CREATED.value(),technologyResponseEntity.getStatusCodeValue());
    }

    @Test
    void addTechnologyTestFail()
    {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));

        when(technologyService.addTechnology(getTechnology())).thenThrow(TechnologyExistException.class);

        ResponseEntity<Technology>technologyResponseEntity = technologyController.addTechnology(getTechnology());

        assertEquals(HttpStatus.BAD_REQUEST.value(),technologyResponseEntity.getStatusCodeValue());
    }

    @Test
    void getTechnologyByIdTestOk()
    {
        Long idTechnology = getTechnology().getIdTechnology();

        when(technologyService.getTechnologyById(idTechnology)).thenReturn(getTechnology());

        ResponseEntity<Technology>technologyResponseEntity = technologyController.getTechnologyById(idTechnology);

        assertEquals(HttpStatus.OK,technologyResponseEntity.getStatusCode());
        assertEquals(getTechnology().getIdTechnology(),technologyResponseEntity.getBody().getIdTechnology());
    }

    @Test
    void getTechnologyByIdTestFail()
    {
        when(technologyService.getTechnologyById(6L)).thenThrow(TechnologyNotExistException.class);
        ResponseEntity<Technology>technologyResponseEntity = technologyController.getTechnologyById(6L);
        assertEquals(HttpStatus.NOT_FOUND,technologyResponseEntity.getStatusCode());
    }

    @Test
    void getAllTechnologyTest()
    {
        when(technologyService.getAllTechnology()).thenReturn(getTechnologyList());
        ResponseEntity<List<Technology>>listResponseEntity = technologyController.getAllTechnology();

        assertEquals(HttpStatus.OK,listResponseEntity.getStatusCode());
        assertEquals(getTechnologyList().get(0).getIdTechnology(),listResponseEntity.getBody().get(0).getIdTechnology());
    }

    @Test
    void deleteTechnologyTest()
    {
        Long idTechnology = getTechnology().getIdTechnology();
        doNothing().when(technologyService).deleteTechnologyById(idTechnology);
        technologyController.deleteTechnology(idTechnology);
        verify(technologyService,times(1)).deleteTechnologyById(idTechnology);
    }

    @Test
    void updateTechnologyTest()
    {
        Long idTechnology = getTechnology().getIdTechnology();
        doNothing().when(technologyService).updateTechnology(idTechnology,getTechnology());
        technologyController.updateTechnology(idTechnology,getTechnology());
        verify(technologyService,times(1)).updateTechnology(idTechnology,getTechnology());
    }
}
