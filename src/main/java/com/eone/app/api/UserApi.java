package com.eone.app.api;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
public interface UserApi {

    @RequestMapping(method = RequestMethod.GET, value = "/version")
    Integer getVersion();

    @RequestMapping(method = RequestMethod.GET, value = "/codes")
    List<String> getCodes();

    @RequestMapping(method = RequestMethod.POST, value = "/")
    User newUser(User info);

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    User getUser(@PathVariable("id")/*Feign requires it right here*/ Integer id);

}

