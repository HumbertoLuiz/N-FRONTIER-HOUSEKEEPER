package com.learning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.learning.core.enums.UserType;
import com.learning.core.filters.AccessTokenRequestFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Autowired
	private AccessTokenRequestFilter accessTokenRequestFilter;

	@Value("${com.learning.rememberMe.key}")
	private String rememberMeKey;

	@Value("${com.learning.rememberMe.validitySeconds}")
	private int rememberMeValiditySeconds;

	private static final String[] SWAGGER_WHITELIST = {"/v3/api-docs/**",
			"/swagger-resources/**", "/configuration/ui/**",
			"/configuration/security/**", "/swagger-ui.html/**",
			"/swagger-ui/**", "/webjars/**"};

	@Bean
	@Order(1)
	SecurityFilterChain apiSecurityFilterChain(HttpSecurity http)
			throws Exception {
		http.httpBasic(AbstractHttpConfigurer::disable);

		http.securityMatchers(
				securityMatchersCustomizer -> securityMatchersCustomizer
						.requestMatchers("/api/**", "/auth/**"));

		http.authorizeHttpRequests(
				authorizeHttpRequestsCustomizer -> authorizeHttpRequestsCustomizer
						.requestMatchers(SWAGGER_WHITELIST).permitAll()
						.anyRequest().permitAll());

		http.csrf(AbstractHttpConfigurer::disable);

		http.sessionManagement(
				sessionManagementCustomizer -> sessionManagementCustomizer
						.sessionCreationPolicy(
								SessionCreationPolicy.STATELESS));

		http.addFilterBefore(accessTokenRequestFilter,
				UsernamePasswordAuthenticationFilter.class).exceptionHandling(
						exceptionHandlingCustomizer -> exceptionHandlingCustomizer
								.authenticationEntryPoint(
										authenticationEntryPoint)
								.accessDeniedHandler(accessDeniedHandler));

		http.cors(cors -> {
		});

		return http.build();
	}

	@Bean
	@Order(2)
	SecurityFilterChain webSecurityFilterChain(HttpSecurity http)
			throws Exception {
		http.authorizeHttpRequests(
				requestMatcherCustomizer -> requestMatcherCustomizer
						.requestMatchers("/admin/**"))
				.authorizeHttpRequests(
						authorizeRequestsCustomizer -> authorizeRequestsCustomizer
								.requestMatchers("/admin/**").permitAll()
								.requestMatchers("/admin/reset-password/**")
								.permitAll().anyRequest()
								.hasAuthority(UserType.ADMIN.name()))
				.formLogin(formLoginCustomizer -> formLoginCustomizer
						.loginPage("/admin/login").usernameParameter("email")
						.passwordParameter("password")
						.defaultSuccessUrl("/admin/jobs").permitAll())
				.logout(logoutCustomizer -> logoutCustomizer
						.logoutRequestMatcher(new AntPathRequestMatcher(
								"/admin/logout", "GET"))
						.logoutSuccessUrl("/admin/login"))
				.rememberMe(rememberMeCustomizer -> rememberMeCustomizer
						.rememberMeParameter("remember-me")
						.tokenValiditySeconds(rememberMeValiditySeconds)
						.key(rememberMeKey))
				.exceptionHandling(
						exceptionHandlingCustomizer -> exceptionHandlingCustomizer
								.accessDeniedPage("/admin/login"));

		return http.build();
	}

	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().requestMatchers("/webjars/**", "/img/**");
	}

	@Bean
	AuthenticationManager authenticationManager(
			AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

}
