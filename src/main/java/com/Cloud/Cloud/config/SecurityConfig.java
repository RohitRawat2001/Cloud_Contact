package com.Cloud.Cloud.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.Cloud.Cloud.helpers.Message;
import com.Cloud.Cloud.helpers.MessageType;
import com.Cloud.Cloud.services.impl.SecurityCustomUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    @Autowired
    private OAuthAuthenticationSuccessHandler handler;

     // Configuration for authentication Provider for spring security
    @Bean
    public DaoAuthenticationProvider  authenticationProvider(){
      DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
      //user detail service object
      daoAuthenticationProvider.setUserDetailsService(userDetailService);
      //password encoder object
      daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
      return  daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        //configuration :----------------

        // httpSecurity.authorizeHttpRequests(authorize ->{
        //     authorize.requestMatchers("/home").permitAll();
        // });
    
        //yha pa Url configuration kra hai ki konsa public rahagha or kon sa private
        httpSecurity.authorizeHttpRequests(authorize ->{
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

       //form login default
       //agar hama kuch bhi change krna hogha tho hum yha pa ayinga: form login sa related
       httpSecurity.formLogin(formLogin ->{
        formLogin.loginPage("/login");
        formLogin.loginProcessingUrl("/authenticate");
        formLogin.successForwardUrl("/user/profile");
      // formLogin.failureForwardUrl("/login/?error=true");
        formLogin.usernameParameter("email");
        formLogin.passwordParameter("password");
        // formLogin.defaultSuccessUrl("/home");


        formLogin.failureHandler(new AuthenticationFailureHandler() {

          @Override
          public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
              AuthenticationException exception) throws IOException, ServletException {
            
                if(exception instanceof DisabledException){
                  HttpSession  session = request.getSession();
                  session.setAttribute("message",Message.builder().content("User is disabled,Email with verification Link is sent to your Email !!").type(MessageType.red).build());

                  response.sendRedirect("/login");
                }else{

                  response.sendRedirect("/login?error=true");
                }

          }
          
        });
       });

       //logout
       httpSecurity.csrf(AbstractHttpConfigurer::disable);
       httpSecurity.logout(logoutForm ->{
        logoutForm.logoutUrl("/do-logout");
        logoutForm.logoutSuccessUrl("/login?logout=true");
       });

       //oauth configuration
       httpSecurity.oauth2Login(oauth ->{
        oauth.loginPage("/login");
        oauth.successHandler(handler);
       });

        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
