package com.alerner.testBackEnd.controller;

import com.alerner.testBackEnd.domain.User;
import com.alerner.testBackEnd.domain.dto.LoginRequestDto;
import com.alerner.testBackEnd.domain.dto.LoginResponseDto;
import com.alerner.testBackEnd.domain.dto.UserDto;
import com.alerner.testBackEnd.exception.UserExistException;
import com.alerner.testBackEnd.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.alerner.testBackEnd.utils.Constants.AUTH_ADMIN;
import static com.alerner.testBackEnd.utils.Constants.JWT_SECRET;

@RestController
@RequestMapping("/api/")
@Log4j2
public class UserController
{
    private UserService userService;
    private ModelMapper modelMapper;
    private ObjectMapper objectMapper;


    public UserController(UserService userService, ModelMapper modelMapper,ObjectMapper objectMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<User>addUser(@RequestBody User user)
    {
        try
        {
            return new ResponseEntity<User>(userService.addUser(user), HttpStatus.CREATED);
        }
        catch (UserExistException e)
        {
            e.getMessage();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>>getAllUser()
    {
        return new ResponseEntity<List<User>>(userService.getAllUser(),HttpStatus.OK);
    }

    @PostMapping(value = "login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto)
    {
        log.info(loginRequestDto.toString());
        User user = userService.getUsernameAndPassword(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        if (user!=null)
        {
            UserDto dto = modelMapper.map(user, UserDto.class);
            return ResponseEntity.ok(LoginResponseDto.builder().token(this.generateToken(dto)).build());
        }
        else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private String generateToken(UserDto userDto) {

        try {
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("DEFAULT_USER");
            String token = Jwts
                    .builder()
                    .setId("JWT")
                    .setSubject(userDto.getUsername())
                    .claim("user", objectMapper.writeValueAsString(userDto))
                    .claim("authorities", grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 1000))
                    .signWith(SignatureAlgorithm.HS512, JWT_SECRET.getBytes()).compact();
            return token;
        } catch (Exception e) {
            return null;
        }
    }
}
