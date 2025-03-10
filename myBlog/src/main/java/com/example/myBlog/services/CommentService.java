package com.example.myBlog.services;

import com.example.myBlog.dto.CommentDTO;
import com.example.myBlog.entities.Blog;
import com.example.myBlog.entities.Comment;
import com.example.myBlog.repositories.BlogRepository;
import com.example.myBlog.repositories.CommentRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService
{
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    ModelMapper modelMapper;

    public CommentDTO createComment(CommentDTO commentDTO,Integer blogId)
    {
        Blog blog = this.blogRepository.findById(blogId).get();
        Comment comment = this.modelMapper.map(commentDTO, Comment.class);
        comment.setBlog(blog);

        Comment savedComment = this.commentRepository.save(comment);
        return this.modelMapper.map(savedComment, CommentDTO.class);
    }

    public boolean deleteComment(Integer id) {
        if(commentRepository.existsById(id))
        {
            Comment comment = this.commentRepository.findById(id).get();
            Blog blog  = this.blogRepository.findById(comment.getBlog().getBlogId()).get();
            blog.getComments().remove(comment);
            this.commentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
