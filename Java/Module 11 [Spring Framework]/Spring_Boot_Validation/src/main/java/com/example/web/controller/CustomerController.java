package com.example.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.web.bean.Customer;
import com.example.web.validation.CustomerValidation;

@Controller
public class CustomerController {

	private CustomerValidation customerValidation;

	@Autowired
	public void setCusomerValidation(CustomerValidation customerValidation) {
		this.customerValidation = customerValidation;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {

		System.out.println("Home call");

		return "index";
	}

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public ModelAndView customer() {
		System.out.println("Controller");

		Customer customer = new Customer();

		customer.setId(1);

		return new ModelAndView("customer", "customer", customer);
	}

	@ModelAttribute("customerDynamic")
	public Map<String, List<String>> dynamic() {
		Map<String, List<String>> map = new HashMap<String, List<String>>();

		List<String> languages = new ArrayList<String>();

		languages.add("Java");
		languages.add("Testing");
		languages.add("Android");
		languages.add(".Net");
		languages.add("iPhone");
		languages.add("PHP");

		map.put("languages", languages);

		List<String> education = new ArrayList<String>();

		education.add("");
		education.add("BE");
		education.add("ME");
		education.add("BCA");
		education.add("MCA");
		education.add("PhD");

		map.put("degrees", education);

		return map;
	}

	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	public String addCustomer(@ModelAttribute("customer") @Validated Customer customer,

			BindingResult result,

			ModelMap model,

			SessionStatus status) {

		customerValidation.validate(customer, result);

		if (result.hasErrors()) {
			return "customer";
		}

		status.setComplete();

		model.addAttribute("customer", customer);

		return "show";
	}
}
