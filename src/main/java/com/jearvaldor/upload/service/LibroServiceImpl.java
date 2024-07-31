package com.jearvaldor.upload.service;

import com.jearvaldor.upload.model.Libro;
import com.jearvaldor.upload.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroServiceImpl implements LibroService{

    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private UploadService uploadService;

    String url = "http://localhost:8080/upload/";
    @Override
    public Libro save(Libro libro, MultipartFile file) throws IOException {
        String nombre = uploadService.saveUpload(file);
        libro.setNombre(nombre);
        return libroRepository.save(libro);
    }

    @Override
    public List<Libro> getLibros() {
        List<Libro> libros = libroRepository.findAll();
        libros = libros.stream().map(libro -> {libro.setNombre(url + libro.getNombre());
                return libro;
        }).collect(Collectors.toList());
        return libros;
    }

    @Override
    public Libro get(Long id) {
        Libro libro = libroRepository.findById(id).get();
        libro.setNombre(url+ libro.getNombre());
        return libro;
    }

    @Override
    public Libro update(Long id, Libro libro, MultipartFile file) throws IOException {
        Libro libro1 = libroRepository.findById(id).get();
        String nombre = uploadService.saveUpload(file);
        if (!libro1.getNombre().equals(nombre)){
            uploadService.deleteUpload(libro1.getNombre());
        }
        libro.setId(id);
        libro.setNombre(nombre);
        return libroRepository.save(libro);
    }

    @Override
    public void detete(Long id) throws IOException {
        Libro libro = libroRepository.findById(id).get();
        String nombre = libro.getNombre();
        uploadService.deleteUpload(nombre);
        libroRepository.delete(libro);

    }
}
