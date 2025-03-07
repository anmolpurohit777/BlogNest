package com.example.myBlog.controllers;

import com.example.myBlog.dto.CategoryDTO;
import com.example.myBlog.entities.Category;
import com.example.myBlog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController
{
    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<?> getAllCategories()
    {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.getAllCategories());
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Error fetching categories: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable int id)
    {
        try{
            CategoryDTO categoryDTO = this.categoryService.getCategoryById(id);
            if(categoryDTO == null)
            {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(categoryDTO);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Error fetching category: " + e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO)
    {
        try{
            CategoryDTO categoryCreatedDTO = categoryService.createCategory(categoryDTO);
            return ResponseEntity.ok(categoryCreatedDTO);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Error creating category: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable int id, @RequestBody CategoryDTO updatedCategoryDTO)
    {
        try{
            CategoryDTO categoryDTO = categoryService.updateCategory(id, updatedCategoryDTO);
            if (categoryDTO == null)
            {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(categoryDTO);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Error updating category: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id)
    {
        try{
            boolean deleted=categoryService.deleteCategory(id);
            if (deleted)
            {
                return ResponseEntity.ok("Category deleted successfully.");
            }
            else
            {
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Error deleting category: " + e.getMessage());}
        }
}
