package com.artyom.quotedictionary.repo;

import com.artyom.quotedictionary.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getById(Long id);
}