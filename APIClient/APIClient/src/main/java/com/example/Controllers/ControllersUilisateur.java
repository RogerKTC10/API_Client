package com.example.Controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RepositoryLiaison.InterfaceUtilisateur;
import com.example.APIClient.Utilisateur;

@RestController
public class ControllersUilisateur{
    private final InterfaceUtilisateur repository;

    public ControllersUilisateur(InterfaceUtilisateur repository){
        this.repository = repository;
    }
    //LISTER POUR LA LESTURE
    @GetMapping("/utilisateurs")
    public String ListeAffiche(){
        return "" ;
    }
    //AJOUTER
    //SUPPRIMER
    //MODIFIER
    //BLOQUER
}
