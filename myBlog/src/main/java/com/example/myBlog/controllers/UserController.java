package com.example.myBlog.controllers;

import com.example.myBlog.dto.UserDTO;
import com.example.myBlog.entities.User;
import com.example.myBlog.repositories.UserRepository;
import com.example.myBlog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController
{
    @Autowired
    UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> getAllUser()
    {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.userService.getAllUsers());
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Error fetching users: " + e.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id)
    {
        try{
            UserDTO userDTO = this.userService.getUserById(id);
            if(userDTO == null)
            {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Error fetching user: " + e.getMessage());
        }

    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getById(@PathVariable String email)
    {
        try{
            UserDTO userDTO = this.userService.getUserByEmail(email);
            if(userDTO == null)
            {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Error fetching user: " + e.getMessage());
        }

    }

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO)
    {
        try{
            UserDTO createUserDTO=this.userService.createUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createUserDTO);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Error creating user: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody UserDTO updatedUserDTO)
    {
        try{
            UserDTO updateUserDTO=this.userService.updateUser(id, updatedUserDTO);
            return ResponseEntity.ok().body(updateUserDTO);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Error updating user: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id)
    {
        try{
            boolean deleted = userService.deleteUser(id);
            if (deleted)
            {
                return ResponseEntity.ok("User deleted successfully.");
            }
            else
            {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error deleting user: " + e.getMessage());
        }
    }

}
