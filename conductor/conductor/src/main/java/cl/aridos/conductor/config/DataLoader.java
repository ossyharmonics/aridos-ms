package cl.aridos.conductor.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.aridos.conductor.model.Conductor;
import cl.aridos.conductor.repository.ConductorRepository;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initData(ConductorRepository conRepo){
        return args -> {
            if (conRepo.count()> 0){
                System.out.println("La base de datos ya contiene registros.");
            }else{
                Conductor c1 = new Conductor(null, "A4");
                Conductor c2 = new Conductor(null, "A2");
                Conductor c3 = new Conductor(null, "A4");

                conRepo.save(c1);
                conRepo.save(c2);
                conRepo.save(c3);

                System.out.println("Datos cargados Correctamente.");
            };
        };
    }
}
