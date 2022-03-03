package com.alerner.testBackEnd.service;

import com.alerner.testBackEnd.domain.User;
import com.alerner.testBackEnd.exception.UserExistException;
import com.alerner.testBackEnd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user)
    {
        if(userRepository.findByEmail(user.getEmail()) == null)
        {
            throw new UserExistException("This user with next email : "+ user.getEmail() + "not exist.");
        }
        else
        {
            return userRepository.save(user);
        }
    }

    public List<User>getAllUser()
    {
        return userRepository.findAll();
    }

    public User getUsernameAndPassword(String username,String password)
    {
        return userRepository.findByUsernameAndPassword(username,password);
    }
}
