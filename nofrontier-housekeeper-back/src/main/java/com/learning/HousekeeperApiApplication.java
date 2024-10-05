package com.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableConfigurationProperties
@ComponentScan({
    "com.learning",
    "com.learning.config",
    "com.learning.api.controllers",
    "com.learning.api.mappers",
    "com.learning.api.services", 
    "com.learning.core.domain.models",
    "com.learning.core.repository",
    "com.learning.core.services"
})
@EnableJpaRepositories(basePackages = "com.learning.core.repository")
@SpringBootApplication(scanBasePackages = {
	    "com.learning",
	    "com.learning.config",
	    "com.learning.api.controllers",
	    "com.learning.api.mappers",
	    "com.learning.api.services", 
	    "com.learning.core.domain.models",
	    "com.learning.core.repository",
	    "com.learning.core.services"
})
public class HousekeeperApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HousekeeperApiApplication.class, args);
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
