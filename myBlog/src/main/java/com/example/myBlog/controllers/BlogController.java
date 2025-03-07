package com.example.myBlog.controllers;

import com.example.myBlog.dto.BlogDTO;
import com.example.myBlog.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BlogController
{
    @Autowired
    BlogService blogService;

    @GetMapping("/user/{userId}/blogs")
    public ResponseEntity<?> getBlogByUser(@PathVariable Integer userId)
    {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.blogService.getByUsers(userId));
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Error fetching blogs: " + e.getMessage());
        }
    }

    @GetMapping("/category/{categoryId}/blogs")
    public ResponseEntity<?> getBlogByCategory(@PathVariable Integer categoryId)
    {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.blogService.getByCategories(categoryId));
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Error fetching blogs: " + e.getMessage());
        }
    }

    @GetMapping("/blogs/")
    public ResponseEntity<?> getAllBlogs()
    {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.blogService.getAllBlogs());
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Error fetching blogs: " + e.getMessage());
        }
    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<?> getBlogBtId(@PathVariable Integer id)
    {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.blogService.getBlogById(id));
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Error fetching blogs: " + e.getMessage());
        }
    }

    @PostMapping("/user/{userId}/category/{categoryId}/blogs")
    public ResponseEntity<?> createBlog(
            @RequestBody BlogDTO blogDTO,
            @PathVariable int userId,
            @PathVariable int categoryId)
    {
        try{
            BlogDTO createBlogDTO=this.blogService.createBlog(blogDTO, userId, categoryId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createBlogDTO);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Error creating blog: " + e.getMessage());
        }
    }

    @DeleteMapping("/blogs/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Integer id)
    {
        try{
            boolean deleted=this.blogService.deletePost(id);
            if(deleted)
            {
                return ResponseEntity.ok("User deleted successfully.");
            }
            else
            {
                return ResponseEntity.internalServerError().body("Error deleting blog: " + id);
            }
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Error deleting blog: " + e.getMessage());
        }
    }
}
