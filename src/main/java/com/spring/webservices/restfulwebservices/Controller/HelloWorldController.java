package com.spring.webservices.restfulwebservices.Controller;

import com.spring.webservices.restfulwebservices.DTO.HelloWorldBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

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

    @GetMapping(path="/hello-world/path-variable/{name}/{age}")
    public HelloWorldBean helloWorldBean(@PathVariable String name,@PathVariable String age)
    {
        return new HelloWorldBean("Hello world ," + name + " age " + age);
    }

    @GetMapping(path="/hello-world-internalisation")
    public String helloWorldInternalisation()
    {
        return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    }
}
