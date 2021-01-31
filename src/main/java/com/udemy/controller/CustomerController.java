package com.udemy.controller;

import com.udemy.entity.Customer;
import com.udemy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    /* need to inject the customer dao
    @Autowired
    private CustomerDAO customerDAO;
    */
    // this is no longer needed as the new layer Service is between customer controller and customer DAO (see slides)

    // need to inject the customer service
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model) {

        // get customers from dao --> List<Customer> customers = customerDAO.getCustomers();  BEFORE Service layer
        // add the customers to the model

        model.addAttribute("customers", customerService.getCustomers());
        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        // create model attribute to bind form data

        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        // save the customer using our service

        customerService.saveCustomer(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate/{customerId}")
    public String showFormForUpdate(@PathVariable(value = "customerId") int theId, Model model) {
        // get the customer from our service
        // set customer as a model attribute to pre-populate the form
        // send over to our form

        Customer customer = customerService.getCustomer(theId);
        model.addAttribute("customer", customer);
        return "customer-form";
    }

}
