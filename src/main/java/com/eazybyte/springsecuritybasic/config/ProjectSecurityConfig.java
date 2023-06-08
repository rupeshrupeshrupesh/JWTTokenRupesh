package com.eazybyte.springsecuritybasic.config;

import com.eazybyte.springsecuritybasic.filter.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;

@Component
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        csrfTokenRequestAttributeHandler.setCsrfRequestAttributeName("_csrf");
        http

//                .securityContext().requireExplicitSave(false)
//                .and().sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))


                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration corsConfiguration = new CorsConfiguration();
                        corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
                        corsConfiguration.setAllowCredentials(true);
                        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
                        corsConfiguration.setExposedHeaders(Arrays.asList("Authorization"));
                        corsConfiguration.setMaxAge(3600L);
                        return corsConfiguration;
                    }
                }).and()
                .csrf((csrf) ->
                        csrf.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler).ignoringRequestMatchers("/contact", "/register")
                                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
//                .ignoringRequestMatchers("/contact","/register")

                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
//                .addFilterBefore(new RequestValidationBeforeFilter(),BasicAuthenticationFilter.class)
//                .addFilterAfter(new AuthoritiesLoggoingAfterFilet(),BasicAuthenticationFilter.class)
                .addFilterAfter(new JwtTokenGeneratorFilter(),BasicAuthenticationFilter.class)  //JwtTokenValidatorFilter
                .addFilterBefore(new JwtTokenValidatorFilter(),BasicAuthenticationFilter.class)  //jwtTokenGenerator
                .authorizeHttpRequests()
//                .requestMatchers("/myAccount/**").hasRole("user")
//                .requestMatchers("/myBalance").hasAnyRole("user","admin")
//                .requestMatchers("/myLoans").hasRole("admin")
//                .requestMatchers("/myCards").hasRole("user")
//                .requestMatchers("/user").authenticated()

                .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards" ).authenticated()
                .requestMatchers("/notices", "/contact", "/register","/user").permitAll()
                .and().formLogin()
                .and().httpBasic();

                return http.build();

//                http.authorizeHttpRequests()
////                        .requestMatchers("/myAccount","/myBalance","/myLoans","/myCards").authenticated()
////                        .requestMatchers("/notices","/contact").permitAll()
//                        .anyRequest().denyAll().and()
//                .formLogin()
//                .and().httpBasic();
//
//        return http.build();

//         .authorizeHttpRequests()
//                .requestMatchers("/myAccount').hasAuthority("VIEWACCOUNT")
//                        .
//                .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards", "/user").authenticated()
//                .requestMatchers("/notices", "/contact", "/register").permitAll()
//                .and().formLogin()
//                .and().httpBasic();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

//        user is class and userdetails is interface  its proivide spring secutrity provided

//        build method provide to create a new object of and add username and password and any biolran value
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("12345")
                .authorities("admin")
                .build();

        //        build method provide to create a new object of and add username and password and any biolran value
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .authorities("read")
                .build();
        return new InMemoryUserDetailsManager(admin, user);


//        approach 2 weuse nooppasswoedencoder bean while creating thr user details


    }

    //this is not recommende for profuction
    @Bean
    public PasswordEncoder passwordEncoder() {

//        @Deprecated
//        return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }
//
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource){
//
//        return new JdbcUserDetailsManager();
//    }
}

