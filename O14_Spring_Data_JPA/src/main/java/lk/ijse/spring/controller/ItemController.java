package lk.ijse.spring.controller;


import lk.ijse.spring.dto.ItemDTO;

import lk.ijse.spring.service.ItemService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {

    @Autowired
    ItemService Iservice;

    @PostMapping
    public ResponseUtil addItem(ItemDTO dto){
        Iservice.addItem(dto);
        return new ResponseUtil("Ok","Successfully Added",dto);
    }

    @DeleteMapping(params = {"code"})
    public ResponseUtil deleteItem(String code){
        Iservice.deleteItem(code);
        return new ResponseUtil("Ok","Successfully Deleted",code);
    }

    @GetMapping
    public ResponseUtil getAllItem(){
        return new ResponseUtil("Ok","Successfully Loaded",Iservice.getAllItems());
    }

    @GetMapping(params = {"code"})
    public ResponseUtil findItem(String code){
        return new ResponseUtil("Ok","Successfully", Iservice.findItem(code));
    }

    @PutMapping
    public ResponseUtil updateItem(@RequestBody ItemDTO c){
        Iservice.updateItem(c);
        return new ResponseUtil("Ok","Successfully Updated",c);
    }



}
