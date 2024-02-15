package it.epicode.w6d4p.controller;

import com.cloudinary.Cloudinary;
import it.epicode.w6d4p.model.Autore;
import it.epicode.w6d4p.model.BlogPost;
import it.epicode.w6d4p.model.BlogPostRequest;
import it.epicode.w6d4p.model.CustomResponse;
import it.epicode.w6d4p.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class BlogPostController {
    @Autowired
    private BlogPostService blogPostService;

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("/post")
    public Page<BlogPost> getAll(Pageable pageable){

        return blogPostService.cercaTuttiIBlogPosts(pageable);
    }
    @GetMapping("/post/{id}")
    public BlogPost getBlogPostById(@PathVariable int id){
        return blogPostService.cercaPostPerId(id);

    }
    @PostMapping("/post")
    public BlogPost saveBlogPost(@RequestBody BlogPostRequest blogPostRequest){
        return blogPostService.salvaBlogPost(blogPostRequest);
    }
    @PutMapping("/post/{id}")
    public BlogPost updateBlogPost(@PathVariable int id, @RequestBody BlogPostRequest blogPostRequest){
        return blogPostService.aggiornaBlogPost(id, blogPostRequest);
    }

    @DeleteMapping("/post/{id}")
    public void deleteBlogPost(@PathVariable int id){
        blogPostService.cancellaBlogPost(id);
    }

    @PatchMapping("/post/{id}/upload")
    public ResponseEntity<CustomResponse> uploadCover(@PathVariable int id, @RequestParam("upload") MultipartFile file){
        try {

            BlogPost blogPost = blogPostService.uploadcover(id, (String) cloudinary.uploader().upload(file.getBytes(), new HashMap()).get("url"));
            return CustomResponse.success(HttpStatus.OK.toString(), blogPost,HttpStatus.OK);

        }catch (IOException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
