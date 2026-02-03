package com.apiGateway.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http
            // Stateless API → disable CSRF
            .csrf(ServerHttpSecurity.CsrfSpec::disable)

            // Authorization rules
            .authorizeExchange(exchange -> exchange
                .pathMatchers("/auth/login", "/auth/register","/orders/**").permitAll()//login should be public
                .anyExchange().authenticated()
            )

            // Disable default login mechanisms
            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
            .formLogin(ServerHttpSecurity.FormLoginSpec::disable)

            .build();
    }
}
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.apiGateway.security.JwtAuthenticationFilter;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//	
//	private final JwtAuthenticationFilter jwtFilter;
//	
//	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
//		this.jwtFilter=jwtAuthenticationFilter;
//	}
//	
//	@Bean
//	public SecurityFilterChain filterChain (HttpSecurity http){
//		
//		http
//		//disable csrf as its stateless(server dont keep the user data in session(cookie) as the request 
//		//itself contains user id, token, signature, expire time)
//		.csrf(csrf -> csrf.disable())
//		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//		.authorizeHttpRequests(auth -> auth
//				//.requestMatchers("/user**").permitAll() public api's
//				.anyRequest().authenticated());
//		
//		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//		return http.build();
//		
//	}
//
//	
//
//}
