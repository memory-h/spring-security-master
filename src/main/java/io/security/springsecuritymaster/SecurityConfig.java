package io.security.springsecuritymaster;

import io.security.springsecuritymaster.cookie.CsrfCookieFilter;
import io.security.springsecuritymaster.cookie.SpaCsrfTokenRequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        SpaCsrfTokenRequestHandler csrfTokenRequestHandler = new SpaCsrfTokenRequestHandler();

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/csrf","/ignoreCsrf", "/requestCsrf", "/cookieCsrf").permitAll()
                .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .csrfTokenRequestHandler(csrfTokenRequestHandler)
                );
        http.addFilterBefore(new CsrfCookieFilter(), BasicAuthenticationFilter.class);

        return http.build();
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/csrf","/ignoreCsrf", "/requestCsrf", "/cookieCsrf").permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .csrf(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withUsername("user").password("{noop}1111").roles("USER").build();
        return  new InMemoryUserDetailsManager(user);
    }
}
