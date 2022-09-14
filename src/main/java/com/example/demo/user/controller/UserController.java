package com.example.demo.user.controller;

import com.example.demo.user.entity.MyUser;
import com.example.demo.user.exeption.UserExistException;
import com.example.demo.user.exeption.UserNotFoundException;
import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public MyUser saveUser(@RequestBody MyUser user) throws UserExistException {
        return userService.createUser(user);
    }

    @GetMapping
    public ResponseEntity<Iterable<MyUser>> getUsers() {
        Iterable<MyUser> userList = userService.getUsers();
        String version = String.valueOf(userList.hashCode());
        return ResponseEntity.ok()
                .eTag(version)
                .body(userList);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<MyUser> findShopById(@PathVariable("id") long id) throws UserNotFoundException {
        MyUser myUser = userService.findById(id);
        String version = Long.toString(myUser.getVersion());
        return ResponseEntity.ok()
                .eTag(version)
                .body(myUser);
    }

    @DeleteMapping("/delete/{id}")
    public void delShopById(@PathVariable("id") long id) throws UserNotFoundException {
        userService.delete(id);
    }

    @PutMapping
    public MyUser updateUser(@RequestBody MyUser user, Long id) throws UserNotFoundException {
        return userService.update(user, id);
    }
}
