package com.example.sales.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.sales.model.Quota;

public interface QuotaRepo extends JpaRepository<Quota,Integer>{
	
	@Query("SELECT u FROM Quota u WHERE u.id = ?1")
	public Quota findById(int id);

	Optional<Quota> findQuotaById(int id);
}
