package com.example.myBlog.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileService
{
    public String uploadImage(String path,MultipartFile file) throws IOException
    {
        String name = file.getOriginalFilename();

        String randomId = UUID.randomUUID().toString();
        String filename1=randomId.concat(name.substring(name.lastIndexOf(".")));

        String filePath=path + File.separator + filename1;

        File f=new File(filePath);
        if(!f.exists())
        {
            f.mkdirs();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));
        return name;
    }

    public InputStream getResource(String path, String filename) throws FileNotFoundException
    {
        String fullPath=path+File.separator+filename;
        InputStream is=new FileInputStream(fullPath);

        return is;
    }
}
