package bpe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;


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

	/* CORS : Alternativa para a anotação @CrossOrigin(origins = "http://localhost:4200") que são utilizadas nos Controllers */
	@Bean
	public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

	/* Encrypted (senha) */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
