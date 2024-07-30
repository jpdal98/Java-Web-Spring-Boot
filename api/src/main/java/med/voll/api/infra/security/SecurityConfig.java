package med.voll.api.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return
                http.csrf(csrf -> csrf.disable())
                        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authorizeHttpRequests(req -> {
                            req.requestMatchers("/login").permitAll()
                                    .requestMatchers(HttpMethod.POST, "/usuarios/cadastrar").permitAll()
                                    .requestMatchers(HttpMethod.GET, "/usuarios/buscarUsuarios").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.DELETE, "/usuarios").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.DELETE, "/medicos").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.PUT, "/medicos/desativar").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.DELETE, "/pacientes").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.PUT, "/pacientes/desativar").hasRole("ADMIN");
                            req.anyRequest().authenticated();
                        })
                        .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
