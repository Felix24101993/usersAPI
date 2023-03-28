package com.users.api.usersAPI.controller;

import com.users.api.usersAPI.model.User;
import com.users.api.usersAPI.model.UserStatus;
import com.users.api.usersAPI.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/page/{pageNum}")
    public List<User> findAllByPage(@PathVariable int pageNum,
                                    @RequestParam(defaultValue = "10") int pageSize,
                                    @RequestParam(defaultValue = "id") String sortField,
                                    @RequestParam(defaultValue = "asc") String sortDir ) {
        return userService.findAllByPage(pageNum, pageSize, sortField, sortDir);
    }

    // method for getting all the users sorted by their birthdate and after that by their first name
    @GetMapping("/sorted")
    public List<User> findAllSorted() {
        return userService.findAllSorted();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User newUser) {
        return userService.update(id, newUser);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PatchMapping("/{id}/update-status")
    public User updateStatus(@PathVariable long id, @RequestBody UserStatus userStatus) {
        return userService.updateStatus(userStatus, findById(id));
    }

}
