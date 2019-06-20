package com.spring.webservices.restfulwebservices.Controller;


import com.spring.webservices.restfulwebservices.Resources.SomeDemoBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilterController {

    @GetMapping("/filtering")
    public SomeDemoBean retrieveSomeDemoBean()
    {
        return new SomeDemoBean("type","demo","bean");
    }



    @GetMapping("/filtering-list")
    public List<SomeDemoBean> retrieveListOfSomeDemoBean()
    {
        return Arrays.asList(
                new SomeDemoBean("type1","demo1","bean1"),
                new SomeDemoBean("type2","demo2","bean2"),
                new SomeDemoBean("type3","demo3","bean3"));
    }
}
