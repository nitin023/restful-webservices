package com.spring.webservices.restfulwebservices.ApiVersioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    @GetMapping("v1/person")
    public PersonV1 getPersonV1()
    {
        return (new PersonV1("Nitin Khulbe"));
    }

    @GetMapping("v2/person")
    public PersonV2 getPersonV2()
    {
        return new PersonV2(new Name("Nitin","Khulbe"));
    }

    @GetMapping(value ="person/param",params = "version=1")
    public PersonV1 getPersonV1usingRequestParam()
    {
        return (new PersonV1("Nitin Khulbe"));
    }

    @GetMapping(value ="person/param",params = "version=2")
    public PersonV2 getPersonV2usingRequestParam()
    {
        return new PersonV2(new Name("Nitin","Khulbe"));
    }

    @GetMapping(value ="person/header",headers = "X-API-VERSION=1")
    public PersonV1 getPersonV1usingRequestHeader()
    {
        return (new PersonV1("Nitin Khulbe"));
    }

    @GetMapping(value ="person/header",headers = "X-API-VERSION=2")
    public PersonV2 getPersonV2usingRequestHeader()
    {
        return new PersonV2(new Name("Nitin","Khulbe"));
    }

    @GetMapping(value ="person/produces",produces = "application/vnd.company.app-v1+json")
    public PersonV1 getPersonV1usingRequestHeaderProducesMethod()
    {
        return (new PersonV1("Nitin Khulbe"));
    }

    @GetMapping(value ="person/produces",produces = "application/vnd.company.app-v2+json")
    public PersonV2 getPersonV2usingRequestHeaderProducesMethod()
    {
        return new PersonV2(new Name("Nitin","Khulbe"));
    }

}
