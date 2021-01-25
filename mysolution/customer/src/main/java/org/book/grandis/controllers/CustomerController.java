package org.book.grandis.controllers;

import org.book.grandis.model.Customer;
import org.book.grandis.repos.CustomerRepository;
import org.book.grandis.services.TraceService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping(value = "/v2/customer")
public class CustomerController {

    @Autowired
    TraceService traceService;

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // CREATE
    @RequestMapping(method = RequestMethod.PUT)
    public void addNewCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
        this.customerRepository.save(customer);
    }


    // READ
    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable String bookId) {
        Optional<Customer> bookOptional = customerRepository.findById(bookId);
        return bookOptional.get();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Collection<Customer> getAllCustomer() {
        Logger log= null;
        log.info("Get all Customer");
        return customerRepository.findAll();
    }


    // UPDATE
    @RequestMapping(value = "/{customerId}", method = RequestMethod.POST)
    public Customer modifyCustomer(@RequestBody Customer customer, @RequestBody String bookId ) {
        return customerRepository.save(customer);
    }


    // DELETE
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllCustomer() {
        customerRepository.deleteAll();
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable String bookId) {
        customerRepository.deleteById(bookId);
    }
}
