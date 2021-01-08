package com.example.demo.service;
import java.util.List;

import com.example.demo.model.Customer;

public interface CustomerServ {
	List<Customer> getAllCustomers(String keyword);// 
	
	void saveCustomer(Customer customer);
	
	Customer getCustById(long id);
	
	void deleteCustById(long id);

}
