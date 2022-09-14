package com.example.sales.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//import com.example.sales.model.Commission;
import com.example.sales.model.Vehicle;

public interface VehicleRepo extends JpaRepository<Vehicle, Integer>{
	
	@Query("SELECT u FROM Vehicle u WHERE u.id = ?1")
    public Vehicle findById(int id);
	
	@Query("select p.type from Vehicle p where p.id =:id")
	public String getVehicleType(@Param("id") int vehicleId);

	@Query("select p.type from Sales_csv p where p.id =:id and p.cost=:cost")
	public Vehicle findByVehicleIdCost(@Param("id") int vehicleid,@Param("cost") Long cost); 
}
