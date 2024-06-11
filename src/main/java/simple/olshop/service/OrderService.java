package simple.olshop.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simple.olshop.dto.order.OrderRequestDto;
import simple.olshop.model.Customers;
import simple.olshop.model.Item;
import simple.olshop.model.Order;
import simple.olshop.repository.CustomerRepository;
import simple.olshop.repository.ItemRepository;
import simple.olshop.repository.OrderRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomerRepository customerRepository;

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

    public Order detailOrder(Integer id){
        Optional<Order> orders = orderRepository.findByOrderId(id);
        if(orders.isEmpty()){
            throw new EntityNotFoundException("order tidak ditemukan");
        }else{
            return Order.builder()
                    .orderId(orders.get().getOrderId())
                    .orderDate(orders.get().getOrderDate())
                    .orderCode(orders.get().getOrderCode())
                    .totalPrice(orders.get().getTotalPrice())
                    .item(orders.get().getItem())
                    .quantity(orders.get().getQuantity())
                    .customers(orders.get().getCustomers())
                    .build();
        }
    }

    public Order deleteOrder(Integer id){
        Optional<Order> order = orderRepository.findByOrderId(id);
         if(order.isEmpty()){
             throw new EntityNotFoundException("order tidak ditemukan");
         }else{
             orderRepository.deleteById(id);
         }
        return null;
    }

    public Order createOrder(OrderRequestDto orderRequestDto){

        Item itemGet = itemRepository.findById(orderRequestDto.getIdItem())
        .orElseThrow(() -> new IllegalArgumentException("Item not found"));

        Customers customers = customerRepository.findById(orderRequestDto.getIdCustomer())
                .orElseThrow(()-> new IllegalArgumentException("customer not found"));
        customers.setLastOrderDate(Timestamp.valueOf(LocalDateTime.now()));

        Order order = Order.builder().orderCode(orderRequestDto.getOrderCode())
                .orderDate(orderRequestDto.getOrderDate())
                .item(itemGet)
                .totalPrice(orderRequestDto.getTotalPrice())
                .customers(customers)
                .quantity(orderRequestDto.getQuantity())
                .build();
        customerRepository.save(customers);
        orderRepository.save(order);
        return order;
    }

    public Order updateOrder(Integer id, OrderRequestDto orderRequestDto){
        Item itemGet = itemRepository.findById(orderRequestDto.getIdItem())
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));

        Customers customers = customerRepository.findById(orderRequestDto.getIdCustomer())
                .orElseThrow(()-> new IllegalArgumentException("customer not found"));
        customers.setLastOrderDate(Timestamp.valueOf(LocalDateTime.now()));

        Order existingOrder = orderRepository.findByOrderId(id)
                .orElseThrow(()-> new IllegalArgumentException("order id not found"));

        Order orders = Order.builder()
                .orderId(id)
                .orderCode(orderRequestDto.getOrderCode())
                .orderDate(orderRequestDto.getOrderDate())
                .item(itemGet)
                .totalPrice(orderRequestDto.getTotalPrice())
                .customers(customers)
                .quantity(orderRequestDto.getQuantity())
                .build();

        customerRepository.save(customers);

        orderRepository.save(orders);
        return orders;
    }


}
