package com.example.myBlog.dto;

import com.example.myBlog.entities.Category;
import com.example.myBlog.entities.Comment;
import com.example.myBlog.entities.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

//BlogDTO
public class BlogDTO
{
    private int id;
    private String title;
    private String content;
    private String imageName;
    private Date addDate;
    private CategoryDTO category;
    private UserDTO user;
    private List<CommentDTO> comments=new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}
