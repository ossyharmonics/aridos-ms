package cl.aridos.camion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CamionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamionApplication.class, args);
	}

}
