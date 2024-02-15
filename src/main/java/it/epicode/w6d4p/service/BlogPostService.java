package it.epicode.w6d4p.service;

import org.springframework.stereotype.Service;
import it.epicode.w6d4p.exception.NotFoundException;
import it.epicode.w6d4p.model.Autore;
import it.epicode.w6d4p.model.BlogPost;
import it.epicode.w6d4p.model.BlogPostRequest;
import it.epicode.w6d4p.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class BlogPostService {
    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private AutoreService autoreService;

    public Page<BlogPost> cercaTuttiIBlogPosts(Pageable pageable){
        return  blogPostRepository.findAll(pageable);
    }

    public BlogPost cercaPostPerId(int id) throws NotFoundException{
        return blogPostRepository.findById(id).
                orElseThrow(()->new NotFoundException("BlogPost con id="+id+" non trovato"));
    }

    public BlogPost salvaBlogPost(BlogPostRequest blogPostRequest) throws NotFoundException{
        Autore autore = autoreService.cercaAutorePerId(blogPostRequest.getIdAutore());

        BlogPost blogPost = new BlogPost(blogPostRequest.getContenuto(), blogPostRequest.getTitolo(),
                blogPostRequest.getCategoria(), blogPostRequest.getTempoLettura(), autore);

        return blogPostRepository.save(blogPost);

    }

    public BlogPost aggiornaBlogPost(int id, BlogPostRequest blogPostRequest) throws NotFoundException{
        BlogPost post = cercaPostPerId(id);

        Autore autore = autoreService.cercaAutorePerId(blogPostRequest.getIdAutore());

        post.setCategoria(blogPostRequest.getCategoria());
        post.setContenuto(blogPostRequest.getContenuto());
        post.setTitolo(blogPostRequest.getTitolo());
        post.setTempoLettura(blogPostRequest.getTempoLettura());
        post.setAutore(autore);

        return blogPostRepository.save(post);
    }

    public void cancellaBlogPost(int id) throws NotFoundException{
        BlogPost post = cercaPostPerId(id);
        blogPostRepository.delete(post);
    }

    public BlogPost uploadcover(int id, String url) throws NotFoundException{
        BlogPost blogPost = cercaPostPerId(id);

        blogPost.setCover(url);
        return blogPostRepository.save(blogPost);
    }
}
