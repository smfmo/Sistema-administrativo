package com.samuel.contratos.service;

import com.samuel.contratos.model.UserAdm;
import com.samuel.contratos.repository.UserAdmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAdmService {

    private final UserAdmRepository userAdmRepository;
    private final PasswordEncoder passwordEncoder;

    public void criarUsuarioAdmin(String username, String password) {
        if (userAdmRepository.findByUsername(username).isPresent()){
            return;
        }
        UserAdm user = new UserAdm();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("ADMIN"); //define o papel como administrador

        userAdmRepository.save(user);
    }
}
