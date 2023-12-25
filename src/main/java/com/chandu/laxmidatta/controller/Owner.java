package com.chandu.laxmidatta.controller;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.chandu.laxmidatta.model.Image;
import com.chandu.laxmidatta.service.ImageService;

import jakarta.servlet.http.HttpServletRequest;

//import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@RestController
public class Owner {
    @Autowired
    private ImageService imageService;

    @GetMapping("/ping")
    @ResponseBody
    public String hello_world(){
        return "Hello World!";
    }

    // display image
    @GetMapping("/display")
    public ResponseEntity<byte[]> displayImage(@RequestParam("id") long id) throws IOException, SQLException
    {
        Image image = imageService.viewById(id);
        byte [] imageBytes = null;
        imageBytes = image.getImage().getBytes(1,(int) image.getImage().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    // view All images
    @GetMapping("showimages")
    public ModelAndView showimages(){
        ModelAndView mv = new ModelAndView("showimages");
        List<Image> imageList = imageService.viewAll();
        mv.addObject("imageList", imageList);
        return mv;
    }

    // add image - get
    @GetMapping("/add")
    public ModelAndView addImage(){
        return new ModelAndView("addimage");
    }

    // add image - post
    @PostMapping("/add")
    public String addImagePost(HttpServletRequest request,@RequestParam("image") MultipartFile file) throws IOException, SerialException, SQLException
    {
        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

        Image image = new Image();
        image.setImage(blob);
        imageService.create(image);
        return "Uploaded Successfully";
    }
    @GetMapping("/homepage")
    public ModelAndView Home(){
        return new ModelAndView("HomePage");
    }
    @GetMapping("/about")
    public ModelAndView About(){
        return new ModelAndView("about.html");
    }
    @GetMapping("/work")
    public ModelAndView Work(){
        return new ModelAndView("work"); 
    }
    
    @GetMapping("/whatwedo")
    public ModelAndView whatwedo(){
        return new ModelAndView("what-we-do"); 
    }
    @GetMapping("/gallery")
    public ModelAndView gallery(){
        return new ModelAndView("gallery"); 
    }
    @GetMapping("/contact")
    public ModelAndView contact(){
        return new ModelAndView("contact"); 
    }
    
    @GetMapping("/addimage")
    public ModelAndView addimage(){
        return new ModelAndView("addimage"); 
    }
   
    
}