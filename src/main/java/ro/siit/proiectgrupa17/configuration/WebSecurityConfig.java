package ro.siit.proiectgrupa17.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/room").permitAll()
                        .requestMatchers("/statistics").hasRole("HOTEL_MANAGER")
                        .requestMatchers("/admin").hasAnyRole("ADMIN", "CHUCK_NORRIS")
                        .requestMatchers("/guests").hasRole("HOTEL_MANAGER")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Bean
    public PasswordEncoder setEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        UserDetails user2 =
//                User.withDefaultPasswordEncoder()
//                        .username("user2")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//        UserDetails manager1 =
//                User.withDefaultPasswordEncoder()
//                        .username("manager1")
//                        .password("password")
//                        .roles("HOTEL_MANAGER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user, manager1);
//    }

    @Bean(name = "standardEncoder")
    public PasswordEncoder encoder() {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //..........

        return encoder;


    }

}