package simple.olshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.olshop.model.Order;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findByOrderId(Integer orderId);
}
