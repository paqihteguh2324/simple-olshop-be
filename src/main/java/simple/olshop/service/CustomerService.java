package simple.olshop.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import simple.olshop.dto.customer.CustomerListDto;
import simple.olshop.dto.customer.CustomerRequestDto;
import simple.olshop.dto.customer.CustomerResponseDto;
import simple.olshop.model.Customers;
import simple.olshop.repository.CustomerRepository;

import java.util.List;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerListDto> getAllCustomer(){
        List<Customers> customers = customerRepository.findAll();

        return customers.stream().map(
                customer -> CustomerListDto.builder()
                        .customerName(customer.getCustomerName())
                        .customerPhone(customer.getCustomerPhone())
                        .customerId(customer.getCustomerId())
                        .pic(customer.getPic())
                        .isActive(customer.getIsActive())
                        .build()
        ).toList();
    }

    public CustomerResponseDto getCustomerById(Integer customerId) {
        Customers customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer with id " + customerId + " not found"));

        return CustomerResponseDto.builder()
                .customerId(customer.getCustomerId())
                .customerName(customer.getCustomerName())
                .customerAddress(customer.getCustomerAddress())
                .customerCode(customer.getCustomerCode())
                .customerPhone(customer.getCustomerPhone())
                .isActive(customer.getIsActive())
                .lastOrderDate(customer.getLastOrderDate())
                .pic(customer.getPic())
                .build();
    }

    public Customers createCustomer(CustomerRequestDto customerRequestDto){
        log.info(String.valueOf(customerRequestDto));
        Customers customers = Customers.builder()
                .customerName(customerRequestDto.getCustomerName())
                .customerCode(customerRequestDto.getCustomerCode())
                .customerAddress(customerRequestDto.getCustomerAddress())
                .customerPhone(customerRequestDto.getCustomerPhone())
                .isActive(customerRequestDto.getIsActive())
                .pic(customerRequestDto.getPic())
                .build();

        customerRepository.save(customers);
        return ResponseEntity.ok().body(customers).getBody();
    }

    public Customers updateCustomer(int id, CustomerRequestDto customerRequestDto){
        Customers customers = Customers.builder()
                .customerId(id)
                .customerName(customerRequestDto.getCustomerName())
                .customerPhone(customerRequestDto.getCustomerPhone())
                .customerAddress(customerRequestDto.getCustomerAddress())
                .isActive(customerRequestDto.getIsActive())
                .pic(customerRequestDto.getPic())
                .customerCode(customerRequestDto.getCustomerCode()).build();
        customerRepository.save(customers);
        return ResponseEntity.ok().body(customers).getBody();
    }

    public Customers deleteCustomer(int id){
        Customers customers = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer with id " + id + " not found"));
        customers.setIsActive(false);
        customerRepository.save(customers);
        return customers;
    }
}
