package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.respository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
    CustomerRepository repo;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    @GetMapping("/customers/state/{state}")
    public List<Customer> getCustomersByState(@PathVariable String state) {
        return repo.findByState(state);
    }


    @GetMapping("/customers/{customerId}")
    public Customer getCustomerById(@PathVariable int customerId) {
        Optional<Customer> result = repo.findById(customerId);

        if (!result.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");

        return result.get();
    }


    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer item) {
        return repo.save(item);
    }

    @PutMapping("/customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Customer updateCustomer(@RequestBody Customer item, @PathVariable int customerId) {
        Optional<Customer> result = repo.findById(customerId);

        if (!result.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");


        Customer customerInstance = result.get();

        if (item.getFirstName() != null) customerInstance.setFirstName(item.getFirstName());
        if (item.getLastName() != null) customerInstance.setLastName(item.getLastName());
        if (item.getEmail() != null) customerInstance.setEmail(item.getEmail());
        if (item.getCompany() != null) customerInstance.setCompany(item.getCompany());
        if (item.getPhone() != null) customerInstance.setPhone(item.getPhone());
        if (item.getAddress1() != null) customerInstance.setAddress1(item.getAddress1());
        if (item.getAddress2() != null) customerInstance.setAddress2(item.getAddress2());
        if (item.getCity() != null) customerInstance.setCity(item.getCity());
        if (item.getState() != null) customerInstance.setState(item.getState());
        if (item.getPostalCode() != null) customerInstance.setPostalCode(item.getPostalCode());
        if (item.getCountry() != null) customerInstance.setCountry(item.getCountry());


        return repo.save(customerInstance);
    }

    @DeleteMapping("/customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Customer deleteCustomer(@PathVariable int customerId) {
        Optional<Customer> result = repo.findById(customerId);

        if (!result.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");

        Customer customerInstance = result.get();
        repo.delete(customerInstance);

        return customerInstance;
    }
}
