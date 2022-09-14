package com.example.sales.service;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.sales.CSVHelper;
import com.example.sales.Repo.AdminRepo;
import com.example.sales.Repo.CommissionRepo;
import com.example.sales.Repo.QuotaRepo;
import com.example.sales.Repo.SalesPersonRepo;
//import com.example.sales.Repo.SalesPersonRepo;
import com.example.sales.Repo.Sales_csvRepo;
import com.example.sales.Repo.VehicleRepo;
import com.example.sales.dto.SalescsvDto;
import com.example.sales.model.Admin;
import com.example.sales.model.Commission;
import com.example.sales.model.Quota;
import com.example.sales.model.SalesPerson;
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
	
	@Autowired
	private SalesPersonRepo sprepo;
	
	@Autowired
	private CommissionRepo commissionrepo;
	

	


	
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
                salesLineItem.setType(item.getType());
                salesLineItem.setName(item.getName());
                salesLineItems.add(salesLineItem);
                
               // ChangeCommissionValue(item);
        		System.out.println("Entered Commission class");
        		String type=item.getType();
        		Long cost=item.getCost();
        		int p=0;
        		//System.out.println("type:"+type+"Cost:"+cost);
        		
        		List<Commission> com=commissionrepo.findAll();
        		for(Commission c:com)
        		{
        			//System.out.println("minrange:"+c.getMin_range()+"cost:"+cost);
        			if(type.equals(c.getType()))
        			{
        				if(cost>=c.getMin_range() && cost<=c.getMax_range())
        				{
        					p=c.getPercentage();
        				}
        			}
        		}
        		System.out.println("percentage:"+p);
        		long ans=(p*cost)/100;
        		SalesPerson sp=sprepo.findById(item.getSalesperson_id());
        		long newCom=sp.getCommission_value()+ans;
        		System.out.println("NewCommission:"+newCom);
        		
        		double l1=1/10;
        		double l2=1/20;
        		double l3=1/50;
        		
        		long lval1=500000;
        		long lval2=1000000;
        		long lval3=1500000;
        		
        		if(sp.getCommission_value()>=lval1)
        		{
        			long SuperCom1=sp.getCommission_value()+(long)(l1*ans);
        			sp.setCommission_value(SuperCom1);
        		}
        		else if(sp.getCommission_value()>=lval2)
        		{
        			long SuperCom2=sp.getCommission_value()+(long)(l2*ans);
        			sp.setCommission_value(SuperCom2);
        		}
        		else if(sp.getCommission_value()>=lval3)
        		{
        			long SuperCom3=sp.getCommission_value()+(long)(l3*ans);
        			sp.setCommission_value(SuperCom3);
        		}
        		else {
        			sp.setCommission_value(newCom);
        		}
                sp.setMonthly_sales(item.getCost()+sp.getMonthly_sales());
                    
                
            }
			/* System.out.println("SalesLineItems: "+salesLineItems); */
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
	
	public List<Quota> AllQuota()
	{
		return quotarepo.findAll();
	}
	
	public Optional<Quota> findByQuotaId(int id)
	{
		return quotarepo.findQuotaById(id);
	}

	

	

	

}
