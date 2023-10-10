package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.OrderDetailsDTO;
import lk.ijse.spring.dto.OrdersDTO;
import lk.ijse.spring.entity.Item;
import lk.ijse.spring.entity.OrderDetails;
import lk.ijse.spring.entity.Orders;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.repo.ItemRepo;
import lk.ijse.spring.repo.OrderDetailsRepo;
import lk.ijse.spring.repo.OrdersRepo;
import lk.ijse.spring.service.PurchaseOrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    OrdersRepo ordersRepo;
    @Autowired
    OrderDetailsRepo orderDetailsRepo;
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ModelMapper mapper;



    @Override
    public void  purchaseOrder(OrdersDTO ordersDTO, OrderDetailsDTO orderDetailsDTO) {
        if (ordersRepo.existsById(ordersDTO.getOid())) {
            throw new RuntimeException(ordersDTO.getOid()+" is already available, please insert a new ID");
        }
        if (!customerRepo.existsById(ordersDTO.getCusID())) {
            throw new RuntimeException(ordersDTO.getOid()+" is not available, please enter valid customer ID");
        }
        if (!itemRepo.existsById(orderDetailsDTO.getItemCode())) {
            throw new RuntimeException(ordersDTO.getOid()+" is not available, please enter valid item code");
        }
        Orders map = mapper.map(ordersDTO, Orders.class);
        ordersRepo.save(map);

        OrderDetails map1 = mapper.map(orderDetailsDTO, OrderDetails.class);
        orderDetailsRepo.save(map1);

        Item item = itemRepo.findById(orderDetailsDTO.getItemCode()).get();
        itemRepo.save(item);
        item.setQtyOnHand(item.getQtyOnHand()-orderDetailsDTO.getQty());


    }

    @Override
    public List<OrdersDTO> getAllOrders(){
        List<Orders> all = ordersRepo.findAll();
        return mapper.map(all, new TypeToken<List<OrdersDTO>>() {
        }.getType());
    }


}
