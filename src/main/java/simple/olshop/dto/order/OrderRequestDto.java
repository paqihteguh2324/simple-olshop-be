package simple.olshop.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OrderRequestDto {
    private String orderCode;
    private LocalDateTime orderDate;
    private BigInteger totalPrice;
    private Integer idCustomer;
    private Integer quantity;
    private Integer idItem;
}
