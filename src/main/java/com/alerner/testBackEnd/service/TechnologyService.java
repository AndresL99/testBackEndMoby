package com.alerner.testBackEnd.service;

import com.alerner.testBackEnd.domain.Technology;
import com.alerner.testBackEnd.repository.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
public class TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;


    public Technology addTechnology(Technology source)
    {
        if(technologyRepository.existsById(source.getIdTechnology()))
        {
            throw new EntityExistsException();
        }
        else
        {
            return technologyRepository.save(source);
        }
    }

    public Technology getTechnologyById(Long idTechnology)
    {
        return technologyRepository.findById(idTechnology).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public List<Technology>getAllTechnology()
    {
        return technologyRepository.findAll();
    }

    public void deleteTechnologyById(Long idTechnology)
    {
        technologyRepository.deleteById(idTechnology);
    }

    public void updateTechnology(Long idTechnology, Technology source)
    {
        Optional<Technology>technology = technologyRepository.findById(idTechnology);
        source.setIdTechnology(technology.get().getIdTechnology());
        technologyRepository.save(source);
    }
}
