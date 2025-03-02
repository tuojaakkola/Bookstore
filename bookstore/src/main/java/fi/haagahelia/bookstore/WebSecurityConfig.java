package fi.haagahelia.bookstore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{ 
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests( authorize -> authorize
        .requestMatchers("/delete/**").hasRole("ADMIN")
        .anyRequest().authenticated()
        )
        .formLogin(formLogin -> formLogin
                .loginPage("/login")        
                .defaultSuccessUrl("/booklist", true)
                .permitAll()
        )
        .logout( logout -> logout
        .permitAll()
        );
        return http.build();
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("adminpassword")                       
                .roles("ADMIN")
                .build();

        List<UserDetails> users = new ArrayList<>();
        users.add(user);
        users.add(admin);
                
        return new InMemoryUserDetailsManager(users);
    }

}