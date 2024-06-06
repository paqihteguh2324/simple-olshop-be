package simple.olshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.olshop.model.Customers;

public interface CustomerRepository extends JpaRepository<Customers, Integer> {
}
