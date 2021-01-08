package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepo;

@Service

public class CustomerServImp implements CustomerServ {
	
	@Autowired
	private CustomerRepo customerRepo;
	@Override
	public List<Customer> getAllCustomers(String keyword) {  
		if(keyword != null) {
			return customerRepo.search(keyword);
		}
		return customerRepo.findAll();
	}
	@Override
	public void saveCustomer(Customer customer) {
		this.customerRepo.save(customer);
		
	}
	@Override
	public Customer getCustById(long id) {
		Optional<Customer> optional = customerRepo.findById(id);
		Customer customer = null;
		if(optional.isPresent()) {
			customer = optional.get();
		} else {
			throw new RuntimeException("Customer not found for id ::" + id);
		}
		
		return customer;
	}
	@Override
	public void deleteCustById(long id) {
		this.customerRepo.deleteById(id);
		
	}

}
