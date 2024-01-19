package com.workintech.ecommerce.config;

import com.workintech.ecommerce.repository.UserRepository;
import com.workintech.ecommerce.service.User.LoginUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {




        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }


        @Bean
        public AuthenticationManager authManage(UserDetailsService userDetailsService){
            DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
            provider.setUserDetailsService(userDetailsService);
            provider.setPasswordEncoder(passwordEncoder());
            return new ProviderManager(provider);
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{


        return httpSecurity.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->{
                  //  auth.requestMatchers("/category/**").permitAll();
                    auth.requestMatchers("/category/**").hasAnyAuthority("ADMIN","USER");
                    auth.requestMatchers("/auth/**").permitAll();
                    auth.requestMatchers("/address/**").hasAnyAuthority("ADMIN","USER");
                    auth.requestMatchers("/product/**").hasAnyAuthority("ADMIN","USER");
                    auth.requestMatchers("/order/**").hasAnyAuthority("ADMIN","USER");
                    auth.requestMatchers("/city/**").hasAnyAuthority("ADMIN","USER");



                    auth.anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

}
