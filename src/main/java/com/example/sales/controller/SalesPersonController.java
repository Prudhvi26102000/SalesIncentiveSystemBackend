package com.example.sales.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sales.model.SalesPerson;
import com.example.sales.service.SalesPersonService;

@CrossOrigin("*")
@RestController
@RequestMapping("/sales")
public class SalesPersonController {

	@Autowired
	private SalesPersonService salesservice;
		
	
	@PostMapping("/login")
	public Boolean CheckUser(@Validated @RequestBody SalesPerson salesperson) 
	{
		return salesservice.Login(salesperson);
	}
	
	@PostMapping("/logout")
	public Boolean LogoutUser(@Validated @RequestBody String email)
	{
		return salesservice.Logout(email);
	}


	
	

}
