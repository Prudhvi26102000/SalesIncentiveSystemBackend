package com.example.sales.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.example.sales.CSVHelper;
import com.example.sales.model.Admin;
import com.example.sales.model.Quota;
import com.example.sales.model.SalesPerson;
import com.example.sales.model.Sales_csv;
import com.example.sales.model.Vehicle;
import com.example.sales.service.AdminService;
import com.example.sales.service.SalesPersonService;
import com.example.sales.service.VehicleService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

	@Autowired
	private AdminService adminservice;
	
	@Autowired
	private SalesPersonService salesservice;
	
	@Autowired 
	private VehicleService vehicleservice;
	
	
	@PostMapping("/login")
	public Boolean CheckUser(@Validated @RequestBody Admin admin)
	{
		return adminservice.Login(admin);
	}
	
	@RequestMapping("/logout")
	public Boolean Logout(@RequestBody String email)
	{
		return adminservice.Logout(email);
	}
	
	@GetMapping("/allusers")
	public ResponseEntity<List<SalesPerson>> getAllUsers()
	{
		List<SalesPerson> salesPersons=salesservice.findAllSalesPerson();
		return new ResponseEntity<>(salesPersons,HttpStatus.OK);
	}

	@PostMapping("/addSalesPerson")
	public ResponseEntity<SalesPerson> addUser(@RequestBody SalesPerson salesperson)
	{
		SalesPerson newsalesperson=salesservice.addUser(salesperson);
		return new ResponseEntity<>(newsalesperson,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteSalesPerson/{salesPersonId}")
	public ResponseEntity<?> deleteSalesPerson(@PathVariable int salesPersonId)
	{
		salesservice.deleteSalesperson(salesPersonId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/vehicles")
	public ResponseEntity<List<Vehicle>> getAllVehicles()
	{
		List<Vehicle> vehicles=adminservice.Allvehicles();
		return new ResponseEntity<>(vehicles,HttpStatus.OK);
	}
	
	@PostMapping("/addvehicle")
	public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle)
	{
		Vehicle newvehicle=adminservice.addVehicle(vehicle);
		return new ResponseEntity<>(newvehicle,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteVehicle/{id}")
	public ResponseEntity<?> deleteVehicle(@PathVariable int id)
	{
		vehicleservice.deleteVehicle(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        System.out.println(CSVHelper.hasCSVFormat(file));
        if (CSVHelper.hasCSVFormat(file)) {
            try {
                adminservice.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return new ResponseEntity<>(message, HttpStatus.OK);
            } catch (Exception e) {
                System.out.println(e.fillInStackTrace());
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return new ResponseEntity<>(message,HttpStatus.EXPECTATION_FAILED);
            }
        }
        message = "Please upload a csv file!";
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }
	
	@GetMapping("/all")
    public ResponseEntity<List<Sales_csv>> findAllSalesLineItem() {
        try {
            List<Sales_csv> salesLineItems =adminservice.findAllSalesDetails();
            if (salesLineItems.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(salesLineItems, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@PutMapping("/updateQuota/{id}")
	public ResponseEntity<Quota> UpdateQuota(@PathVariable int id,@RequestBody Quota quota)
	{
		quota.setId(id);
		Quota upquota=adminservice.UpdateQuota(quota);
		return new ResponseEntity<>(upquota,HttpStatus.OK);
	}
	
	@PostMapping("/addQuota")
	public ResponseEntity<Quota> AddQuota(@RequestBody Quota quota)
	{
		Quota newQuota=adminservice.addQuota(quota);
		return new ResponseEntity<>(newQuota,HttpStatus.CREATED);
	}
	
	@GetMapping("/quotaDetails")
	public ResponseEntity<List<Quota>> findAllQuota(){
		
		List<Quota> allDetails=adminservice.AllQuota();
		return new ResponseEntity<>(allDetails,HttpStatus.OK);
	}
	
	@GetMapping("/findquota/{id}")
	public ResponseEntity<Optional<Quota>> findQuotabyId(@PathVariable("id") int id)
	{
		Optional<Quota> quota=adminservice.findByQuotaId(id);
		return new ResponseEntity<>(quota,HttpStatus.OK);
	}
	

}
