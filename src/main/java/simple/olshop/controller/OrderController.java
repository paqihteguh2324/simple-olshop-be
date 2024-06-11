package simple.olshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simple.olshop.dto.order.OrderRequestDto;
import simple.olshop.model.Order;
import simple.olshop.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getAll")
    public List<Order> getListOrder(){
        return orderService.getAll();
    }

    @GetMapping("/detail/{id}")
    public Order getDetail(@PathVariable Integer id){
        return orderService.detailOrder(id);
    }

    @PostMapping("/create")
    public Order createOrder(@RequestBody OrderRequestDto orderRequestDto){
        return orderService.createOrder(orderRequestDto);
    }

    @PutMapping("update/{id}")
    public Order updateOrder(@PathVariable Integer id, @RequestBody OrderRequestDto orderRequestDto){
        return orderService.updateOrder(id, orderRequestDto);
    }

    @DeleteMapping("delete/{id}")
    public Order delete(@PathVariable Integer id){
        Order order = orderService.deleteOrder(id);
        return ResponseEntity.ok().body(order).getBody();
    }
}
