package simple.olshop.dto.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ItemRequestDto {
    private String itemName;
    private String itemCode;
    private Integer stock;
    private BigInteger price;
    private Boolean isAvailable;
    private LocalDateTime lastReStock;
}
