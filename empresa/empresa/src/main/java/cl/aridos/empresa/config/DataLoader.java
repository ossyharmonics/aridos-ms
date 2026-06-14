package cl.aridos.empresa.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.aridos.empresa.model.Empresa;
import cl.aridos.empresa.model.TipoGiro;
import cl.aridos.empresa.repository.EmpresaRepository;
import cl.aridos.empresa.repository.TipoGiroRepository;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initData(TipoGiroRepository tipoRepo, EmpresaRepository empRepo){
        return args -> {
            if (tipoRepo.count() > 0){

                System.out.println("La base de datos ya contiene registros.");
            
            }else{

                TipoGiro t1 = new TipoGiro(null, "Transporte de carga");
                TipoGiro t2 = new TipoGiro(null, "Compra y venta de áridos");
                TipoGiro t3 = new TipoGiro(null, "Construcción");
                TipoGiro t4 = new TipoGiro(null, "Obras viales");

                tipoRepo.save(t1);
                tipoRepo.save(t2);
                tipoRepo.save(t3);
                tipoRepo.save(t4);

                Empresa emp1 = new Empresa("77944929-7", "Urbaterra", t3);
                Empresa emp2 = new Empresa("76223805-5", "Comercializadora Frank Orellana", t2);
                Empresa emp3 = new Empresa("79730570-7", "Sicomaq", t4);
                Empresa emp4 = new Empresa("76520501-8", "Transportes Cristian Seguel", t1);

                empRepo.save(emp1);
                empRepo.save(emp2);
                empRepo.save(emp3);
                empRepo.save(emp4);

                System.out.println("Datos cargados correctamente.");
            };
        };
    } 
}