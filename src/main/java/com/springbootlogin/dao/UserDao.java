package com.springbootlogin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springbootlogin.modal.User;

public interface UserDao  extends CrudRepository<User, Long> {
	@Query("select u from User u where u.id = ?1")
	public User findByUserId(Long id);
	
	@Query("select u from User u where u.userName = ?1")
	public User findByUserName(String username);
	
	public List<User> findAllByOrderByIdAsc();
	
	public User findByEmail(String email);
}
