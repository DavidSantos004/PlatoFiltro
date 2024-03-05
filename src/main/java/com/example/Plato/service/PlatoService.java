package com.example.Plato.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Plato.repository.PlatoRepository;
import com.example.Plato.model.Plato;
import com.example.Plato.model.Plato.PlatoBuilder;

import java.util.List;
import java.util.Optional;

@Service
public class PlatoService {
    @Autowired
    private final PlatoRepository platoRepository;

    @Autowired
    public PlatoService(PlatoRepository platoRepository){
        this.platoRepository = platoRepository;
    }

    public List<Plato> getAllPlatos(){
        return platoRepository.findAll();
    }

    public Optional<Plato> getPlatoById(Integer id){
        return platoRepository.findById(id);
    }

    public Plato savePlato(Plato plato){
        return platoRepository.save(plato);
    }

    public void deletePlato(Integer id){
        platoRepository.deleteById(id);
    }

    public Plato updatePlato(Integer id, Plato updatedPlato) {
        Plato existingPlato = platoRepository.findById(id).orElse(null);

        if (existingPlato != null) {
            existingPlato.setNombreplato(updatedPlato.getNombreplato());
            existingPlato.setDescripcionplato(updatedPlato.getDescripcionplato());
            existingPlato.setPrecioplato(updatedPlato.getPrecioplato());
            existingPlato.setMaterialplato(updatedPlato.getMaterialplato());
            existingPlato.setColorplato(updatedPlato.getColorplato());

            return platoRepository.save(existingPlato);
        } else {
            
            return null;
        }
    }

}
