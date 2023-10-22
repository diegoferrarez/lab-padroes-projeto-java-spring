package one.digitalinnovation.gof;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@SpringBootApplication
@EnableFeignClients
public class DIOApplication {

	public static void main(String[] args) {
		SpringApplication.run(DIOApplication.class, args);
	}

}
