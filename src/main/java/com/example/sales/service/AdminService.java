package com.example.sales.service;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.sales.CSVHelper;
import com.example.sales.Repo.AdminRepo;
import com.example.sales.Repo.QuotaRepo;
import com.example.sales.Repo.Sales_csvRepo;
import com.example.sales.Repo.VehicleRepo;
import com.example.sales.dto.SalescsvDto;
import com.example.sales.model.Admin;
import com.example.sales.model.Quota;
import com.example.sales.model.Sales_csv;
import com.example.sales.model.Vehicle;


@Service
public class AdminService {

	@Autowired
	private AdminRepo adminrepo;
	
	@Autowired
	private SalesPersonService salesservice;
	
	@Autowired
	private  VehicleService vehicleservice;

	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private QuotaRepo quotarepo;
	
	@Autowired 
	private VehicleRepo vehiclerepo;
	
	@Autowired
	private Sales_csvRepo salesrepo;
	
	public Boolean Login(Admin admin)
	{
		List<Admin> admins=adminrepo.findAll();
		System.out.println(admin.toString());
		for(Admin other:admins)
		{
			System.out.println(other.getEmail()+""+other.getPassword()+" "+admin.getEmail()+" "+admin.getPassword());
			if(other.getRole().equals(admin.getRole()))
			{
				if(other.getEmail().equals(admin.getEmail())) 
				{
					if(other.getPassword().equals(admin.getPassword()))
					{
						return true;
					
					}
				}
			}
		}
		return false;
	}
	
//	public Boolean Login(Admin admin) {
//
//		if(!adminrepo.existsById(admin.getEmail()))
//		{
//			return false;
//		}
//		
//        if(adminrepo.existsById(admin.getEmail())) {
//            return true;
//        }
//
//        if(admin.getPassword().equals(adminrepo.findByEmail(admin.getEmail()).get().getPassword())) {
//            adminrepo.save(admin);
//            return true;
//        } 
//        else
//        {
//            return false;
//        }
//    }
	
	
	public Boolean Logout(String email)
	{
		if((adminrepo.findByEmail(email)) != null){
			System.out.println(email);
            return true;
        }
		
		boolean b=(adminrepo.findByEmail(email)!=null);
		System.out.println(email);
        return b;
        		
	}
	

	
	public List<Vehicle> Allvehicles()
	{
		return vehiclerepo.findAll();
	}
	
	public Vehicle addVehicle(Vehicle vehicle)
	{
		return vehiclerepo.save(vehicle);
	}

		
	public void save(MultipartFile file) {
        try {
            List<SalescsvDto> salesLineDtoItems = CSVHelper.csvToSalesLineItems(file.getInputStream());
            System.out.println("after csvtosales");
            List<Sales_csv> salesLineItems=new ArrayList<>();

            for(SalescsvDto item:salesLineDtoItems){
            	System.out.println(item);
            	Sales_csv salesLineItem=modelMapper.map(item,Sales_csv.class);
               // Sales_csv salesLineItem= SalesCsvMapper.INSTANCE.dtoToModel(item);
                salesLineItem.setId(item.getId());
                salesLineItem.setSalesperson_id(salesservice.findBySalesPersonId(item.getSalesperson_id()));
                salesLineItem.setVehicleId(vehicleservice.findByVehicleId(item.getVehicleId()));
                salesLineItem.setDate(item.getDate());
                salesLineItem.setCost(item.getCost());
                salesLineItems.add(salesLineItem);
            }
            salesrepo.saveAll(salesLineItems);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

	public List<Sales_csv> findAllSalesDetails()
	{
		return salesrepo.findAll();
	}
	
	public Quota UpdateQuota(Quota quota)
	{
		return quotarepo.save(quota);
	}

	public Quota addQuota(Quota quota)
	{
		return quotarepo.save(quota);
	}
	
	
//	 private boolean ChangeCommissionValue(SalesCsv sc) {
//	        Commission cm=commissionRepo.findByVehicleIdCost(sc.getVehicleId(),sc.getCost());
//	        int percent=cm.getPercentage();
//	        long com=(percent*sc.getCost())/100;
//	        Salesperson sp=salespersonRepo.findById(sc.getSalespersonId()).get();
//	        long presentVal=sp.getCommission()+com;
//	        boolean check=true;
//	        double[] level=new double[3];
//	        level[0]=(double) 1/10;
//	        level[1]=(double) 1/20;
//	        level[2]=(double) 1/50;
//	        int i=0;
//	        int id=sc.getSalespersonId();
//	        while(check & i<=2){
//	            int superiorId=salespersonRepo.findSuperiorIdBySalespersonId(id);
//	            if(superiorId==0 ){
//	                check=false;
//	                break;
//	            }
//	            Salesperson sup=null;
//	            try {
//	                sup = salespersonRepo.findById(superiorId).get();
//	                long sup_com=sup.getCommission() + (long)(level[i]*com);
//	                salespersonRepo.setCommission(superiorId,sup_com,sup.getMonthlySales());
//	            }
//	            catch (NoSuchElementException e) {
//	                logger.error(e.getMessage());
//	            }
//	            i++;
//	            id=superiorId;
//	        }
//	        long monthlySales=sp.getMonthlySales()+ sc.getCost();
//	        salespersonRepo.setCommission(sc.getSalespersonId(), presentVal, monthlySales);
//	        return true;
//	    }
}
