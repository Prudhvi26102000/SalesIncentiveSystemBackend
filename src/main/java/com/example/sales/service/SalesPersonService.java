package com.example.sales.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.sales.Repo.SalesPersonRepo;
import com.example.sales.model.SalesPerson;

@Service
public class SalesPersonService {
		
	@Autowired
	private SalesPersonRepo salespersonrepo;
	
	
//	public SalesPersonService(SalesPersonRepo salespersonrepo)
//	{
//		this.salespersonrepo=salespersonrepo;
//	}
	
	public Boolean Login(SalesPerson salesperson)
	{
		List<SalesPerson> salespersons=salespersonrepo.findAll();
		
		for(SalesPerson other:salespersons)
		{
			if(other.getEmail().equals(salesperson.getEmail()))
			{
				if(other.getPassword().equals(salesperson.getPassword()))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public Boolean Logout(String email)
	{
		if(salespersonrepo.findByEmail(email)!=null)
		{
			return true;
		}
		boolean b=salespersonrepo.findByEmail(email)!=null;
		return b;
	}
	
	public SalesPerson addUser(SalesPerson user)
	{
		return salespersonrepo.save(user);
	}
	
	public void deleteSalesperson(int salesPersonId)
	{
		salespersonrepo.deleteById(salesPersonId);
	}
	
	public List<SalesPerson> findAllSalesPerson()
	{
		return salespersonrepo.findAll();
	}
	
	public SalesPerson findBySalesPersonId(int id){
		SalesPerson employee=salespersonrepo.findById(id);
		System.out.println(employee.getEmail());
		return salespersonrepo.findById(id);
	}

}
