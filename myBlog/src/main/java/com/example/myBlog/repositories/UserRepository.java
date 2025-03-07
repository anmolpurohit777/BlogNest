package com.example.myBlog.repositories;

import com.example.myBlog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

//UserRepo
public interface UserRepository extends JpaRepository<User, Integer>
{

}
