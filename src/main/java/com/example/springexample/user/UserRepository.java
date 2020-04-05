package com.example.springexample.user;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.gender = :gender")
	public List<User> findUserByGender(@Param("gender") String gender);

	@Query("SELECT userId FROM User u WHERE u.userName = :userName")
	public List<User> findIdByUserName(@Param("userName") String userName);

	@Modifying
	@Query("UPDATE User u SET u.name=:name, u.password=:password, u.email=:email, u.gender=:gender, u.age=:age WHERE u.userId=:userId")
	public void updateUser(@Param("userId") int userId, @Param("name") String name, @Param("password") String password,
			@Param("email") String email, @Param("gender") String gender, @Param("age") int age);
	
	@Modifying
	@Query("UPDATE User u SET u.name = :name WHERE u.userId= :userId")
	public void updateName(@Param("userId") int userId, @Param("name") String name);
	
	@Modifying
	@Query("UPDATE User u SET u.password = :password WHERE u.userId= :userId")
	public void updatePassword(@Param("userId") int userId,@Param("password") String password);
	
	@Modifying
	@Query("UPDATE User u SET u.email = :email WHERE u.userId= :userId")
	public void updateEmail(@Param("userId") int userId, @Param("email") String email);
	
	@Modifying
	@Query("UPDATE User u SET u.age = :age WHERE u.userId= :userId")
	public void updateAge(@Param("userId") int userId, @Param("age") int age);
	
	@Modifying
	@Query("UPDATE User u SET u.gender = :gender WHERE u.userId= :userId")
	public void updateGender(@Param("userId") int userId, @Param("gender") String gender);
}



