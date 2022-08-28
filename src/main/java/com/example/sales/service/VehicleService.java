package com.example.sales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sales.Repo.VehicleRepo;
import com.example.sales.model.Vehicle;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepo vehiclerepo;
	
	
	
	public Vehicle findByVehicleId(int id){
		Vehicle product=vehiclerepo.findById(id);
		System.out.println(product.getName());
		return vehiclerepo.findById(id);
	}
	
	public void deleteVehicle(int id) {
		vehiclerepo.deleteById(id);
	}
	
}
