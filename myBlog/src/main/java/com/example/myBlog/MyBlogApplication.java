package com.example.myBlog;

import com.cloudinary.Cloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class MyBlogApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(MyBlogApplication.class, args);
		System.out.println("Hello World!");
	}

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}


}



