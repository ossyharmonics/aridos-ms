package cl.aridos.persona.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.aridos.persona.model.Persona;
import cl.aridos.persona.repository.PersonaRepository;


@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initData(PersonaRepository persoRepo){
        return args -> {

            Persona p1 = new Persona("14138994-7", "Victor", null, "Argandoña", "Argandoña");
            Persona p2 = new Persona("14124173-7", "Nicolas", null, "Rojas", "Castillo");
            Persona p3 = new Persona("9904442-K", "Emilo", "Armando", "Lopez",  "Rodriguez");
            Persona p4 = new Persona("10439301-8", "Esteban", null, "Rojas", "Argandoña");
           
            persoRepo.save(p1);
            persoRepo.save(p2);
            persoRepo.save(p3);
            persoRepo.save(p4);

            System.out.println("Datos cargados correctamente.");

        };


    }
}
