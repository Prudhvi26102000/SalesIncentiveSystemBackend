package com.example.sales.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import com.example.sales.model.Commission;
//import com.example.sales.model.Sales_csv;


public interface CommissionRepo extends JpaRepository<Commission,Integer>{

	
	
}
