package com.alerner.testBackEnd.repository;

import com.alerner.testBackEnd.domain.Candidate;
import com.alerner.testBackEnd.domain.CandidateForTechnology;
import com.alerner.testBackEnd.domain.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.alerner.testBackEnd.utils.Querys.CANDIDATE_FOR_TECHNOLOGY;

@Repository
public interface CandidateForTechnologyRepository extends JpaRepository<CandidateForTechnology,Long>
{
    @Query(value = CANDIDATE_FOR_TECHNOLOGY, nativeQuery = true)
    List<CandidateForTechnology> findByTechnologyName(String name);

    List<CandidateForTechnology> findByTechnology(Technology technology);

    List<CandidateForTechnology> findByCandidate(Candidate candidate);
    CandidateForTechnology findByCandidateAndTechnology(Candidate candidate, Technology technology);
}
