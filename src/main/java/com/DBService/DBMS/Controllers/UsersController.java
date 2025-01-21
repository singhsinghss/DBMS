package com.DBService.DBMS.Controllers;

import com.DBService.DBMS.Models.Users;
import com.DBService.DBMS.Services.Interfaces.UsersService;
import com.DBService.DBMS.Services.UsersServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    @Autowired
    private final UsersServiceImpl userServiceImpl;
    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@Valid @RequestBody Users user)
    {
        return new ResponseEntity<>(userServiceImpl.registerNewUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    private ResponseEntity<Users> getUserById(@PathVariable Long userId)
    {
      return new ResponseEntity<>(userServiceImpl.getUserById(userId), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUserDetails(@PathVariable Long userId,@RequestBody Users user)
    {
        try {
            userServiceImpl.updateUser(userId,user);
            return ResponseEntity.ok("User updated successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{user_id}")
    public String deleteUser(@PathVariable Long user_id)
    {
        userServiceImpl.deleteById(user_id);
        return "User with ID " + user_id + " deleted successfully.";

    }

}
