package simple.olshop.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CustomerRequestDto {
    private String customerName;
    private String customerAddress;
    private String customerCode;
    private String customerPhone;
    private Boolean isActive;
    private String pic;
}