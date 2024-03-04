package com.example.Plato;

import org.apache.el.parser.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.Plato.repository.PlatoRepository;
import com.example.Plato.service.PlatoService;
import com.example.Plato.service.TestDataGeneratorService;

@SpringBootApplication
public class PlatoApplication {

	public static void main(String[] args) throws ParseException{
		ConfigurableApplicationContext context = SpringApplication.run(PlatoApplication.class, args);
		PlatoRepository platoRepository = context.getBean(PlatoRepository.class);
		if (platoRepository.count() == 0) {
			TestDataGeneratorService testDataGeneratorService = context.getBean(TestDataGeneratorService.class);
			try {
				testDataGeneratorService.generateTestData();
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("La base de datos ya contiene datos, no se ejecutará TestDataGeneratorService.");
		}

		PlatoService platoService = context.getBean(PlatoService.class);
		testPlatoService(platoService);


	}
	private static void testPlatoService(PlatoService platoService){

	}

}
