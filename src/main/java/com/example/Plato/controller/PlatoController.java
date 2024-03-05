package com.example.Plato.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Plato.model.Plato;
import com.example.Plato.service.PlatoService;

@RestController
@RequestMapping("/api/platos")
@CrossOrigin("*")

public class PlatoController {

    private final PlatoService platoService;

    @Autowired
    public PlatoController(PlatoService platoService) {
        this.platoService = platoService;
    }

    @CrossOrigin("*")
    @GetMapping("")
    public List<Plato> getAllPlatos() {
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
    @PutMapping("/{id}")
    public ResponseEntity<Plato> updatePlato(@PathVariable Integer id, @RequestBody Plato updatedPlato) {
        Optional<Plato> existingPlatoOptional = platoService.getPlatoById(id);

        if (existingPlatoOptional.isPresent()) {
            Plato existingPlato = existingPlatoOptional.get();

            existingPlato.setNombreplato(updatedPlato.getNombreplato());
            existingPlato.setDescripcionplato(updatedPlato.getDescripcionplato());
            existingPlato.setPrecioplato(updatedPlato.getPrecioplato());
            existingPlato.setMaterialplato(updatedPlato.getMaterialplato());
            existingPlato.setColorplato(updatedPlato.getColorplato());

            Plato savedPlato = platoService.savePlato(existingPlato);
            return ResponseEntity.ok(savedPlato);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    

    @CrossOrigin("*")
    @DeleteMapping("/{id}")
    public void deletePlato(@PathVariable Integer id) {
        platoService.deletePlato(id);
    }

}
