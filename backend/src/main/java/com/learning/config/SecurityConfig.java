package com.learning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.learning.core.enums.UserType;
import com.learning.core.filters.AccessTokenRequestFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Order(1)
    @Configuration
    public class ApiSecurityConfig {

        @Autowired
        private AccessTokenRequestFilter accessTokenRequestFilter;

        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        }

        @Bean
        AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
            return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        SecurityFilterChain configure(HttpSecurity http) throws Exception {

//            http.securityMatchers(securityMatchersCustomizer -> securityMatchersCustomizer
//                .requestMatchers("/api/**", "/auth/**"));

            http.authorizeHttpRequests(
                authorizeHttpRequestsCustomizer -> authorizeHttpRequestsCustomizer
                    .anyRequest().permitAll());

//            http.authorizeHttpRequests(
//                authorizeHttpRequestsCustomizer -> authorizeHttpRequestsCustomizer
//                    .requestMatchers("/uploads").permitAll()
//                    .requestMatchers("/api/**").permitAll()
//                    .requestMatchers("/auth/**").permitAll()
//                    .requestMatchers("/admin/**").hasAuthority(UserType.ADMIN.toString())
//                    .anyRequest().permitAll());
//
//            http.formLogin(formLoginCustomizer -> formLoginCustomizer
//                .loginPage("/admin/login")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/admin/jobs")
//                .permitAll());
//
//            http.logout(logoutCustomizer -> logoutCustomizer
//                .logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout", "GET"))
//                .logoutSuccessUrl("/admin/login"));

            http.sessionManagement(sessionManagementCustomizer -> sessionManagementCustomizer
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

            http.addFilterBefore(accessTokenRequestFilter,
                UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandlingCustomizer -> exceptionHandlingCustomizer
                    .authenticationEntryPoint(authenticationEntryPoint)
                    .accessDeniedHandler(accessDeniedHandler));

            http.csrf(AbstractHttpConfigurer::disable);
            http.cors(AbstractHttpConfigurer::disable);

            return http.build();
        }
    }


    @Order(2)
    @Configuration
    public class WebSecurityConfig {

        @Value("${com.learning.rememberMe.key}")
        private String rememberMeKey;

        @Value("${com.learning.rememberMe.validitySeconds}")
        private int rememberMeValiditySeconds;

        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        }

        protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

            http.authorizeHttpRequests(
                requestMatcherCustomizer -> requestMatcherCustomizer.requestMatchers("/admin/**"))
                .authorizeHttpRequests(authorizeRequestsCustomizer -> authorizeRequestsCustomizer
                    .requestMatchers("/admin/**")
                    .permitAll().requestMatchers("/admin/reset-password/**").permitAll()
                    .anyRequest()
                    .hasAuthority(UserType.ADMIN.name()))
                .formLogin(
                    formLoginCustomizer -> formLoginCustomizer.loginPage("/admin/login")
                        .usernameParameter("email")
                        .passwordParameter("password").defaultSuccessUrl("/admin/jobs").permitAll())
                .logout(logoutCustomizer -> logoutCustomizer
                    .logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout", "GET"))
                    .logoutSuccessUrl("/admin/login"))
                .rememberMe(
                    rememberMeCustomizer -> rememberMeCustomizer.rememberMeParameter("remember-me")
                        .tokenValiditySeconds(rememberMeValiditySeconds).key(rememberMeKey))
                .exceptionHandling(
                    exceptionHandlingCustomizer -> exceptionHandlingCustomizer
                        .accessDeniedPage("/admin/login"));

            return http.build();
        }

        @Bean
        WebSecurityCustomizer webSecurityCustomizer() {
            return web -> web
                .ignoring()
                .requestMatchers("/img/**", "/js/**", "/webjars/**");
        }

    }
}
