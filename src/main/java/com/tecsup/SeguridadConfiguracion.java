package com.tecsup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.tecsup.servicio.UsuarioServicio;



@Configuration
@EnableWebSecurity
public class SeguridadConfiguracion extends WebSecurityConfiguration {

    @Autowired
    private UsuarioServicio userDet;

    @Autowired
    private BCryptPasswordEncoder bcryp;

    @Bean
    public BCryptPasswordEncoder passEncoder() {
        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
        return bcpe;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDet).passwordEncoder(bcryp);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/").permitAll()
                .requestMatchers("/listar").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/productos", "/clientes", "/ventas").hasRole("ADMIN")
                .anyRequest().authenticated())
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/listar")
                .failureUrl("/login?error=true"))
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login"))
            .exceptionHandling(handling ->
                handling.accessDeniedPage("/acceso-denegado"));

        return http.build();
    }
}