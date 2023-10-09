package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.ItemDTO;
import lk.ijse.spring.entity.Item;
import lk.ijse.spring.repo.ItemRepo;
import lk.ijse.spring.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepo itemRepo;

    @Autowired
    ModelMapper mapper;


    @Override
    public void addItem(ItemDTO dto) {
        if (itemRepo.existsById(dto.getCode())) {
            throw new RuntimeException(dto.getCode()+" is already available, please insert a new ID");
        }

        Item map = mapper.map(dto, Item.class);
        itemRepo.save(map);
    }


    @Override
    public void deleteItem(String code) {
        if (!itemRepo.existsById(code)) {
            throw new RuntimeException(code+ " Customer is not available, please check the ID before delete.!");
        }
        itemRepo.deleteById(code);

    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> all = itemRepo.findAll();
        return mapper.map(all, new TypeToken<List<ItemDTO>>() {
        }.getType());
    }

    @Override
    public ItemDTO findItem(String code) {
        if (!itemRepo.existsById(code)) {
            throw new RuntimeException(code+ " Customer is not available, please check the ID.!");
        }
        Item item = itemRepo.findById(code).get();
        return mapper.map(item, ItemDTO.class);
    }

    @Override
    public void updateItem(ItemDTO i) {

        if (!itemRepo.existsById(i.getCode())) {
            throw new RuntimeException(i.getCode()+ " Customer is not available, please check the ID before update.!");
        }
       Item map = mapper.map(i, Item.class);
        itemRepo.save(map);

    }
}
