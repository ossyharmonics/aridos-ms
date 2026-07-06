package com.aridos.material.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aridos.material.model.Material;
import com.aridos.material.repository.MaterialRepository;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initData(MaterialRepository materialRepo) {
        return args -> {
            if (materialRepo.count() > 0) {
                System.out.println("La base de datos ya tiene registros");
            } else {
                Material m1 = new Material(null, "Arena Gruesa 3/8", 22200);
                Material m2 = new Material(null, "Bolones", 14200);
                Material m3 = new Material(null, "Estabilizado 1 1/2", 11900);
                Material m4 = new Material(null, "Gravilla 3/4", 16200);
                Material m5 = new Material(null, "Ripio", 14200);

                materialRepo.save(m1);
                materialRepo.save(m2);
                materialRepo.save(m3);
                materialRepo.save(m4);
                materialRepo.save(m5);

                System.out.println("Datos cargados correctamente.");
            }
        };
    }
}
