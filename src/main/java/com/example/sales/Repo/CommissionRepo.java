package com.example.sales.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sales.model.Commission;

public interface CommissionRepo extends JpaRepository<Commission,Integer>{

}
