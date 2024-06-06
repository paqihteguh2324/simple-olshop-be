package simple.olshop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simple.olshop.dto.customer.CustomerListDto;
import simple.olshop.dto.customer.CustomerRequestDto;
import simple.olshop.dto.customer.CustomerResponseDto;
import simple.olshop.model.Customers;
import simple.olshop.service.CustomerService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerListDto>> getAllCustomers() {
        try {
            List<CustomerListDto> customers = customerService.getAllCustomer();
            if (customers == null) {
                return ResponseEntity.noContent().build(); // Use build() for cleaner syntax
            }
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/get/{id}")
    public CustomerResponseDto getById(@PathVariable int id){
        return customerService.getCustomerById(id);
    }

    @PostMapping("/create")
    public Customers createCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws Exception {
        try{
            log.info(String.valueOf(customerRequestDto.getIsActive()));
            return customerService.createCustomer(customerRequestDto);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @PutMapping("/update/{id}")
    public Customers customers(@PathVariable int id, @RequestBody CustomerRequestDto customerRequestDto) throws Exception {
        try{
            return customerService.updateCustomer(id, customerRequestDto);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public Customers delete(@PathVariable int id) throws Exception {
        try {
            Customers customers = customerService.deleteCustomer(id);
            return ResponseEntity.ok().body(customers).getBody();
        }catch (Exception e){
            throw new Exception(e);
        }
    }


}
