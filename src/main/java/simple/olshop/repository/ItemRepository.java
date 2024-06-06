package simple.olshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.olshop.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
