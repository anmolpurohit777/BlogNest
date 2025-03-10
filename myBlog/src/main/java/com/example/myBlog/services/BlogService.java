package com.example.myBlog.services;

import com.example.myBlog.dto.BlogDTO;
import com.example.myBlog.dto.CommentDTO;
import com.example.myBlog.entities.Blog;
import com.example.myBlog.entities.Category;
import com.example.myBlog.entities.User;
import com.example.myBlog.repositories.BlogRepository;
import com.example.myBlog.repositories.CategoryRepository;
import com.example.myBlog.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//BlogService

@Service
public class BlogService
{
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<BlogDTO> getByCategories(Integer id)
    {
        Category category=this.categoryRepository.findById(id).get();
        List<Blog> blogs=this.blogRepository.findByCategory(category);
        List<BlogDTO> blogDTOs=blogs.stream().map(blog->this.modelMapper.map(blog, BlogDTO.class)).collect(Collectors.toList());
        return blogDTOs;
    }

    public List<BlogDTO> getByUsers(Integer id)
    {
        User user=this.userRepository.findById(id).get();
        List<Blog> blogs=this.blogRepository.findByUser(user);
        List<BlogDTO> blogDTOs=blogs.stream().map(blog->this.modelMapper.map(blog, BlogDTO.class)).collect(Collectors.toList());
        return blogDTOs;
    }

    public List<BlogDTO> getAllBlogs()
    {
        List<Blog> blogs=this.blogRepository.findAll();
        List<BlogDTO> blogDTOs=blogs.stream().map(blog-> {
            BlogDTO blogDTO =  this.modelMapper.map(blog, BlogDTO.class);
            blogDTO.setComments(blog.getComments().stream().map(comment->this.modelMapper.map(comment, CommentDTO.class)).collect(Collectors.toList()));
            return blogDTO;
        }).collect(Collectors.toList());

        return blogDTOs;
    }

    public BlogDTO getBlogById(Integer id)
    {
        Blog blog=this.blogRepository.findById(id).get();
        return this.modelMapper.map(blog,BlogDTO.class);
    }

    public BlogDTO createBlog(BlogDTO blogDTO,Integer userId,Integer categoryId)
    {
        User user=userRepository.findById(userId).get();
        Category category=categoryRepository.findById(categoryId).get();

        Blog blog=this.modelMapper.map(blogDTO, Blog.class);
        blog.setAddDate(new Date());
        blog.setUser(user);
        blog.setCategory(category);

        Blog newBlog=this.blogRepository.save(blog);
        return this.modelMapper.map(newBlog,BlogDTO.class);
    }

    public BlogDTO updateBlog(BlogDTO blogDTO,Integer blogId)
    {
        Blog blog=this.blogRepository.findById(blogId).get();

        blog.setTitle(blogDTO.getTitle());
        blog.setContent(blogDTO.getContent());
        blog.setImageName(blogDTO.getImageName());

        Blog updatedBlog=this.blogRepository.save(blog);
        return this.modelMapper.map(updatedBlog,BlogDTO.class);
    }

    public boolean deletePost(Integer id)
    {
        if(this.blogRepository.existsById(id))
        {
            this.blogRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<BlogDTO> searchBlogs(String keyword)
    {
        List<Blog> blogs=this.blogRepository.findByTitleContaining(keyword);
        List<BlogDTO> blogDTOs=blogs.stream().map(blog->this.modelMapper.map(blog, BlogDTO.class)).collect(Collectors.toList());
        return blogDTOs;
    }

}
