package simple.olshop.dto.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemListDto {
    private String itemName;
    private Integer stock;
    private BigInteger price;
    private Boolean isAvailable;
}
