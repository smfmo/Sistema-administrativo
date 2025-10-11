package com.samuel.contratos.service;

import com.samuel.contratos.model.User;
import com.samuel.contratos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public void criarUsuarioAdmin(String username, String password) {
        if (repository.findByUsername(username).isPresent()){
            return;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setRole("ADMIN"); //define o papel como administrador

        repository.save(user);
    }
}
