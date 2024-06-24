package restdemo.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restdemo.demo.exeption_handling.NoSuchUser;
import restdemo.demo.exeption_handling.UserIncorrectData;
import restdemo.demo.model.User;
import restdemo.demo.service.UserService;


import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        List<User> users = userService.getUsers();
        return users;
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") long id){
       User user = userService.getUserById(id);
       if (user == null){
           throw new NoSuchUser("Пользователя с айди "+ id + " нет в базе данных");
       }
        return user;

    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }
//
    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return user;
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "User " + id + " was deleted";
    }
    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handleExeption(NoSuchUser exeption){
        UserIncorrectData data = new UserIncorrectData();
        data.setInfo(exeption.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

}