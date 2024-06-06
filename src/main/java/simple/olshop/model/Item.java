package simple.olshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "items")
public class Item {

    @Id
    @Column(name = "items_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;

    @Column(name = "items_name")
    private String itemName;

    @Column(name = "items_code")
    private String itemCode;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "price")
    private BigInteger price;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @Column(name = "last_re_stock")
    private LocalDateTime lastReStock;

}
