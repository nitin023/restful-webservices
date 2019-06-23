package com.spring.webservices.restfulwebservices.Controller;

import com.spring.webservices.restfulwebservices.Domain.User;
import com.spring.webservices.restfulwebservices.Exception.UserNotFoundException;
import com.spring.webservices.restfulwebservices.Repository.UserDomainRepository;
import com.spring.webservices.restfulwebservices.Service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserJPAResourceController {

    @Autowired
    private UserDomainRepository userDomainRepository;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers()
    {
        return userDomainRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id)
    {
        Optional<User> user = userDomainRepository.findById(id);
        if(!user.isPresent())
        {
            throw new UserNotFoundException("user not found with id : " + id);
        }
        Resource<User> resource = new Resource<User>(user.get());
        ControllerLinkBuilder linkBuilder = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkBuilder.withRel("all-users"));

        return resource;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity createUser(@Valid @RequestBody User user)
    {
        User createdUser = userDomainRepository.save(user);
        URI location =  ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id)
    {
        Optional<User> optionalUser = userDomainRepository.findById(id);
        String responseMessage = "";
        if(optionalUser.isPresent())
        {
            userDomainRepository.deleteById(id);
            responseMessage = "user " + optionalUser.get().getName() + " deleted successfully";
            return new ResponseEntity(responseMessage, HttpStatus.OK);
        }
        throw new UserNotFoundException("user not found with id : " + id);
    }
}
