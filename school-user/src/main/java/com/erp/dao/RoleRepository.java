package com.erp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.enums.RoleName;
import com.erp.model.UserModule;

@Repository
public interface RoleRepository extends JpaRepository<UserModule, Long> {
    Optional<UserModule> findByName(RoleName roleName);	

}
