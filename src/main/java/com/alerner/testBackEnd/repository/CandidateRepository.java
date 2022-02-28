package com.alerner.testBackEnd.repository;

import com.alerner.testBackEnd.domain.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long>
{

}
