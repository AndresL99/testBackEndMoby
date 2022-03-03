package com.alerner.testBackEnd.controller;

import com.alerner.testBackEnd.domain.User;
import com.alerner.testBackEnd.exception.UserExistException;
import com.alerner.testBackEnd.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import static com.alerner.testBackEnd.utils.TestEntityFactory.getUser;
import static com.alerner.testBackEnd.utils.TestEntityFactory.getUserList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserControllerTest extends AbstractControllerTest
{
    private UserService userService;
    private UserController userController;
    private ModelMapper modelMapper;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp()
    {
        userService = mock(UserService.class);
        objectMapper = mock(ObjectMapper.class);
        modelMapper = mock(ModelMapper.class);
        userController = new UserController(userService,modelMapper,objectMapper);
    }

    @Test
    void addUserTestOk()
    {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));

        when(userService.addUser(getUser())).thenReturn(getUser());

        ResponseEntity<User>userResponseEntity = userController.addUser(getUser());

        assertEquals(HttpStatus.CREATED.value(),userResponseEntity.getStatusCodeValue());
    }

    @Test
    void addUserTestFail()
    {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));

        when(userService.addUser(getUser())).thenThrow(UserExistException.class);

        ResponseEntity<User>userResponseEntity = userController.addUser(getUser());

        assertEquals(HttpStatus.BAD_REQUEST.value(),userResponseEntity.getStatusCodeValue());
    }

    @Test
    void getAllUserTest()
    {
        when(userService.getAllUser()).thenReturn(getUserList());
        ResponseEntity<List<User>>listResponseEntity = userController.getAllUser();

        assertEquals(HttpStatus.OK,listResponseEntity.getStatusCode());
        assertEquals(getUserList().get(0).getEmail(),listResponseEntity.getBody().get(0).getEmail());
    }
}
