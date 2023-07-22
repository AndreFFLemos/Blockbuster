package Blockbuster.Security;

import Blockbuster.Model.Customer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Optional;


//before the request enters our backend point on the controller, it will hit this class

public class JWTAuthenticationFilter extends OncePerRequestFilter {


    private JWTService jwtService;

    CustomUserDetailsService customUserDetailsService;

    public JWTAuthenticationFilter(JWTService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }
    public JWTAuthenticationFilter(){

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token= getToken(request);

        //from the token, let's get the identification
        Optional<String> userEmail= jwtService.getUserId(token);

        if (!userEmail.isPresent()){
            throw new InputMismatchException("No user found");
        }

        //with the id let's get the user
        UserDetails customer= customUserDetailsService.loadUserByUsername(userEmail.get());

        //the empty collections parameter are the permissions that the user has, and if the user is authenticated
        UsernamePasswordAuthenticationToken authentication=
                new UsernamePasswordAuthenticationToken(customer,null, Collections.emptyList());

        //sets the authentication context to the actual request in course
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        //resend the authentication to the Spring context and Spring takes care of it now
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest request){
        String token= request.getHeader("Authorization");

        //Spring class with a method to check if the token came with more than white spaces or not
        if (!StringUtils.hasText(token)){
        return null;
        }

        //the token returns a String but the first 6 letters are Bearer, I want it to return what's next
        return token.substring(7);
    }
}
