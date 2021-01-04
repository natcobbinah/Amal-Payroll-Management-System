package com.payroll.usermanagement.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.payroll.usermanagement.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value="select * from users u where u.employeeid = :empid",
			nativeQuery=true)
	User findUserByemployeeid(@Param("empid")String empid);
	
	@Query(value="select * from users u where u.name = :name",
			nativeQuery=true)
	List<User> findUserByName(@Param("name") String name);
	
	@Query(value= "select * from users u WHERE u.email = :email",
			nativeQuery=true)
	User getUserByEmail(@Param("email") String email);

	@Query(value= "select * from users  u where u.email= :email and u.password= :password",
			nativeQuery=true)
	Optional<User> getUserByEmailandPassword(@Param("email") String email, @Param("password") byte[] password);

	@Modifying
	@Query(value="delete from users u where u.employeeid = :empid",
			nativeQuery=true)
	void disableandDeleteUser(@Param("empid")String empid);
}
