package com.company.customerdataservice.respository;

import com.company.customerdataservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepo;

    @Before
    public void setUp() throws Exception{
        customerRepo.deleteAll();
    }

    @Test
    public void addCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setEmail("joe@email.com");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress1("Norfolk Ave");
        customer.setAddress2("Ap2");
        customer.setCity("Norfolk");
        customer.setState("Va");
        customer.setPostalCode("45324");
        customer.setCountry("USA");

        customerRepo.save(customer);

        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        assertEquals(customer1.get(), customer);
    }

    @Test
    public void getAllCustomers(){
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setEmail("joe@email.com");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress1("Norfolk Ave");
        customer.setAddress2("Ap2");
        customer.setCity("Norfolk");
        customer.setState("Va");
        customer.setPostalCode("45324");
        customer.setCountry("USA");

        customerRepo.save(customer);

        List<Customer> customerList = customerRepo.findAll();

        assertEquals(customerList.size(), 1);
    }

    @Test
    public void getCustomerById(){
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setEmail("joe@email.com");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress1("Norfolk Ave");
        customer.setAddress2("Ap2");
        customer.setCity("Norfolk");
        customer.setState("Va");
        customer.setPostalCode("45324");
        customer.setCountry("USA");

        customerRepo.save(customer);


        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        assertEquals(customer1.get(), customer);
    }

    @Test
    public void updateCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setEmail("joe@email.com");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress1("Norfolk Ave");
        customer.setAddress2("Ap2");
        customer.setCity("Norfolk");
        customer.setState("Va");
        customer.setPostalCode("45324");
        customer.setCountry("USA");

        customerRepo.save(customer);

        customer.setCompany("Netflix");
        customerRepo.save(customer);

        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        assertEquals(customer1.get(), customer);
    }

    @Test
    public void deleteCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setEmail("joe@email.com");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress1("Norfolk Ave");
        customer.setAddress2("Ap2");
        customer.setCity("Norfolk");
        customer.setState("Va");
        customer.setPostalCode("45324");
        customer.setCountry("USA");

        customerRepo.save(customer);


        customerRepo.deleteById(customer.getId());

        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        assertFalse(customer1.isPresent());
    }



    @Test
    public void findByState() {
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setEmail("joe@email.com");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress1("Norfolk Ave");
        customer.setAddress2("Ap2");
        customer.setCity("Norfolk");
        customer.setState("Va");
        customer.setPostalCode("45324");
        customer.setCountry("USA");

        customerRepo.save(customer);

        List<Customer> customerList = customerRepo.findByState(customer.getState());

        assertEquals(customerList.size(), 1);
    }
}