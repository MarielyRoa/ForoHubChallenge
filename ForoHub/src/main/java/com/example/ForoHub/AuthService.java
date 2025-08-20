package com.example.ForoHub;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(String username, String password) {
        if (usuarioRepo.findByUsername(username).isPresent()) {
            throw new RuntimeException("Usuario ya existe");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(password));

        return usuarioRepo.save(usuario);
    }

    public Usuario autenticarUsuario(String username, String password) {
        Optional<Usuario> usuarioOpt = usuarioRepo.findByUsername(username);
        if (usuarioOpt.isEmpty()) throw new RuntimeException("Usuario o contraseña inválidos");

        Usuario usuario = usuarioOpt.get();
        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Usuario o contraseña inválidos");
        }

        return usuario;
    }
}
