package net.kwevo.virtustore.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import net.kwevo.virtustore.AppProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    private static final String HEADER_NAME = "Authorization";

    private final AppProperties appProperties;

    public AuthorizationFilter(AuthenticationManager authenticationManager, AppProperties appProperties) {
        super(authenticationManager);

        this.appProperties = appProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_NAME);

        if (header != null) {
            UsernamePasswordAuthenticationToken authenticationToken = authenticate(header);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken authenticate(String header) {
        String token = header.substring(7);
        Claims userClaims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(appProperties.getJwtSecret().getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();

        if (userClaims != null) {
            return new UsernamePasswordAuthenticationToken(userClaims, null, new ArrayList<>());
        }

        return null;
    }
}
