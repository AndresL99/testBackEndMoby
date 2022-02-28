package com.alerner.testBackEnd.repository;

import com.alerner.testBackEnd.domain.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology,Long>
{

}
