package com.microservicio.usuario;

import com.microservicio.usuario.model.Rol;
import com.microservicio.usuario.model.Usuario;
import com.microservicio.usuario.repository.RolRepository;
import com.microservicio.usuario.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository userRepo, RolRepository rolRepo, PasswordEncoder passwordEncoder) {
        return args -> {

            Rol adminRol = crearRolSiNoExiste(rolRepo, "ADMIN");
            Rol clienteRol = crearRolSiNoExiste(rolRepo, "CLIENTE");

            if (userRepo.findByEmailIgnoreCase("boss@gamingstore.com").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setNombre("Marcos");
                admin.setApellido("Gamer");
                admin.setEmail("boss@gamingstore.com");
                admin.setPassword(passwordEncoder.encode("admin_secure_2026"));
                admin.setRol(adminRol);
                userRepo.save(admin);
                System.out.println("Cargado usuario Administrador: Marcos");
            }

            if (userRepo.findByEmailIgnoreCase("lara.croft@gmail.com").isEmpty()) {
                Usuario cliente1 = new Usuario();
                cliente1.setNombre("Lara");
                cliente1.setApellido("Croft");
                cliente1.setEmail("lara.croft@gmail.com");
                cliente1.setPassword(passwordEncoder.encode("tombraider123"));
                cliente1.setRol(clienteRol);
                userRepo.save(cliente1);
                System.out.println("Cargado usuario Cliente: Lara");
            }

            if (userRepo.findByEmailIgnoreCase("nathan.drake@uncharted.com").isEmpty()) {
                Usuario cliente2 = new Usuario();
                cliente2.setNombre("Nathan");
                cliente2.setApellido("Drake");
                cliente2.setEmail("nathan.drake@uncharted.com");
                cliente2.setPassword(passwordEncoder.encode("treasure777"));
                cliente2.setRol(clienteRol);
                userRepo.save(cliente2);
                System.out.println("Cargado usuario Cliente: Nathan");
            }

            if (userRepo.findByEmailIgnoreCase("it-is-me@mario.com").isEmpty()) {
                Usuario cliente3 = new Usuario();
                cliente3.setNombre("Mario");
                cliente3.setApellido("Bros");
                cliente3.setEmail("it-is-me@mario.com");
                cliente3.setPassword(passwordEncoder.encode("peach_savior"));
                cliente3.setRol(clienteRol);
                userRepo.save(cliente3);
                System.out.println("Cargado usuario Cliente: Mario");
            }
        };
    }

    private Rol crearRolSiNoExiste(RolRepository repo, String nombre) {
        return repo.findByNombreRol(nombre)
                   .orElseGet(() -> {
                       Rol nuevoRol = new Rol();
                       nuevoRol.setNombreRol(nombre);
                       System.out.println("Creado rol: " + nombre);
                       return repo.save(nuevoRol);
                   });
    }
}