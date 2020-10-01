package com.example.springapi.controller;

import com.example.springapi.models.User;
import com.example.springapi.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins="*")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @PostMapping("/user")
    @ResponseBody
    @JsonAnyGetter
    public Map<String, Object> authUser(@RequestBody User user) {
        return userRepository.AuthUser(user.getLogin(), user.getPassword());
    }

    @PostMapping("/reg")
    @ResponseBody
    public Map<String, Object> regUser(@RequestBody User user){
        Map<String, Object> map = new HashMap<>();
        map.put("action", "register");
        if (StringUtils.hasText(user.getLogin()) && StringUtils.hasText(user.getPassword())){
            try {
                User u = userRepository.save(user);
                map.put("result", "done");
                map.put("id", u.getId());
                map.put("login", u.getLogin());

            } catch(Exception e) {
                map.put("result", e.toString());
            }
        }
        return map;
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable("id") long id, @RequestBody User user){
        User tmp;
        try {
            tmp = userRepository.getOne(id);
            String course = user.getCourse();
            String group = user.getNumgroup();
            String pass = user.getPassword();
            String login = user.getLogin();
            if (StringUtils.hasText(course)) {
                tmp.setCourse(course);
            } else{
                tmp.setCourse("");
            }
            if (StringUtils.hasText(login)) {
                tmp.setLogin(login);
            }
            if (StringUtils.hasText(pass)) {
                tmp.setPassword(pass);
            }
            if (StringUtils.hasText(group)) {
                tmp.setNumgroup(group);
            } else{
                tmp.setNumgroup("");
            }
            tmp = userRepository.save(tmp);
            tmp.setPassword("");
        }catch(Exception e){
            tmp = null;
        }
        return tmp;
    }
}
