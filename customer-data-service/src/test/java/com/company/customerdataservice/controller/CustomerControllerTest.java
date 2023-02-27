package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.respository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CustomerRepository customerRepo;

    @Before
    public void setUp() throws Exception{
        customerRepo.deleteAll();
    }


    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldGetAllCustomers() throws Exception {
        mockMvc.perform(get("/customers"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getCustomersByState() throws Exception {
        Customer customer = new Customer();
        customer.setId(1);
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

        when(customerRepo.findById(customer.getId())).thenReturn(Optional.of(customer));


        mockMvc.perform(get("/customers/state/"+customer.getState()))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void getCustomerById() throws Exception{
        Customer customer = new Customer();
        customer.setId(1);
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


        when(customerRepo.findById(customer.getId())).thenReturn(Optional.of(customer));

        mockMvc.perform(delete("/customers/"+customer.getId()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void addCustomer() throws  Exception {
        Customer customer = new Customer();
        customer.setId(1);
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

        when(customerRepo.findById(customer.getId())).thenReturn(Optional.of(customer));

        String inputJson = mapper.writeValueAsString(customer);

        mockMvc.perform(
                        post("/customers")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void updateCustomer() throws Exception{
        Customer customer = new Customer();
        customer.setId(1);
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

        when(customerRepo.findById(customer.getId())).thenReturn(Optional.of(customer));

        String inputJson = mapper.writeValueAsString(customer);

        mockMvc.perform(
                        put("/customers/"+customer.getId())
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteCustomer() throws Exception {
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

        when(customerRepo.findById(customer.getId())).thenReturn(Optional.of(customer));


        customerRepo.deleteById(customer.getId());

        mockMvc.perform(delete("/customers/"+customer.getId()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}