package com.spring.webservices.restfulwebservices.Controller;

import com.spring.webservices.restfulwebservices.Domain.User;
import com.spring.webservices.restfulwebservices.Exception.UserNotFoundException;
import com.spring.webservices.restfulwebservices.Service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResourceController {

    @Autowired
    private UserDaoService userDaoService;


    @GetMapping("/users")
    public List<User> retrieveAllUsers()
    {
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id)
    {
        User user = userDaoService.findOne(id);
        if(user==null)
        {
            throw new UserNotFoundException("id : " + id);
        }
        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder linkBuilder = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkBuilder.withRel("all-users"));

        return resource;
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody User user)
    {
        User createdUser = userDaoService.save(user);
       URI location =  ServletUriComponentsBuilder
               .fromCurrentRequest()
               .path("/{id}")
               .buildAndExpand(createdUser.getId())
               .toUri();

       return ResponseEntity.created(location).build();
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id)
    {
        User user = userDaoService.deleteById(id);
        if(user==null)
        {
            throw new UserNotFoundException("id : " + id);
        }
        return new ResponseEntity("user " + user.getName() + " deleted successfully", HttpStatus.OK);
    }
}
