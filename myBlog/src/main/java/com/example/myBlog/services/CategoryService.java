package com.example.myBlog.services;

import com.example.myBlog.dto.CategoryDTO;
import com.example.myBlog.dto.UserDTO;
import com.example.myBlog.entities.Category;
import com.example.myBlog.entities.User;
import com.example.myBlog.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService
{
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories=categoryRepository.findAll();

        List<CategoryDTO> categoryDTOs=categories.stream().map(c->this.categoryToDto(c)).collect(Collectors.toList());

        return categoryDTOs;
    }

    public CategoryDTO getCategoryById(int id) {
        Category category=categoryRepository.findById(id).get();
        return this.categoryToDto(category);
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category=this.dtoToCategory(categoryDTO);
        Category savedCategory=this.categoryRepository.save(category);
        return this.categoryToDto(savedCategory);
    }

    public CategoryDTO updateCategory(int id, CategoryDTO updatedCategoryDTO) {
        Category category=categoryRepository.findById(id).orElse(null);

        category.setCategoryDescription(updatedCategoryDTO.getCategoryDescription());
        category.setCategoryDescription(updatedCategoryDTO.getCategoryDescription());

        Category savedCategory=this.categoryRepository.save(category);
        return this.categoryToDto(savedCategory);
    }

    public boolean deleteCategory(int id) {
        if (categoryRepository.existsById(id))
        {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //MODEL MAPPER
    public Category dtoToCategory(CategoryDTO categoryDTO)
    {
        Category category=modelMapper.map(categoryDTO, Category.class);
        return category;
    }
    public CategoryDTO categoryToDto(Category category)
    {
        CategoryDTO categoryDTO=modelMapper.map(category, CategoryDTO.class);
        return categoryDTO;
    }
}
