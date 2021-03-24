package io.kagboton.springdemo.controller;

import io.kagboton.springdemo.entity.Customer;
import io.kagboton.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel){

        // get the customer from the database
        Customer theCustomer = customerService.getCustomerById(theId);

        //set customer as model attribute to pre-populate the form
        theModel.addAttribute("theCustomer", theCustomer);

        // send over our form
        return "customer-form";
    }


    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam("customerId") int theId, Model theModel){
        // delete the customer
        customerService.deleteCustomer(theId);
        return "redirect:/customer/list";
    }

    @GetMapping("/searchCustomer")
    public String searchCustomer(@RequestParam("searchName") String searchName, Model theModel){

        // search customer from the service
        List<Customer> customers = customerService.searchCustomers(searchName);

        // add customers to the model
        theModel.addAttribute("customers", customers);

        return "list-customers";
    }
}
