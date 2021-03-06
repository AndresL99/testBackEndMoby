package com.alerner.testBackEnd.repository;

import com.alerner.testBackEnd.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>
{
    User findByUsernameAndPassword(String username,String password);
    User findByEmail(String email);
}
