package com.lihang.web.controller;

import org.apache.commons.io.IOUtils;
import org.hibernate.result.Output;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/file")
public class FileController {
  private  String path = "E:\\Program2\\security\\security-demo\\src\\main\\resources\\file";
    @PostMapping("/upload")
    public File uploadFile(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getSize());
        System.out.println(file.getOriginalFilename());

        File newFile = new File(path,new Date().getTime()+".txt");
        file.transferTo(newFile);
        return new File(newFile.getAbsolutePath());
    }
    @GetMapping("/download/{id}")
    public void downloadFile(@PathVariable String id, HttpServletRequest request, HttpServletResponse response){
        try (
                InputStream in = new FileInputStream(new File(path,id+".txt"));
                OutputStream out = response.getOutputStream();
                ){
                response.setContentType("application/x-download");
                response.addHeader("Content-Disposition","attachment;filename=test.txt");
                IOUtils.copy(in,out);
                out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
