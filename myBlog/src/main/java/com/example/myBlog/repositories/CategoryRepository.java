package com.example.myBlog.repositories;

import com.example.myBlog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

//CategoryRepo
public interface CategoryRepository extends JpaRepository<Category, Integer>
{

}
