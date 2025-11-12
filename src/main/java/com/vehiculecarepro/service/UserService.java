package com.vehiculecarepro.service;

import com.vehiculecarepro.model.User;
import com.vehiculecarepro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

// UserService.java (resumen)
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public boolean exists(String username) {
        return repo.existsByUsername(username);
    }

    public User register(String username, String rawPassword, String role) {
        if (repo.existsByUsername(username)) {
            throw new IllegalArgumentException("El usuario ya existe");
        }
        String hashed = encoder.encode(rawPassword);
        String roleClean = role.startsWith("ROLE_") ? role.substring(5) : role; // usamos roles("...") luego
        return repo.save(new User(username, hashed, roleClean));
    }

    /** Crea o ACTUALIZA password/rol si el usuario ya existe */
    public User upsert(String username, String rawPassword, String role) {
        String hashed = encoder.encode(rawPassword);
        String roleClean = role.startsWith("ROLE_") ? role.substring(5) : role;

        return repo.findByUsername(username)
                .map(u -> {
                    u.setPassword(hashed);
                    u.setRole(roleClean);
                    return repo.save(u);
                })
                .orElseGet(() -> repo.save(new User(username, hashed, roleClean)));
    }
}
