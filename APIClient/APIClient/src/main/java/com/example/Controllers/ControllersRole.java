package com.example.Controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.RepositoryLiaison.InterfaceRole;
import com.example.APIClient.UserRolePrecis;

@RestController
public class ControllersRole {
    private final InterfaceRole repository;

    public ControllersRole(InterfaceRole repository){
        this.repository = repository;
    }
    //LISTER POUR LA LESTURE
    @GetMapping("/userRolesPrecis")
    public String RoleAffiche(){
         return repository.findAll().toString();
    }
    //AJOUTER

    @PostMapping("/userRolesPrecis")
    public String AjoutRole(@RequestBody UserRolePrecis role) {
        if(role.getUserRole() == null){
                return "Preciser le Role avant l'ajout";
        } 
        return repository.save(role).toString();
    }
    //SUPPRIMER

    @DeleteMapping("/userRolesPrecis")
    public String SupprimerRole(@RequestParam long id) {
        if(!repository.existsById(id)){
            return "Introuvable ... ";
        }
        repository.deleteById(id);
        return "Role Supprimé";
    }
    //MODIFIER

    @PutMapping("/userRolesPrecis/{id}")
public String ModifierRole(@RequestBody UserRolePrecis role, @PathVariable long id){
    if(!repository.existsById(id)){
        return "Cet Role est introuvable... La modification ne peut se faire";
    }
    UserRolePrecis userRole = repository.findById(id).orElseThrow();
    if(role.getUserRole() == null){
        return "Preciser avant modification";
    }
    userRole.setUserRole(role.getUserRole());

    return repository.save(userRole).toString();
    }
}
