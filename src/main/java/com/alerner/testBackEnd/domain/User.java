package com.alerner.testBackEnd.domain;


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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Long idUser;

    @Column(name = "email")
    @NotBlank(message = "The email cannot be empty.")
    @Email
    private String email;

    @NotBlank(message = "The username cannot be empty!")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 6,max = 16)
    @Column(name = "password")
    private String password;
}
