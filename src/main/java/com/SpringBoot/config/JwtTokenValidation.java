package com.SpringBoot.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JwtTokenValidation extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        // ✅ Skip auth endpoints
        if (path.startsWith("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }



        String jwt = request.getHeader("Authorization");
        if(jwt!=null && jwt.startsWith("Bearer")){
            jwt=jwt.substring(7);
            try {
                SecretKey key = Keys.hmacShaKeyFor(JWT_CONSTANT.SECRET_KEY.getBytes());
                Claims claims = Jwts.parser()
                        .setSigningKey(key)
                        .build().
                        parseClaimsJws(jwt)
                        .getBody();

                String email = claims.get("email",String.class);
                String authorities = claims.get("authorities",String.class);

                List<GrantedAuthority> auths = AuthorityUtils
                        .commaSeparatedStringToAuthorityList(authorities);
                Authentication authentication = new UsernamePasswordAuthenticationToken(email,null,auths);

                SecurityContextHolder.getContext().setAuthentication(authentication);

                System.out.println("User authenticated: " + email);


            }catch (Exception e ){
                System.out.println("JWT Error : " + e.getMessage());
                throw new BadCredentialsException("Invalid Jwt token..");
            }

        }
        filterChain.doFilter(request,response);
    }
}
