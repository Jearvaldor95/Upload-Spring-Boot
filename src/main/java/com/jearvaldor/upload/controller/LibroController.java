package com.jearvaldor.upload.controller;

import com.jearvaldor.upload.model.Libro;
import com.jearvaldor.upload.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @PostMapping("/libro")
    public ResponseEntity<Libro> save(@RequestPart MultipartFile file, @RequestPart Libro libro) throws IOException {
        return new ResponseEntity<>(libroService.save(libro, file), HttpStatus.CREATED);
    }

    @GetMapping("/libro")
    public ResponseEntity<List<Libro>> getLibros(){
        return new ResponseEntity<>(libroService.getLibros(), HttpStatus.OK);
    }

    @GetMapping("/libro/{id}")
    public ResponseEntity<Libro>  get(@PathVariable Long id){
        return new ResponseEntity<>(libroService.get(id), HttpStatus.OK);
    }

    @PutMapping("/libro/{id}")
    public ResponseEntity<Libro> update(@PathVariable Long id, @RequestPart Libro libro, @RequestPart MultipartFile file) throws IOException {
        return new ResponseEntity<>(libroService.update(id, libro, file), HttpStatus.OK);
    }

    @DeleteMapping("/libro/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws IOException {
        libroService.detete(id);
        return new ResponseEntity<>( HttpStatus.OK) ;
    }

}
