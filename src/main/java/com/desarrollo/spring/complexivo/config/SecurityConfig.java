package com.desarrollo.spring.complexivo.config;

import com.desarrollo.spring.complexivo.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .authorizeHttpRequests(auth -> auth
                        // login y register libres
                        .requestMatchers("/login", "/register").permitAll()

                        // acceso solo a ADMIN en reservas y habitaciones (crear, editar, eliminar)
                        .requestMatchers("/reservas/nueva", "/reservas/editar/**", "/reservas/eliminar/**").hasRole("ADMIN")
                        .requestMatchers("/habitaciones/nueva", "/habitaciones/editar/**", "/habitaciones/eliminar/**").hasRole("ADMIN")

                        // acceso a la lista (reservas, clientes, habitaciones) tanto USER como ADMIN
                        .requestMatchers("/", "/reservas/lista", "/clientes/lista", "/habitaciones/lista").hasAnyRole("USER", "ADMIN")

                        // cualquier otra ruta autenticada
                        .anyRequest().authenticated()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
}
