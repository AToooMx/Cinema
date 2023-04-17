package com.clevercinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService myUserDetailsService;
	
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests
                        (auth -> auth
                         .antMatchers("/admin").hasAuthority("ROLE_ADMIN") 
                         .antMatchers("/login", "/registration", "/process-registration").permitAll()	 
                         .antMatchers("/movies/movie/add-comment-process").authenticated() //Додавати коментарі може тільки авторизований користувач
                         .antMatchers("/account", "/account/**").authenticated()
                         .anyRequest().permitAll())
                .userDetailsService(myUserDetailsService)
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("email")
                        .loginProcessingUrl("/process-login"))
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")))
                        .exceptionHandling(handling -> handling.accessDeniedPage("/access-denied"));
		
		return http.build();
	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

/*
 * .antMatchers("/admin").hasAuthority("ADMIN") .antMatchers("/login",
 * "/registration", "/process-registration").permitAll()
 */