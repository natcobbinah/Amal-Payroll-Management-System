package com.payroll.usermanagement.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.payroll.usermanagement.entities.Department;

@Repository
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Integer> {

}
