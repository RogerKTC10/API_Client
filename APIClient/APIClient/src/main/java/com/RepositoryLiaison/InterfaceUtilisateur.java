package com.RepositoryLiaison;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.APIClient.Utilisateur;

@Repository
public interface InterfaceUtilisateur extends JpaRepository<Utilisateur , Long>{
    //IMPLEMENTATION DES METHODES D'ACTION SUR LA BASE DE DONNEES grace à JPA REPOSITORY 

    Utilisateur findByEmail(String email);
}
