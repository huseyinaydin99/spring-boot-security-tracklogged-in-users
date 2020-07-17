package com.huseyinaydin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

@Controller
public class UploadController {
    @Value("${upload.path}")
    private String UPLOAD_DIR;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        /*UPLOAD_DIR = UploadController.class.getResource("").getPath();
        System.out.println("File dir " + UPLOAD_DIR);*/
        /*File file1 = new File("/");
        if (file1.exists()) {
            System.out.println("Klasör var");
            String[] files = file1.list();
            Arrays.stream(files).forEach(i-> System.out.println(i));
        }
        else
            System.out.println("Klasör yok");*/
        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Lütfen yüklemek istediğiniz dosyayı seçin!");
            return "redirect:/";
        }
        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            System.out.println(path.getRoot().toString());
            System.out.println(this.UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // return success response
        attributes.addFlashAttribute("message", "Dosya başarı ile yüklendi " + fileName);
        return "redirect:/";
    }

    @GetMapping(value = "/api/image/{image}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getImage(@PathVariable String image, HttpServletResponse response) {
        System.out.println("get image metodu çalıştı!");
        try {
            File file = new File(UPLOAD_DIR + image + ".jpg");
            System.out.println(file.getAbsolutePath());
            if(file.exists()){
                InputStream targetStream = new FileInputStream(file);
                return ResponseEntity
                        .ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(new InputStreamResource(targetStream));
            }

        } catch (Throwable t) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("Hata var");
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body("Resim bulunamadı");
    }
}
