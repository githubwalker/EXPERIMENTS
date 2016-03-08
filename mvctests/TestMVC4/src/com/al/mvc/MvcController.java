package com.al.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// http://websystique.com/spring-4-mvc-tutorial/


// Circular view path [index.html]: would dispatch back to the current handler URL
// http://stackoverflow.com/questions/18813615/how-to-avoid-the-circular-view-path-exception-with-spring-mvc-test
// http://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/mvc.html

// not jsp:
// http://stackoverflow.com/questions/15479213/how-to-serve-html-files-with-spring


// http://websystique.com/springmvc/spring-4-mvc-helloworld-tutorial-full-example/

// http://www.coderanch.com/t/642820/Spring/dispathcer-servlet-xml-process-suffix

// resources with spring mvc
// http://fruzenshtein.com/spring-mvc-resources/

@Controller
@RequestMapping("/")
public class MvcController {
    public MvcController()
    {

    }

    @RequestMapping("index")
    public String mainPage(ModelAndView model) {
        return "idx";
    }

    @RequestMapping("login")
    public String loginPage(ModelAndView model) {
        return "lgn";
    }
}

