package com.example.sales.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sales.model.Sales_csv;


@Repository
public interface Sales_csvRepo extends JpaRepository<Sales_csv, Integer>{

}
