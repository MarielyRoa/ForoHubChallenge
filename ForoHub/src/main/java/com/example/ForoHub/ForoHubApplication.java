package com.example.ForoHub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ForoHubApplication implements CommandLineRunner {

	@Autowired
	private UsuarioRepo usuarioRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ForoHubApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Crear usuario inicial si no existe
		if (usuarioRepo.findByUsername("admin").isEmpty()) {
			Usuario admin = new Usuario();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("admin123"));
			usuarioRepo.save(admin);
			System.out.println("Usuario admin creado: admin / admin123");
		}
	}
}
