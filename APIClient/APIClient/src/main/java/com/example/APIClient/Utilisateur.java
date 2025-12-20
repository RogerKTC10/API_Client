package com.example.APIClient;

import jakarta.persistence.Entity; 
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType;

@Entity 
//L'idée c'est de pouvoir l'utiliser avec les mapping BASE DE DONNEES Plutart
public class Utilisateur {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;
     private String name, username, email, password, rappel;
     private boolean block;

     @ManyToOne
     private UserRolePrecis role;
    

     public Utilisateur(){

     }
     
    //Constructeur
    public Utilisateur(long id, String name, String username, String email, String password, String rappel, boolean block, UserRolePrecis role){
        this.id = id; this.name = name; this.username = username;
        this.email = email; this.password = password; this.rappel = rappel;
        this.block = block; this.role = role;
       }

       //GETTEURS
        public long getId(){ return id; }
        public String getName(){ return name; }
        public String getUsername(){ return username;}
        public String getEmail(){ return email; }
        public String getPassword(){return password; }
        public String getRappel(){ return rappel; }
        public boolean getBlock(){ return block; }
        public UserRolePrecis getRole(){ return role; }

       //SETTEURS
       public void setId(long id){ this.id = id; }
       public void setName(String name){ this.name = name; }
       public void setUsername(String username){ this.username = username; }
       public void setEmail(String email){ this.email = email; }
       public void setPassword(String password){ this.password = password; }
       public void setRappel(String rappel){ this.rappel = rappel; }
       public void setBlock(boolean block){ this.block = block; }
       public void setRole(UserRolePrecis role){ this.role = role; }

       @Override
       public String toString(){
          return"Les informations de notre utilisateur sont : \n"+
               "Identifiant : "+this.id+"\n"+
               "Nom : "+this.name+"\n"+
               "Prenoms : "+this.username+"\n"+
               "Email : "+this.email+"\n"+
               "Mot de Passe : "+this.password+"\n"+
               "Phrase de rappel : "+this.rappel+"\n"+
               "Bloqué : "+(this.block ? "OUI":"NON")+ "\n"+
               "Role : "+(this.role != null ? this.role.getUserRole():"Aucun") +"\n";
       }
}
