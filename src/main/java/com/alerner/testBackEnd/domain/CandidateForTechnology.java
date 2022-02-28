package com.alerner.testBackEnd.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CandidateForTechnology
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCandidateForTechnology")
    private Long idCandidateForTechnology;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCandidate", referencedColumnName = "idCandidate")
    private Candidate candidate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTechnology", referencedColumnName = "idTechnology")
    private Technology technology;

    @Column(name = "yearsOfExperience")
    private Integer yearsOfExperience;
}
