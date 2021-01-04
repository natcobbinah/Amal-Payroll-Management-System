package com.payroll.usermanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.payroll.usermanagement.entities.Userrole;

@Repository
public interface UserroleRepository extends JpaRepository<Userrole, Integer> {

	@Query("from Userrole u where u.role.roleid = :id")
	List<Userrole> getUsersByRole(@Param("id") Integer id);
	
}

