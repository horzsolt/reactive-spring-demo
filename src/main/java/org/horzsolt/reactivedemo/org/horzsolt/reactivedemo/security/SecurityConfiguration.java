package org.horzsolt.reactivedemo.org.horzsolt.reactivedemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
class SecurityConfiguration {

    @Bean
    UserDetailsRepository userDetailsRepository() {
        return new MapUserDetailsRepository(
                User.withUsername("horzsolt").roles("USER").password("pw").build(),
                User.withUsername("grinch").roles("ADMIN", "USER").password("pw").build());
    }

    @Bean
    SecurityWebFilterChain securityWebFilterChain(HttpSecurity httpSecurity) {
        return httpSecurity
                .authorizeExchange()
                .anyExchange().hasRole("ADMIN").and()
                .build();

    }
}
