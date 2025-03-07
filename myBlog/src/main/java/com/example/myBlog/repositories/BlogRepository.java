package com.example.myBlog.repositories;

import com.example.myBlog.entities.Blog;
import com.example.myBlog.entities.Category;
import com.example.myBlog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//BlogRepo
public interface BlogRepository extends JpaRepository<Blog, Integer>
{
    List<Blog> findByCategory(Category category);
    List<Blog> findByUser(User user);
}
