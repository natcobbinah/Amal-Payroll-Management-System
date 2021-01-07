package com.payroll.usermanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.payroll.usermanagement.entities.Userdepartment;

@Repository
public interface UserDepartmentRepository extends JpaRepository<Userdepartment, Integer> {

	@Query("from Userdepartment d where d.department.id = :id")
	List<Userdepartment> getUsersbyDepartment(@Param("id") Integer id);
}
