package com.RepositoryLiaison;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.APIClient.Utilisateur;

public interface InterfaceUtilisateur extends JpaRepository<Utilisateur , Long>{
    //IMPLEMENTATION DES METHODES D'ACTION SUR LA BASE DE DONNEES
}
