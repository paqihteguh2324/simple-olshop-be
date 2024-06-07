package simple.olshop.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OrderRequestDto {
    private Integer totalPrice;
    private Integer idCustomer;
    private Integer idItem;
}
