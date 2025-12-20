package com.example.Controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RepositoryLiaison.InterfaceUtilisateur;
import com.example.APIClient.Utilisateur;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
public class ControllersUilisateur{
    private final InterfaceUtilisateur repository;

    public ControllersUilisateur(InterfaceUtilisateur repository){
        this.repository = repository;
    }
    //LISTER POUR LA LESTURE
    @GetMapping("/utilisateurs")
    public String ListeAffiche(){
         return repository.findAll().toString();
    }
    //AJOUTER

    @PostMapping("/utilisateurs")
    public String AjoutUtilisateur(@RequestBody Utilisateur user) {
        if(user.getName() == null || user.getUsername() == null ||user.getEmail() == null || user.getPassword() == null || user.getRappel() == null){
                return "Manques d'informations Importantes";
        } 
        return repository.save(user).toString();
    }
    //SUPPRIMER

    @DeleteMapping("/utilisateurs")
    public String SupprimerUtilisateur(@RequestParam long id) {
        if(!repository.existsById(id)){
            return "Cet Utilisateur est introuvable... La suppression ne peut se faire";
        }
        repository.deleteById(id);
        return "Utilisateur Supprimé";
    }
    //MODIFIER

    @PutMapping("/utilisateurs/{id}")
public String ModifierUtilisateur(@RequestBody Utilisateur u, @PathVariable long id){
    if(!repository.existsById(id)){
        return "Cet Utilisateur est introuvable... La modification ne peut se faire";
    }
    Utilisateur user = repository.findById(id).orElseThrow();
    user.setName(u.getName());
    user.setUsername(u.getUsername());
    user.setEmail(u.getEmail());
    user.setPassword(u.getPassword());
    user.setRappel(u.getRappel());

    return repository.save(user).toString();
    }

    //BLOQUER
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

    @PutMapping("/utilisateurs/Unblock/{id}")
    public String DebloquerUtilisateur(@PathVariable long id){
        if(!repository.existsById(id)){
            return "Cet Utilisateur est introuvable... Le déblocage ne peut se faire";
        }
        Utilisateur user = repository.findById(id).orElseThrow();
        user.setBlock(false);
        return repository.save(user).toString();
    }
}
