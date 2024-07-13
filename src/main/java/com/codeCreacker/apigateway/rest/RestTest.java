package com.codeCreacker.apigateway.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class RestTest {
    
    @GetMapping("/hello")
    public String getMethodName() {
        return "HelloWorld";
    }
    
}
