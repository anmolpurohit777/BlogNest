package com.example.myBlog.controllers;

import com.example.myBlog.dto.UserDTO;
import com.example.myBlog.entities.User;
import com.example.myBlog.repositories.UserRepository;
import com.example.myBlog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController
{
    @Autowired
    UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> getAllUser()
    {
//        try{
//            List<User> users = userService.getAllUsers();
//            return ResponseEntity.ok(users);
//        }catch (Exception e){
//            return ResponseEntity.internalServerError().body("Error fetching users: " + e.getMessage());
//        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.userService.getAllUsers());
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Error fetching users: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id)
    {
//        try{
//            User user = userService.getUserById(id);
//            if (user == null)
//            {
//                return ResponseEntity.notFound().build();
//            }
//            return ResponseEntity.ok(user);
//        }catch (Exception e){
//            return ResponseEntity.internalServerError().body("Error fetching user: " + e.getMessage());
//        }
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

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO)
    {
//        try{
//            User savedUser = userService.createUser(user);
//            return ResponseEntity.ok(savedUser);
//        }catch (Exception e){
//            return ResponseEntity.internalServerError().body("Error creating user: " + e.getMessage());
//        }
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
//        try{
//            User user = userService.updateUser(id, updatedUser);
//            return ResponseEntity.ok(user);
//        }catch(Exception e){
//            return ResponseEntity.internalServerError().body("Error updating user: " + e.getMessage());
//        }
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
