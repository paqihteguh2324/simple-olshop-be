package simple.olshop.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import simple.olshop.dto.item.ItemListDto;
import simple.olshop.dto.item.ItemRequestDto;
import simple.olshop.dto.item.ItemResponseDto;
import simple.olshop.model.Item;
import simple.olshop.repository.ItemRepository;

import java.util.List;

@Slf4j
@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<ItemListDto> getAllItem(){
        List<Item> item = itemRepository.findAll();

        return item.stream().map(
                items -> ItemListDto.builder()
                        .itemName(items.getItemName())
                        .stock(items.getStock())
                        .price(items.getPrice())
                        .isAvailable(items.getIsAvailable())
                        .build()
        ).toList();
    }

    public ItemResponseDto getItemById(Integer itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Customer with id " + itemId + " not found"));

        return ItemResponseDto.builder()
                .itemId(item.getItemId())
                .itemCode(item.getItemCode())
                .itemName(item.getItemName())
                .stock(item.getStock())
                .price(item.getPrice())
                .isAvailable(item.getIsAvailable())
                .lastReStock(item.getLastReStock())
                .build();
    }

    public Item createItem(ItemRequestDto itemRequestDto){
        log.info(String.valueOf(itemRequestDto));
        Item item = Item.builder()
                .itemName(itemRequestDto.getItemName())
                .isAvailable(itemRequestDto.getIsAvailable())
                .itemCode(itemRequestDto.getItemCode())
                .itemName(itemRequestDto.getItemName())
                .price(itemRequestDto.getPrice())
                .stock(itemRequestDto.getStock())
                .lastReStock(itemRequestDto.getLastReStock())
                .build();

        itemRepository.save(item);
        return ResponseEntity.ok().body(item).getBody();
    }

    public Item updateItem(int id, ItemRequestDto itemRequestDto){
        Item item = Item.builder()
                .itemId(id)
                .itemName(itemRequestDto.getItemName())
                .itemCode(itemRequestDto.getItemCode())
                .lastReStock(itemRequestDto.getLastReStock())
                .stock(itemRequestDto.getStock())
                .price(itemRequestDto.getPrice())
                .isAvailable(itemRequestDto.getIsAvailable())
                .build();
        itemRepository.save(item);
        return ResponseEntity.ok().body(item).getBody();
    }

    public Item deleteItem(int id){
        Item items = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer with id " + id + " not found"));
        items.setIsAvailable(false);
        itemRepository.save(items);
        return items;
    }
}
