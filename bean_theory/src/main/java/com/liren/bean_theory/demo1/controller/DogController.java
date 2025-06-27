package com.liren.bean_theory.demo1.controller;


import com.liren.bean_theory.demo1.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DogController {
    @Autowired
    private Dog singleDog;

    @Autowired
    private Dog prototypeDog;

    @Autowired
    private Dog requestDog;

    @Autowired
    private Dog sessionDog;

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("/single")
    public String single(){
        Dog contextDog = (Dog)applicationContext.getBean("singleDog");
        return "dog:"+singleDog.toString()+",contextDog:"+contextDog;
    }

    @RequestMapping("/prototype")
    public String prototype(){
        Dog contextDog = (Dog)applicationContext.getBean("prototypeDog");
        return "dog:"+prototypeDog.toString()+",contextDog:"+contextDog;
    }

    @RequestMapping("/request")
    public String request(){
        Dog contextDog = (Dog)applicationContext.getBean("requestDog");
        return "dog:"+requestDog.toString()+",contextDog:"+contextDog.toString();
    }

    @RequestMapping("/session")
    public String session(){
        Dog contextDog = (Dog)applicationContext.getBean("sessionDog");
        return "dog:"+sessionDog.toString()+",contextDog:"+contextDog.toString();
    }

    @RequestMapping("/application")
    public String application(){
        Dog contextDog = (Dog)applicationContext.getBean("applicationDog");
        return "dog:"+applicationContext.toString()+",contextDog:"+contextDog.toString();
    }
}
