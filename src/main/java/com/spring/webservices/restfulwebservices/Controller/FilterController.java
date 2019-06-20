package com.spring.webservices.restfulwebservices.Controller;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.spring.webservices.restfulwebservices.Resources.SomeDemoBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilterController {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeDemoBean()
    {
        SomeDemoBean someDemoBean = new SomeDemoBean("type","demo","bean");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeDemoBeanFilter",filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someDemoBean);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
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
