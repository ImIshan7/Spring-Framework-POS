package lk.ijse.spring.repo;

import lk.ijse.spring.config.WebRootConfig;
import lk.ijse.spring.entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebAppConfiguration
@ContextConfiguration(classes = {WebRootConfig.class})
@ExtendWith(SpringExtension.class)
@Transactional //data is not save in the database
class CustomerRepoTest {

    @Autowired
    CustomerRepo repo;

    @Test
    public void testGetAllCustomer(){
        List<Customer> all = repo.findAll();
        for (Customer customer : all) {
            System.out.println(customer.toString());
        }
    }


    @Test
    public void queryMethodTwo(){
//        Customer mathara = repo.readByAddress("Mathara");
//        System.out.println(mathara.toString());

//        Customer mathara = repo.getByAddress("Mathara");
//        System.out.println(mathara.toString());

//        Customer mathara = repo.queryByAddress("Mathara");
//        System.out.println(mathara.toString());

//        Long galle = repo.countByAddress("Galle");
//        System.out.println(galle);

        /*List<Customer> allCustomers = repo.getAllCustomers();
        for (Customer allCustomer : allCustomers) {
            System.out.println(allCustomer.toString());
        }*/

        Customer customer = repo.searchCustomerWithName2("Ishan","Galle");
        System.out.println(customer);
    }


}