package com.al.mvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// http://websystique.com/springmvc/spring-mvc-4-restful-web-services-crud-example-resttemplate/

@RestController
public class RestMvcController {

    @RequestMapping("/strings")
    @ResponseBody
    public List<String> listArbitraryStrings()
    {
        List<String> ls = new ArrayList<>();
        ls.add( "1" );
        ls.add( "2" );
        ls.add( "3" );

        return ls;
    }
}
