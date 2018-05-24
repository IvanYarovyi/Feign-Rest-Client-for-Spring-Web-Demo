package com.eone.app.serverside;

import com.eone.app.api.UserApi;
import com.eone.app.api.User;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserApiController implements UserApi {

    @Override
    public Integer getVersion() {
        return 100500;
    }

    @Override
    public List<String> getCodes() {
        return Arrays.asList("CA", "TX");
    }

    @Override
    public User newUser(@RequestBody User user) {
        user.setId(555);
        return user;
    }

    @Override
    public User getUser(@PathVariable /*need it right here for spring*/ Integer id) {
        User user = new User();
        user.setName("User Name");
        user.setId(id);
        user.setAge(19);
        return user;
    }

    @Override
    public User setUserAge(@PathVariable Integer id, @PathVariable Integer age) {
        User user = new User();
        user.setName("User Name");
        user.setId(id);
        user.setAge(age);
        return user;
    }
}
