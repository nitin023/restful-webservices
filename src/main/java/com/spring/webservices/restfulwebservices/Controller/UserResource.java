package com.spring.webservices.restfulwebservices.Controller;

import com.spring.webservices.restfulwebservices.DTO.User;
import com.spring.webservices.restfulwebservices.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;


    @GetMapping("/users")
    public List<User> retrieveAllUsers()
    {
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id)
    {
        User user = userDaoService.findOne(id);
        if(user==null)
        {
            return new User(-1,"-",new Date());
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody User user)
    {
        User createdUser = userDaoService.save(user);
       URI location =  ServletUriComponentsBuilder
               .fromCurrentRequest()
               .path("/{id}")
               .buildAndExpand(createdUser.getId())
               .toUri();

       return ResponseEntity.created(location).build();
    }
}
