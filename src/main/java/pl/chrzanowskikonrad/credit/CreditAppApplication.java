package pl.chrzanowskikonrad.credit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"pl.chrzanowskikonrad.credit"})
public class CreditAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditAppApplication.class, args);
	}

}
