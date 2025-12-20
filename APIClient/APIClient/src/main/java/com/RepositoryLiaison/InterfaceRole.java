package com.RepositoryLiaison;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.APIClient.UserRolePrecis;

public interface InterfaceRole extends JpaRepository<UserRolePrecis, Long> {
    
}
