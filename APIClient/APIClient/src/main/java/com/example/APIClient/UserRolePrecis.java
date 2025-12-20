package com.example.APIClient;

import jakarta.persistence.Entity; 
import jakarta.persistence.Id; 
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType;

@Entity 
public class UserRolePrecis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userRole;

    public UserRolePrecis(String userRole){
        this.userRole = userRole;
    }

    public UserRolePrecis(){}
    //GETTEURS
    public long getId(){ return id;}
    public String getUserRole(){ return userRole; }

    //SETTEURS
    public void setId(long id){ this.id = id; }
    public void setUserRole(String userRole){ this.userRole = userRole;}
}
