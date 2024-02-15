package it.epicode.w6d4p.service;

import it.epicode.w6d4p.model.BlogPost;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import it.epicode.w6d4p.exception.NotFoundException;
import it.epicode.w6d4p.model.Autore;
import it.epicode.w6d4p.model.AutoreRequest;
import it.epicode.w6d4p.repository.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class AutoreService {
    @Autowired
    private AutoreRepository autoreRepository;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public Page<Autore> cercaTuttiGliAutori(Pageable pageable){
        return autoreRepository.findAll(pageable);
    }

    public Autore cercaAutorePerId(int id){
        return autoreRepository.findById(id).
                orElseThrow(()->new NotFoundException("Autore con id="+ id + " non trovato"));
    }

    public Autore salvaAutore(AutoreRequest autoreRequest){
        Autore autore = new Autore();
        autore.setNome(autoreRequest.getNome());
        autore.setCognome(autoreRequest.getCognome());
        autore.setEmail(autoreRequest.getEmail());
        autore.setDataNascita(autoreRequest.getDataNascita());

        sendMail(autore.getEmail());

        return autoreRepository.save(autore);
    }

    public Autore aggiornaAutore(int id, AutoreRequest autoreRequest) throws NotFoundException{
        Autore a = cercaAutorePerId(id);

        a.setNome(autoreRequest.getNome());
        a.setCognome(autoreRequest.getCognome());
        a.setEmail(autoreRequest.getEmail());
        a.setDataNascita(autoreRequest.getDataNascita());

        return autoreRepository.save(a);
    }

    public void cancellaAutore(int id) throws NotFoundException{
        Autore a = cercaAutorePerId(id);

        autoreRepository.delete(a);
    }

    public Autore uploadAvatar(int id, String url) throws NotFoundException{
        Autore autore = cercaAutorePerId(id);

        autore.setAvatar(url);
        return autoreRepository.save(autore);
    }

    public void sendMail(String emalil){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emalil);
        message.setTo("Registrazione Servizio Rest");
        message.setText("Registrazione al servizio avvenuta con successo");

        javaMailSender.send(message);
    }
}
