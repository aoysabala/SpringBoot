package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	ApplicationRunner init(ManuRepository manuRepository, CoffeeTypeRepository coffeetypeRepository,
						   BakeryTypeRepository bakerytypeRepository,
						   ManuTypeRepository manutypeRepository) {
		return args -> {

			Stream.of("-","????","????","????").forEach(coffee ->{
				CoffeeType coffeetype = new CoffeeType();
				coffeetype.setCoffee(coffee);
				coffeetypeRepository.save(coffeetype);
			});
			coffeetypeRepository.findAll().forEach(System.out::println);
			Stream.of("-","SANWICH","CAKE&ROLL","MUFFIN").forEach(bakery ->{
				BakeryType bakerytype = new BakeryType();
				bakerytype.setBakery(bakery);
				bakerytypeRepository.save(bakerytype);
			});
			bakerytypeRepository.findAll().forEach(System.out::println);
			Stream.of("Coffee","Bakery").forEach(menu -> {
				ManuType manutype = new ManuType();
				manutype.setManu(menu);
				manutypeRepository.save(manutype);
			});
			manutypeRepository.findAll().forEach(System.out::println);

			Manu manu = new Manu();
			manu.setName("??????????");
			manu.setPrice(40);
			manu.setManutype(manutypeRepository.getOne(1L));
			manu.setCoffeetype(coffeetypeRepository.getOne(2L));
			manu.setBakerytype(bakerytypeRepository.getOne(1L));
			manuRepository.save(manu);




		};
	}
}
