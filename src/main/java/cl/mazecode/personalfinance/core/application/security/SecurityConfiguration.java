package cl.mazecode.personalfinance.core.application.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .fullyAuthenticated()
                .and()
                .httpBasic(Customizer.withDefaults())
        ;
//        http.authorizeHttpRequests((authz) -> authz.anyRequest()
//                                                   .authenticated())
//            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails root = User.withUsername("root")
//                               .password(passwordEncoder.encode("password"))
                               .password("root")
                               .roles("ROOT")
                               .authorities("ROLE_ROOT")
                               .build();

        UserDetails admin = User.withUsername("admin")
//                                .password(passwordEncoder.encode("password"))
                                .password("admin")
                                .roles("ADMIN")
                                .authorities("READ", "WRITE")
                                .build();

        UserDetails user = User.withUsername("user")
//                               .password(passwordEncoder.encode("password"))
                               .password("user")
                               .roles("USER")
                               .authorities("READ")
                               .build();

        return new InMemoryUserDetailsManager(root, admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}