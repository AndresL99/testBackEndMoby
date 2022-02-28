package com.alerner.testBackEnd.controller;

import com.alerner.testBackEnd.domain.Technology;
import com.alerner.testBackEnd.service.TechnologyService;
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
public class TechnologyController
{
    private TechnologyService technologyService;

    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @PostMapping
    public ResponseEntity<Technology> addTechnology(@RequestBody Technology technology)
    {
        return new ResponseEntity<>(technologyService.addTechnology(technology), HttpStatus.CREATED);
    }

    @GetMapping("/{idTechnology}")
    public ResponseEntity<Technology> getTechnologyById(@PathVariable Long idTechnology)
    {
        return ResponseEntity.ok(technologyService.getTechnologyById(idTechnology));
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
