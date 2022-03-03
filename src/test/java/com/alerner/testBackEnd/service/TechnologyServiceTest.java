package com.alerner.testBackEnd.service;

import com.alerner.testBackEnd.domain.Technology;
import com.alerner.testBackEnd.exception.TechnologyExistException;
import com.alerner.testBackEnd.exception.TechnologyNotExistException;
import com.alerner.testBackEnd.repository.TechnologyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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
public class TechnologyServiceTest
{

    private TechnologyService technologyService;
    private TechnologyRepository technologyRepository;

    @BeforeEach
    public void setUp()
    {
        technologyRepository = mock(TechnologyRepository.class);
        technologyService = new TechnologyService(technologyRepository);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addTechnologyTestOk()
    {
        Long idTechnology = getTechnology().getIdTechnology();
        when(technologyRepository.existsById(idTechnology)).thenReturn(false);
        when(technologyRepository.save(getTechnology())).thenReturn(getTechnology());

        Technology technology = technologyService.addTechnology(getTechnology());

        assertEquals(getTechnology(),technology);
    }

    @Test
    void addTechnologyTestFail()
    {
        Long idTechnology = getTechnology().getIdTechnology();
        when(technologyRepository.existsById(2L)).thenReturn(true);
        when(technologyRepository.save(getTechnology())).thenThrow(TechnologyExistException.class);

        assertThrows(TechnologyExistException.class, ()->technologyService.addTechnology(getTechnology()));
    }

    @Test
    void getTechnologyByIdTestOk()
    {
        Long idTechnology = getTechnology().getIdTechnology();
        when(technologyRepository.findById(idTechnology)).thenReturn(Optional.of(getTechnology()));
        Technology technology = technologyService.getTechnologyById(idTechnology);
        verify(technologyRepository,times(1)).findById(idTechnology);
    }

    @Test
    void getTechnologyByIdTestFail()
    {
        when(technologyRepository.findById(3L)).thenThrow(TechnologyNotExistException.class);
        assertThrows(TechnologyNotExistException.class, ()->technologyService.getTechnologyById(3L));
    }

    @Test
    void getAllTechnologyTest()
    {
        when(technologyRepository.findAll()).thenReturn(getTechnologyList());
        List<Technology>technologies = technologyService.getAllTechnology();

        assertEquals(getTechnologyList(),technologies);

        verify(technologyRepository,times(1)).findAll();
    }

    @Test
    void updateTechnologyTest()
    {
        when(technologyRepository.findById(getTechnology().getIdTechnology())).thenReturn(Optional.of(getTechnology()));
        when(technologyRepository.save(getTechnology())).thenReturn(getTechnology());

        technologyService.updateTechnology(getTechnology().getIdTechnology(),getTechnology());

        verify(technologyRepository,times(1)).findById(getTechnology().getIdTechnology());
        verify(technologyRepository,times(1)).save(getTechnology());
    }

    @Test
    void deleteTechnologyTest()
    {
        doNothing().when(technologyRepository).deleteById(getTechnology().getIdTechnology());

        technologyService.deleteTechnologyById(getTechnology().getIdTechnology());

        verify(technologyRepository,times(1)).deleteById(getTechnology().getIdTechnology());
    }
}
