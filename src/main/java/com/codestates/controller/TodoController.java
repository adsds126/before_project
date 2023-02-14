package com.codestates.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class TodoController {

    @GetMapping
    public String helloworld(){
        return "To-Do-Application !";
        //this is branch merge
    }

}
