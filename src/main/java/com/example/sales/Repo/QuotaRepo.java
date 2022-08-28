package com.example.sales.Repo;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.sales.model.Quota;

public interface QuotaRepo extends CrudRepository<Quota,Integer>{
	


}
