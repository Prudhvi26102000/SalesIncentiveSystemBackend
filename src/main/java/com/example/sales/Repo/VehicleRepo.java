package com.example.sales.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.example.sales.model.Vehicle;

public interface VehicleRepo extends JpaRepository<Vehicle, Integer>{
	
	@Query("SELECT u FROM Vehicle u WHERE u.id = ?1")
    public Vehicle findById(int id);

}
