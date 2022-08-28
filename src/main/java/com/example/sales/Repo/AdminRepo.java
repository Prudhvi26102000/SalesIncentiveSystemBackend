package com.example.sales.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sales.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, String>{

	public Admin findByEmail(String email);
	
	public Boolean existsByEmail(String email);

}
