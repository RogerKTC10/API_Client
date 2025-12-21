package com.example.APIClient;


import com.RepositoryLiaison.InterfaceUtilisateur;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    
    private InterfaceUtilisateur repository;

    public UserService(InterfaceUtilisateur repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur user = repository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Utilisateur introuvable");
        }

        return new UserSecu(user);
    }
}



