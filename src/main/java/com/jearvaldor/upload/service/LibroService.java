package com.jearvaldor.upload.service;

import com.jearvaldor.upload.model.Libro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface LibroService {

    Libro save(Libro libro, MultipartFile file) throws IOException;

    List<Libro> getLibros();

    Libro get(Long id);

    Libro update(Long id, Libro libro, MultipartFile file) throws IOException;

    void detete(Long id) throws IOException;

}
