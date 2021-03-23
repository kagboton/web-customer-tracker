package io.kagboton.springdemo.controller;

import io.kagboton.springdemo.dao.CustomerDAO;
import io.kagboton.springdemo.entity.Customer;
import io.kagboton.springdemo.service.CustomerService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    // inject the customer service
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model){

        // get customers from the service
        List<Customer> customers = customerService.getCustomers();

        // add the customers to the model
        model.addAttribute("customers", customers);

        return "list-customers";
    }

    @GetMapping("/showAddCustomerForm")
    public String showAddCustomerForm(Model model){

        Customer theCustomer = new Customer();

        model.addAttribute("theCustomer", theCustomer);

        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("theCustomer") Customer theCustomer){

        customerService.saveCustomer(theCustomer);

        return "redirect:/customer/list";
    }

}
