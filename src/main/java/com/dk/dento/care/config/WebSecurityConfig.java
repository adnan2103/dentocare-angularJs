package com.dk.dento.care.config;

import com.dk.dento.care.security.PatientRepositoryUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Spring Security config file.
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = { "com.dk.dento.care"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({DatabaseConfig.class})
public class WebSecurityConfig
    extends WebSecurityConfigurerAdapter {

    @Autowired
    private PatientRepositoryUserDetailsService patientRepositoryUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()
                .antMatchers("/resources/**", "/index.html", "/", "/login").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,
                                PatientRepositoryUserDetailsService patientRepositoryUserDetailsService) throws Exception {
        auth
            .userDetailsService(patientRepositoryUserDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}
