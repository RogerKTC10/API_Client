package com.example.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RepositoryLiaison.InterfaceRole;
import com.RepositoryLiaison.InterfaceUtilisateur;
import com.example.APIClient.Utilisateur;
import com.example.APIClient.UserRolePrecis;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
public class ControllersUilisateur{
    private final InterfaceUtilisateur repository;
    private final InterfaceRole repositoryRole;
    private final PasswordEncoder passwordEncoder;

    public ControllersUilisateur(InterfaceUtilisateur repository,InterfaceRole repositoryRole, PasswordEncoder passwordEncoder){
        this.repository = repository;
        this.repositoryRole = repositoryRole;
        this.passwordEncoder = passwordEncoder;
    }
    //LISTER POUR LA LESTURE
    @PreAuthorize("hasRole('ADMIN') or hasRole('Gestionnaire_Utilisateurs')")   
    @GetMapping("/utilisateurs")
    public String ListeAffiche(){
         return repository.findAll().toString();
    }
    //AJOUTER

    @PreAuthorize("hasRole('ADMIN') or hasRole('Gestionnaire_Utilisateurs')")
    @PostMapping("/utilisateurs")
    public String AjoutUtilisateur(@RequestBody Utilisateur user) {
        if(user.getName() == null || user.getUsername() == null ||user.getEmail() == null || user.getPassword() == null || user.getRappel() == null){
                return "Manques d'informations Importantes";
        } 
        if(user.getRole() == null){
            return "Preciser le Role avant d'ajouter cet Utilsateur";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user).toString();
    }
    //SUPPRIMER

    @PreAuthorize("hasRole('ADMIN') ")
    @DeleteMapping("/utilisateurs")
    public String SupprimerUtilisateur(@RequestParam long id) {
        if(!repository.existsById(id)){
            return "Cet Utilisateur est introuvable... La suppression ne peut se faire";
        }
        repository.deleteById(id);
        return "Utilisateur Supprimé";
    }
    //MODIFIER

    @PreAuthorize("hasRole('ADMIN') or hasRole('Gestionnaire_Utilisateurs')")
    @PutMapping("/utilisateurs/{id}")
    public String ModifierUtilisateur(@RequestBody Utilisateur u, @PathVariable long id){
        if(!repository.existsById(id)){
           return "Cet Utilisateur est introuvable... La modification ne peut se faire";
       }
       Utilisateur user = repository.findById(id).orElseThrow();
        user.setName(u.getName());
        user.setUsername(u.getUsername());
        user.setEmail(u.getEmail());
        user.setPassword(passwordEncoder.encode(u.getPassword()));
        user.setRappel(u.getRappel());
        
        return repository.save(user).toString();
      }

    //BLOQUER
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/utilisateurs/Block/{id}")
    public String BlockUtilisateur(@PathVariable long id){
        if(!repository.existsById(id)){
            return "Cet Utilisateur est introuvable... Le blocage ne peut se faire";
        }
        Utilisateur user = repository.findById(id).orElseThrow();
        user.setBlock(true);
        return repository.save(user).toString();
    }
    //DEBLOQUER

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/utilisateurs/Unblock/{id}")
    public String DebloquerUtilisateur(@PathVariable long id){
        if(!repository.existsById(id)){
            return "Cet Utilisateur est introuvable... Le déblocage ne peut se faire";
        }
        Utilisateur user = repository.findById(id).orElseThrow();
        user.setBlock(false);
        return repository.save(user).toString();
    }
    //ATTRIBUER UN ROLE

    @PreAuthorize("hasRole('ADMIN') or hasRole('Gestionnaire_Utilisateurs')")
    @PutMapping("/utilisateurs/Role/{idUser}/{idRole}")
    public String AttribuerRole(@PathVariable long idUser, @PathVariable long idRole){
        Utilisateur user = repository.findById(idUser).orElse(null);
        if(user == null){
            return "Cet Utilisateur est introuvable... L'attribution de role ne peut se faire";
        }
        UserRolePrecis role = repositoryRole.findById(idRole).orElse(null);
        if(role == null){
            return "Ce Role est introuvable... L'attribution de role ne peut se faire";
        }
        user.setRole(role);

        repository.save(user);
        return "Role attribué avec succès";
    }
}
