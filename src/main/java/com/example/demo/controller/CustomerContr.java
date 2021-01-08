package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerServ;

@Controller
public class CustomerContr {
	
	@Autowired
	private CustomerServ customerServ;
	
	@GetMapping("/")
	public String disHomePg(Model model, @Param("keyword") String keyword) {
		
		model.addAttribute("listCustomers", customerServ.getAllCustomers(keyword)); //
		model.addAttribute("keyword", keyword);
		return "index";
	} // method to display list of customers on home pg- index.html
	
	@GetMapping("/newCustomerForm")
	public String newCustomerForm(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer); //thym temp will access this empty cust obj for binding form data
		return "new_customer";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		customerServ.saveCustomer(customer);
		return "redirect:/"; //redirect to hm pg
	}// save cust to database
	
	@GetMapping("/formForEdit/{id}")
	public String formForEdit(@PathVariable(value = "id") long id, Model model) {
		
		//get cust from serv
		Customer customer = customerServ.getCustById(id);
		model.addAttribute("customer", customer); //model attr to prefill form
		return "edit_customer";
	}
	
	@GetMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable (value = "id") long id) {
		
		this.customerServ.deleteCustById(id);
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
		return "redirect:/login";
		
	}

} 
