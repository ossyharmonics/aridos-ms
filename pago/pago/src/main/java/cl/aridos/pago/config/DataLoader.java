package cl.aridos.pago.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.aridos.pago.model.Pago;
import cl.aridos.pago.model.TipoPago;
import cl.aridos.pago.repository.PagoRepository;
import cl.aridos.pago.repository.TipoPagoRepository;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initData(TipoPagoRepository tipoRepo, PagoRepository pagoRepo){
        return args -> {
            if (tipoRepo.count() > 0){
                
                System.out.println("La base de datos ya tiene registros");
            
            }else{

            TipoPago tp1 = new TipoPago(null, "Efectivo");
            TipoPago tp2 = new TipoPago(null, "Transferencia");
            TipoPago tp3 = new TipoPago(null, "Débito");
            TipoPago tp4 = new TipoPago(null, "Crédito");
           
            tipoRepo.save(tp1);
            tipoRepo.save(tp2);
            tipoRepo.save(tp3);
            tipoRepo.save(tp4);

            Pago p1 = new Pago(null, 120000, tp1);
            Pago p2 = new Pago(null, 150000, tp3);
            Pago p3 = new Pago(null, 180000, tp2);
            Pago p4 = new Pago(null, 2200000, tp4);

            pagoRepo.save(p1);
            pagoRepo.save(p2);
            pagoRepo.save(p3);
            pagoRepo.save(p4);

            System.out.println("Datos cargados correctamente.");

            };
        };
    }
}
