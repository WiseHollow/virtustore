package net.kwevo.virtustore.security;

import net.kwevo.virtustore.exceptions.UnauthorizedException;
import net.kwevo.virtustore.security.core.CoreUser;
import net.kwevo.virtustore.security.core.CoreUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public record ApplicationUserDetailsService(CoreUserRepository coreUserRepository) implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CoreUser coreUser = coreUserRepository.findByUsername(username).orElseThrow(UnauthorizedException::new);
        return new User(coreUser.getUsername(), coreUser.getPassword(), Collections.emptyList());
    }
}
