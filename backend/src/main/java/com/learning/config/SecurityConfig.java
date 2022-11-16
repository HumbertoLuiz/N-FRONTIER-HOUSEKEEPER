package com.learning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.learning.core.enums.UserType;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Order(1)
	@Bean
	public SecurityFilterChain apiSecurityConfig(HttpSecurity http) throws Exception {

//        @Autowired
//        private AccessTokenRequestFilter accessTokenRequestFilter;
//
//        @Autowired
//        private AuthenticationEntryPoint authenticationEntryPoint;
//
//        @Autowired
//        private AccessDeniedHandler accessDeniedHandler;

		http.requestMatchers(requestMatcherCustomizer -> requestMatcherCustomizer.antMatchers("/api/**", "/auth/**"))
				.authorizeRequests(authorizeRequestsCustomizer -> authorizeRequestsCustomizer.anyRequest().permitAll())
				.csrf(csrfCustomizer -> csrfCustomizer.disable())
				.sessionManagement(sessionManagementCustomizer -> sessionManagementCustomizer
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//          .addFilterBefore(accessTokenRequestFilter, UsernamePasswordAuthenticationFilter.class)
//          .exceptionHandling(exceptionHandlingCustomizer ->
//              exceptionHandlingCustomizer
//                 .authenticationEntryPoint(authenticationEntryPoint)
//                 .accessDeniedHandler(accessDeniedHandler)
//          )
				.cors();

		return http.build();
	}

	@Value("${com.learning.rememberMe.key}")
	private String rememberMeKey;

	@Value("${com.learning.rememberMe.validitySeconds}")
	private int rememberMeValiditySeconds;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Order(2)
	@Bean
	public SecurityFilterChain WebSecurityConfig(HttpSecurity http) throws Exception {

		http.requestMatchers(requestMatcherCustomizer -> requestMatcherCustomizer.antMatchers("/admin/**"))
				.authorizeRequests(authorizeRequestsCustomizer -> authorizeRequestsCustomizer.antMatchers("/api/**")
						.permitAll().antMatchers("/admin/reset-password/**").permitAll().anyRequest()
						.hasAnyAuthority(UserType.ADMIN.name()))
				.formLogin(
						formLoginCustomizer -> formLoginCustomizer.loginPage("/admin/login").usernameParameter("email")
								.passwordParameter("password").defaultSuccessUrl("/admin/jobs").permitAll())
				.logout(logoutCustomizer -> logoutCustomizer
						.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout", "GET"))
						.logoutSuccessUrl("/admin/login"))
				.rememberMe(rememberMeCustomizer -> rememberMeCustomizer.rememberMeParameter("remember-me")
						.tokenValiditySeconds(rememberMeValiditySeconds).key(rememberMeKey))
				.exceptionHandling(
						exceptionHandlingCustomizer -> exceptionHandlingCustomizer.accessDeniedPage("/admin/login"));

		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/img/**", "/js/**", "/webjars/**");
	}

}
