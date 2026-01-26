package com.example.magiaepigia.controllers;

import com.example.magiaepigia.models.Seed;
import com.example.magiaepigia.services.SeedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/saludapi/v1/seeds")
public class SeedController {

    @Autowired
    SeedService seedService;

    
    @PostMapping
    public ResponseEntity<Seed> guardar(@RequestBody Seed datos) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.seedService.save(datos));
    }

   
    @GetMapping
    public ResponseEntity<List<Seed>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(this.seedService.getAll());
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Seed> buscarPorId(@PathVariable Long id) {
        // Como el servicio devuelve un Optional, usamos .orElse(null) 
        // para que si no lo encuentra, devuelva vacío sin error.
        return ResponseEntity.status(HttpStatus.OK).body(this.seedService.getById(id).orElse(null));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Seed> actualizar(@PathVariable Long id, @RequestBody Seed datos) {
        return ResponseEntity.status(HttpStatus.OK).body(this.seedService.update(id, datos));
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        this.seedService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}