package lk.ijse.spring.repo;

import lk.ijse.spring.config.WebRootConfig;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.entity.Item;
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
class ItemRepoTest {

    @Autowired
   ItemRepo repo;

    @Test
    public void testGetAllItem(){
        List<Item> all = repo.findAll();
        for (Item item : all) {
            System.out.println(item.toString());
        }
    }

}