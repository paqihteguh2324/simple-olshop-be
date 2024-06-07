package simple.olshop.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import simple.olshop.model.Order;
import simple.olshop.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll(){
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(
                order -> Order.builder().orderId(order.getOrderId()).orderCode(order.getOrderCode()).orderDate(order.getOrderDate())
                        .item(order.getItem())
                        .customers(order.getCustomers())
                        .totalPrice(order.getTotalPrice())
                        .quantity(order.getQuantity())
                        .build()
        ).toList();
    }

    public void deleteOrder(Integer id){
        Optional<Order> order = orderRepository.findByOrderId(id);
         if(order.isEmpty()){
             throw new EntityNotFoundException("order tidak ditemukan");
         }else{
             orderRepository.deleteById(id);
         }
    }


}
