package cl.aridos.direccion.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.aridos.direccion.model.Comuna;
import cl.aridos.direccion.model.Direccion;
import cl.aridos.direccion.model.Region;
import cl.aridos.direccion.repository.ComunaRepository;
import cl.aridos.direccion.repository.DireccionRepository;
import cl.aridos.direccion.repository.RegionRepository;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initData(RegionRepository regRepo, ComunaRepository comRepo, DireccionRepository dirRepo){
        return args -> {
            if(regRepo.count() > 0){
                
                System.out.println("La base de datos ya contiene resgistros.");

            }else{

                Region r1 = new Region(null, "Region Metropolitana");

                regRepo.save(r1);

                Comuna c1 = new Comuna(null, "Quilicura", r1);
                Comuna c2 = new Comuna(null, "Colina", r1);
                Comuna c3 = new Comuna(null, "Pudahuel", r1);
                Comuna c4 = new Comuna(null, "Lampa", r1);

                comRepo.save(c1);
                comRepo.save(c2);
                comRepo.save(c3);
                comRepo.save(c4);

                Direccion d1 = new Direccion(null, "Condominio Santa Rosa", "1340B", c4);
                Direccion d2 = new Direccion(null, "Chamicero", "850", c2);
                Direccion d3 = new Direccion(null, "El Molino", "570", c1);
                Direccion d4 = new Direccion(null, "San Martin", "4228", c4);

                dirRepo.save(d1);
                dirRepo.save(d2);
                dirRepo.save(d3);
                dirRepo.save(d4);

                System.out.println("Datos cargados corresctamente");

            };      
        };
    }

}
