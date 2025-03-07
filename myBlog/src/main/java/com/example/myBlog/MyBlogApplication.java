package com.example.myBlog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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



