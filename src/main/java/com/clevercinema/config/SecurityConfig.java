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
                         .antMatchers("/admin/users/{id}/delete").hasAuthority("ROLE_ROOT") 
                         .antMatchers("/admin", "/admin/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_ROOT") 
                         .antMatchers("/{id}/delete-comment/{commentId}").hasAuthority("ROLE_ADMIN")
                         .antMatchers("/login", "/registration", "/process-registration").permitAll()	 
                         .antMatchers("/movies/{id}/add-comment-process").authenticated()
                         .antMatchers("/profile", "/profile/**").authenticated()
                         .antMatchers("/movies/{id}/seance/{seanceId}/buy-ticket", "/movies/{id}/seance/{seanceId}/buy-ticket/process-buy-ticket").authenticated()
                         .anyRequest().permitAll())
                .userDetailsService(myUserDetailsService)
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("email")
                        .defaultSuccessUrl("/movies")
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
