package com.example.myBlog.services;

import com.example.myBlog.entities.User;
import com.example.myBlog.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    public boolean isExistingUser(String email, String password )
    {
        User user = userRepository.findByEmail(email);
        if (user==null || !user.getPassword().equals(password))
        {
            return false;
        }
        return true;
    }
}
