package simple.olshop.dto.item;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemResponseDto {
    private Integer itemId;
    private String itemName;
    private String itemCode;
    private Integer stock;
    private BigInteger price;
    private Boolean isAvailable;
    private LocalDateTime lastReStock;
}
