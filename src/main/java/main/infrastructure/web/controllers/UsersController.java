package main.infrastructure.web.controllers;

import main.application.services.UsersService;
import main.domain.models.Users;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping()
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @PostMapping()
    public Users createUser(@RequestBody Users user) {
        return usersService.saveUser(user);
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return usersService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        System.out.println(user);
        return usersService.login(user);
    }

    @PostMapping("/register")
    public String register(@RequestBody Users user) {
        System.out.println(user);
        return "Register successful";
    }

}
