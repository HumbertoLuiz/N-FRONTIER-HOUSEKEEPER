package br.com.xfrontier.housekeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableConfigurationProperties
@ComponentScan({
    "com.learning",
    "br.com.xfrontier.housekeeper.config",
    "br.com.xfrontier.housekeeper.api.controllers",
    "br.com.xfrontier.housekeeper.api.mappers",
    "br.com.xfrontier.housekeeper.api.services", 
    "br.com.xfrontier.housekeeper.core.domain.models",
    "br.com.xfrontier.housekeeper.core.repository",
    "br.com.xfrontier.housekeeper.core.services"
})
@EnableJpaRepositories(basePackages = "br.com.xfrontier.housekeeper.core.repository")
@SpringBootApplication(scanBasePackages = {
	    "com.learning",
	    "br.com.xfrontier.housekeeper.config",
	    "br.com.xfrontier.housekeeper.api.controllers",
	    "br.com.xfrontier.housekeeper.api.mappers",
	    "br.com.xfrontier.housekeeper.api.services", 
	    "br.com.xfrontier.housekeeper.core.domain.models",
	    "br.com.xfrontier.housekeeper.core.repository",
	    "br.com.xfrontier.housekeeper.core.services"
})
public class XfrontierHousekeeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(XfrontierHousekeeperApplication.class, args);
	}
	
//	@Autowired
//	PasswordEncoder passwordEncoder;
//	
//    @Bean
//    CommandLineRunner init(UserRepository userRepository) {
//        return args -> {
//            User user = new User();
//            user.setCompleteName("Test");
//            user.setEmail("test@mail.com");
//            user.setPassword(passwordEncoder.encode("test@123"));            
//            user.setUserType(UserType.ADMIN);
//            // Preencha os outros campos se necessários
//            userRepository.save(user);
//        };
//    }

}
