package com.example.myBlog.controllers;

import com.example.myBlog.dto.CommentDTO;
import com.example.myBlog.entities.Comment;
import com.example.myBlog.repositories.CommentRepository;
import com.example.myBlog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class CommentController
{
    @Autowired
    CommentService commentService;

    @PostMapping("/blog/{blogId}/comments")
    public ResponseEntity<?> createComment(@RequestBody CommentDTO commentDTO, @PathVariable int blogId)
    {
        try{
            CommentDTO newCommentDTO = this.commentService.createComment(commentDTO, blogId);
            return ResponseEntity.ok(newCommentDTO);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error creating comment: " + e.getMessage());
        }
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int commentId)
    {
        try{
            boolean deleted= this.commentService.deleteComment(commentId);
            if(deleted)
            {
                return ResponseEntity.ok("Comment deleted successfully.");
            }
            else
            {
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Error deleting comment: " + e.getMessage());
        }
    }
}
