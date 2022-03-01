package com.alerner.testBackEnd.domain;

import com.alerner.testBackEnd.domain.enums.TypeOfDocument;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "candidate")
public class Candidate
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCandidate")
    private Long idCandidate;

    @NotNull
    @Column(name = "firstName")
    private String firstName;

    @NotNull
    @Column(name = "lastName")
    private String lastName;

    private TypeOfDocument typeOfDocument;

    @Column(name = "document")
    @NotBlank(message = "Number of document cannot be empty ")
    @Size(max = 8)
    private String document;

    @Column(name = "dateOfBirth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", locale = "es-Arg", timezone = "America/Buenos Aires")
    private Date dateOfBirth;

}
