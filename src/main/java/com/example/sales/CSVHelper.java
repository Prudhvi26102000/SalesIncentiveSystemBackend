package com.example.sales;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import org.springframework.web.multipart.MultipartFile;

import com.example.sales.dto.SalescsvDto;


public class CSVHelper {


    public static String TYPE = "text/csv";
    static String[] HEADERs = { "sales_id","salesperson_id", "vehicleId", "date", "cost" };
    public static boolean hasCSVFormat(MultipartFile file) {
        if (TYPE.equals(file.getContentType())
                || file.getContentType().equals("application/vnd.ms-excel")) {
            return true;
        }

        return false;
    }

    public static List<SalescsvDto> csvToSalesLineItems(InputStream is) {

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<SalescsvDto> salesLineItemDtoList = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {


                SalescsvDto salesLineItemDto = new SalescsvDto();
                
                salesLineItemDto.setId(Integer.parseInt(csvRecord.get("sales_id")));

                salesLineItemDto.setSalesperson_id(Integer.parseInt(csvRecord.get("salesperson_id")));

                salesLineItemDto.setVehicleId(Integer.parseInt(csvRecord.get("vehicleId")));

                String start_dt = csvRecord.get("date");
                System.out.println("in line 60");
                DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                System.out.println("in line 60");
                Date date = (Date)formatter.parse(start_dt);
                System.out.println("in line 60");
                salesLineItemDto.setDate(date);

                salesLineItemDto.setCost(Long.parseLong(csvRecord.get("cost")));
                System.out.println("in line 64");
                
                salesLineItemDto.setType(csvRecord.get("type"));
                System.out.println("in line 65");
                
                salesLineItemDto.setName(csvRecord.get("vehiclename"));

               salesLineItemDtoList.add(salesLineItemDto);
               

            }
            System.out.println(salesLineItemDtoList);

            return salesLineItemDtoList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


}
