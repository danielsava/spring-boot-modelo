package bpe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *  	Anotações de Configurações que pode ser necessárias caso os pacotes não sejam sub-pacotes desta classe (de inicialização e configuração do Spring)
 *
 * 		- @EntityScan : Can be used if the entity package is not a sub-package of the main Spring application package.
 * 		- @EnableJpaRepositories(basePackages = "com.springbootdev.examples.jpa.repositories") : But if the repositories package is not a sub-package of the Spring main class package, in that case, we need to declare the repositories packages, as shown
 *
 *
 */


@SpringBootApplication
public class Application {


	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


}
