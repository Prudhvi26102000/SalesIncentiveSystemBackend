package com.example.sales.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.sales.model.SalesPerson;


@Repository
public interface SalesPersonRepo extends JpaRepository<SalesPerson, Integer>{
	
	public SalesPerson findByEmail(String email); 

	@Query("SELECT u FROM SalesPerson u WHERE u.id = ?1")
    public SalesPerson findById(int id);
	
}
