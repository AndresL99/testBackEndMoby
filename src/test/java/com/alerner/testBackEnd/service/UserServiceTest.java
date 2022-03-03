package com.alerner.testBackEnd.service;

import com.alerner.testBackEnd.domain.User;
import com.alerner.testBackEnd.exception.UserExistException;
import com.alerner.testBackEnd.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.alerner.testBackEnd.utils.TestEntityFactory.getUser;
import static com.alerner.testBackEnd.utils.TestEntityFactory.getUserList;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest
{
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    public void setUp()
    {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addUserTestOk()
    {
        when(userRepository.findByEmail(getUser().getEmail())).thenReturn(getUser());
        when(userRepository.save(getUser())).thenReturn(getUser());
        User user = userService.addUser(getUser());
        assertEquals(getUser(),user);
    }

    @Test
    void addUserTestFail()
    {
        when(userRepository.findByEmail(getUser().getEmail())).thenReturn(null);
        when(userRepository.save(getUser())).thenThrow(UserExistException.class);

        assertThrows(UserExistException.class, () -> userService.addUser(getUser()));
    }

    @Test
    void getAllUserTest()
    {
        when(userRepository.findAll()).thenReturn(getUserList());
        List<User>users = userService.getAllUser();
        assertEquals(getUserList(),users);
        verify(userRepository,times(1)).findAll();
    }

    @Test
    void getUsernameAndPasswordTest()
    {
        when(userRepository.findByUsernameAndPassword(getUser().getUsername(),getUser().getPassword())).thenReturn(getUser());
        User user = userService.getUsernameAndPassword(getUser().getUsername(), getUser().getPassword());
        verify(userRepository,times(1)).findByUsernameAndPassword(getUser().getUsername(), getUser().getPassword());
    }
}
