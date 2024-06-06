package simple.olshop.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CustomerListDto{
    private Integer customerId;
    private String customerName;
    private String customerPhone;
    private boolean isActive;
    private String pic;
}