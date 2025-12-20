package com.example.Controllers;

import com.RepositoryLiaison.InterfaceRole;
import com.RepositoryLiaison.InterfaceUtilisateur;
import com.example.APIClient.Utilisateur;   
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")

public class ControllersAuthentif {

    private final InterfaceUtilisateur userRepo;
    private final InterfaceRole roleRepo;
    private final PasswordEncoder encoder;

    public ControllersAuthentif(InterfaceUtilisateur userRepo, InterfaceRole roleRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public String register(@RequestBody DTO req) {

        if (userRepo.findByEmail(req.email) != null) {
            return "Cet email est déjà utilisé";
        }

        // Charger le rôle depuis la base
        var role = roleRepo.findById(req.roleId)
                   .orElseThrow(() -> new RuntimeException("Rôle introuvable"));

        // Créer l'utilisateur
        Utilisateur user = new Utilisateur();
        user.setName(req.name);
        user.setUsername(req.username);
        user.setEmail(req.email);
        user.setPassword(encoder.encode(req.password));
        user.setRappel(req.rappel);
        user.setRole(role);
        user.setBlock(false);

        userRepo.save(user);
        return "Utilisateur enregistré avec succès";
    }
}
