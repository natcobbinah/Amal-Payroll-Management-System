package com.payroll.usermanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payroll.usermanagement.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
