package net.kwevo.virtustore.security.core;

import net.kwevo.virtustore.exceptions.ConflictException;
import net.kwevo.virtustore.exceptions.NotFoundException;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@ConstructorBinding
public record CoreUserService(CoreUserRepository coreUserRepository, PasswordEncoder passwordEncoder) {
    @PostConstruct
    public void setupAdmin() {
        if (!coreUserRepository().existsByUsername("ADMIN")) {
            String password = passwordEncoder.encode("ADMIN");
            CoreUserModel adminUser = new CoreUserModel(null, "ADMIN", password);
            create(adminUser);
        }
    }

    public CoreUserModel get(String username) {
        CoreUser coreUser = coreUserRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Could not find user with username '" + username + "'."));
        return new CoreUserModel(coreUser);
    }

    public CoreUserModel create(CoreUserModel coreUserModel) {
        if (coreUserRepository.existsByUsername(coreUserModel.getUsername())) {
            throw new ConflictException("Could not create user with username '" + coreUserModel.getUsername() + "'.");
        }

        coreUserModel.setId(null);
        validate(coreUserModel);

        CoreUser entity = coreUserModel.toEntity();
        entity = coreUserRepository.save(entity);
        return new CoreUserModel(entity);
    }

    private void validate(CoreUserModel coreUserModel) {
        if (coreUserModel.getUsername() == null || coreUserModel.getUsername().isBlank()) {
            throw new RuntimeException("nope on that username, buddy.");
        } else if (coreUserModel.getPassword() == null || coreUserModel.getPassword().isBlank()) {
            throw new RuntimeException("nope on that password, buddy.");
        }
    }
}
