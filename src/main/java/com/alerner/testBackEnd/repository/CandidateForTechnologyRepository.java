package com.alerner.testBackEnd.repository;

import com.alerner.testBackEnd.domain.CandidateForTechnology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateForTechnologyRepository extends JpaRepository<CandidateForTechnology,Long>
{

}
