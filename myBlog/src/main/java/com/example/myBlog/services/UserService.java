package com.example.myBlog.services;

import com.example.myBlog.dto.UserDTO;
import com.example.myBlog.entities.User;
import com.example.myBlog.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<UserDTO> getAllUsers()
    {
        List<User> users = this.userRepository.findAll();

        List<UserDTO> userDTOs = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

        return userDTOs;
    }

    public UserDTO getUserById(int id)
    {
        User user = this.userRepository.findById(id).get();
        return this.userToDto(user);
    }

    public UserDTO createUser(UserDTO userDTO)
    {
        //return userRepository.save(user);
        User user = this.dtoToUser(userDTO);
        User savedUser = this.userRepository.save(user);
        return this.userToDto(savedUser);
    }

    public UserDTO updateUser(int id, UserDTO updatedUserDTO)
    {
        User user=this.userRepository.findById(id).orElse(null);

        user.setName(updatedUserDTO.getName());
        user.setEmail(updatedUserDTO.getEmail());
        user.setPassword(updatedUserDTO.getPassword());
        user.setAbout(updatedUserDTO.getAbout());

        User updatedUser = this.userRepository.save(user);
        return this.userToDto(updatedUser);
    }

    public boolean deleteUser(int id)
    {
        if (this.userRepository.existsById(id))
        {
            this.userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //MODEL MAPPER
    public User dtoToUser(UserDTO userDTO)
    {
        User user=modelMapper.map(userDTO, User.class);
        return user;
    }
    public UserDTO userToDto(User user)
    {
        UserDTO userDTO=modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

}
