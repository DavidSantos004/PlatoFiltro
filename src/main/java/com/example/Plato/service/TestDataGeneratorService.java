package com.example.Plato.service;

import com.example.Plato.model.*;
import com.example.Plato.model.Plato.PlatoBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.time.*;

@Service
public class TestDataGeneratorService {
    private final PlatoService platoService;

    @Autowired
    public TestDataGeneratorService(
            PlatoService platoService) {
        this.platoService = platoService;
    }

    
    public void generateTestData() throws ParseException {
        generatePlatos();
    }

    private void generatePlatos() throws ParseException  {
        Plato plato = Plato.builder()
        .nombreplato("PlatoCaucho")
        .descripcionplato("El Plato es re caucho")
        .precioplato(1.500)
        .colorplato("rojo")
        .materialplato("Plastico")
        .build();
        platoService.savePlato(plato);

        Plato plato1 = Plato.builder()
        .nombreplato("PlatoNoCaucho")
        .descripcionplato("El Plato no es caucho")
        .precioplato(1.500)
        .colorplato("negro")
        .materialplato("vidrio")
        .build();
        platoService.savePlato(plato1);

        Plato plato2 = Plato.builder()
        .nombreplato("PlatoMedioCaucho")
        .descripcionplato("El Plato es medio caucho")
        .precioplato(1.500)
        .colorplato("amarillo")
        .materialplato("Barro")
        .build();
        platoService.savePlato(plato2);

        Plato plato3 = Plato.builder()
        .nombreplato("PlatoParaNadaCaucho")
        .descripcionplato("El Plato no es para nada caucho")
        .precioplato(1.500)
        .colorplato("verde")
        .materialplato("aire")
        .build();
        platoService.savePlato(plato3);
    }

}
