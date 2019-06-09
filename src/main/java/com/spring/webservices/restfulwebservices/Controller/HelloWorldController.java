package com.spring.webservices.restfulwebservices.Controller;

import com.spring.webservices.restfulwebservices.DTO.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(path="/hello-world")
    public String helloWorld()
    {
        return "hello world";
    }

    @GetMapping(path="/hello-world-bean")
    public HelloWorldBean helloWorldBean()
    {
        return new HelloWorldBean("Hello world Bean");
    }

    @GetMapping(path="/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name)
    {
        return new HelloWorldBean("Hello world ," + name);
    }
}
