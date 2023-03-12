package com.example.emsApp.Controller;

import com.example.emsApp.Entity.User;
import com.example.emsApp.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Operation(summary = "using ID number, fetch user from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302",
                    description = "User fetched",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500",
                    description = "if user is not found in database, throw EntityNotFoundException",
                    content = @Content)})
    @GetMapping("/{id}")
    ResponseEntity<String> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id).getUsername(), HttpStatus.FOUND);
    }

    @Operation(summary = "Fetch all users from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Users fetched",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "No users created yet",
                    content = @Content)})
    @GetMapping("/all")
    ResponseEntity<List<User>> getAllUsers() {
        if (userService.getAllUsers().isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @Operation(summary = "Register new user")
    @ApiResponse(responseCode = "200",
            description = "User registered",
            content = {@Content(mediaType = "application/json")})
    @PostMapping("/register")
    ResponseEntity<User> saveUser(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); //set password to encoded version of the entered password
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }

    @Operation(summary = "Delete user from database")
    @ApiResponse(responseCode = "204",
            description = "User deleted",
            content = {@Content(mediaType = "application/json")})
    @DeleteMapping("/{id}")
    ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
