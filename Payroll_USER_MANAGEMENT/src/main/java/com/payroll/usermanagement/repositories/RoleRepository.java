package com.payroll.usermanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payroll.usermanagement.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
