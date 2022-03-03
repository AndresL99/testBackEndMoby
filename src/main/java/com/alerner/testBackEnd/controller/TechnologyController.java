package com.alerner.testBackEnd.controller;

import com.alerner.testBackEnd.domain.Technology;
import com.alerner.testBackEnd.exception.TechnologyExistException;
import com.alerner.testBackEnd.exception.TechnologyNotExistException;
import com.alerner.testBackEnd.service.TechnologyService;
import lombok.extern.log4j.Log4j2;
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
@RequestMapping("api/technology")
@Log4j2
public class TechnologyController
{
    private TechnologyService technologyService;

    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @PostMapping
    public ResponseEntity<Technology> addTechnology(@RequestBody Technology technology)
    {
        try
        {
            return new ResponseEntity<>(technologyService.addTechnology(technology), HttpStatus.CREATED);
        }
        catch (TechnologyExistException e)
        {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/{idTechnology}")
    public ResponseEntity<Technology> getTechnologyById(@PathVariable Long idTechnology)
    {
        try
        {
            return ResponseEntity.ok(technologyService.getTechnologyById(idTechnology));
        }
        catch (TechnologyNotExistException e)
        {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping
    public ResponseEntity<List<Technology>>getAllTechnology()
    {
        return ResponseEntity.ok(technologyService.getAllTechnology());
    }

    @DeleteMapping("/{idTechnology}")
    public void deleteTechnology(@PathVariable Long idTechnology)
    {
        technologyService.deleteTechnologyById(idTechnology);
    }

    @PutMapping("/{idTechnology}")
    public void updateTechnology(@PathVariable Long idTechnology,@RequestBody Technology technology)
    {
        technologyService.updateTechnology(idTechnology,technology);
    }

}
