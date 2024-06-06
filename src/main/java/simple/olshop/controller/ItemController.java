package simple.olshop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simple.olshop.dto.customer.CustomerListDto;
import simple.olshop.dto.customer.CustomerRequestDto;
import simple.olshop.dto.customer.CustomerResponseDto;
import simple.olshop.dto.item.ItemListDto;
import simple.olshop.dto.item.ItemRequestDto;
import simple.olshop.dto.item.ItemResponseDto;
import simple.olshop.model.Customers;
import simple.olshop.model.Item;
import simple.olshop.service.CustomerService;
import simple.olshop.service.ItemService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ItemListDto>> getAllItems() {
        try {
            List<ItemListDto> items = itemService.getAllItem();
            if (items == null) {
                return ResponseEntity.noContent().build(); // Use build() for cleaner syntax
            }
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/get/{id}")
    public ItemResponseDto getById(@PathVariable int id){
        return itemService.getItemById(id);
    }

    @PostMapping("/create")
    public Item createItem(@RequestBody ItemRequestDto itemRequestDto) throws Exception {
        try{
            return itemService.createItem(itemRequestDto);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @PutMapping("/update/{id}")
    public Item item(@PathVariable int id, @RequestBody ItemRequestDto itemRequestDto) throws Exception {
        try{
            return itemService.updateItem(id, itemRequestDto);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public Item delete(@PathVariable int id) throws Exception {
        try {
            Item item = itemService.deleteItem(id);
            return ResponseEntity.ok().body(item).getBody();
        }catch (Exception e){
            throw new Exception(e);
        }
    }


}
