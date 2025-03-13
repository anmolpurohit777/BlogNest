package com.example.myBlog.controllers;

import com.example.myBlog.dto.BlogDTO;
import com.example.myBlog.services.BlogService;
import com.example.myBlog.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class BlogController
{
    @Autowired
    BlogService blogService;

    @Autowired
    FileService fileService;

    @Value("${project.image}")
    private String path;

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

    @GetMapping("/blogs")
    public ResponseEntity<?> getAllBlogs(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) Integer pageNo,
            @RequestParam(value = "pageSize",defaultValue = "6",required = false) Integer pageSize,
            @RequestParam(value="sortBy",defaultValue = "addDate",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "desc",required = false)String sortDir
    )
    {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.blogService.getAllBlogs(pageNo,pageSize,sortBy,sortDir));
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

    @PutMapping("/post/{postId}")
    public ResponseEntity<?> updatePost(@RequestBody BlogDTO blogDTO, @PathVariable Integer postId)
    {
        try{
            return ResponseEntity.ok(this.blogService.updateBlog(blogDTO, postId));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error updating blog: " + e.getMessage());
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

    @GetMapping("/blogs/search/{keyword}")
    public ResponseEntity<?> searchBlogByTitle(@PathVariable String keyword)
    {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.blogService.searchBlogs(keyword));
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Error fetching blogs: " + e.getMessage());
        }
    }

    @PostMapping("/blog/image/upload/{postId}")
    public ResponseEntity<BlogDTO> uploadBlogImage(
            @RequestParam("image")MultipartFile image,
            @PathVariable Integer postId
    ) throws IOException
    {
        BlogDTO blogDTO=this.blogService.getBlogById(postId);
        String fileName=this.fileService.uploadImage(path,image);
        blogDTO.setImageName(fileName);
        BlogDTO updatedBlogDTO=this.blogService.updateBlog(blogDTO,postId);

        return new ResponseEntity<BlogDTO>(updatedBlogDTO,HttpStatus.OK);
    }
}
