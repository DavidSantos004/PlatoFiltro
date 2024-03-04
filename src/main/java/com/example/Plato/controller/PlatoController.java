package com.example.Plato.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.example.Plato.model.Plato;
import com.example.Plato.service.PlatoService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/platos")
@CrossOrigin("*")

public class PlatoController {

    private final PlatoService platoService;

    @Autowired
    public PlatoController(PlatoService platoService){
        this.platoService = platoService;
    }

    @CrossOrigin("*")
    @GetMapping("")
    public List<Plato> getAllPlatos(){
     return platoService.getAllPlatos();   
    }

    @CrossOrigin("*")
    @GetMapping("/{id}")
    public ResponseEntity<Plato> getPlatoById(@PathVariable Integer id) {
        Optional<Plato> plato = platoService.getPlatoById(id);
        return plato.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @CrossOrigin("*")
    @PostMapping
    public Plato savePlato(@RequestBody Plato plato) {
        return platoService.savePlato(plato);
    }

    @CrossOrigin("*")
    @DeleteMapping("/{id}")
    public void deletePlato(@PathVariable Integer id){
        platoService.deletePlato(id);
    }
    
}
