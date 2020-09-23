package com.example.springapi.controller;

import com.example.springapi.models.User;
import com.example.springapi.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @PostMapping("/auth")
    @ResponseBody
    @JsonAnyGetter
    public Map<String, Object> authUser(@RequestBody User user) {
        return userRepository.AuthUser(user.getLogin(), user.getPassword());
    }

    @PostMapping("/users")
    @ResponseBody
    public User regUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @PostMapping("/upgusers/{id}")
    public User upgUser(@PathVariable("id") long id, @RequestBody User user){
        User tmp = userRepository.getOne(id);
        tmp.setLogin(user.getLogin());
        tmp.setCourse(user.getCourse());
        tmp.setGroup(user.getGroup());
        String pass = user.getPassword();
        if(pass.equals("") && pass != null){
            tmp.setPassword(pass);
        }
        return userRepository.save(tmp);
    }
}
