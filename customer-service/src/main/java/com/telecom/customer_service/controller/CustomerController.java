package com.telecom.customer_service.controller;


import com.telecom.customer_service.dto.ResponseDto;
import com.telecom.customer_service.entity.Customer;
import com.telecom.customer_service.exception.GlobalExceptionHandler;
import com.telecom.customer_service.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

/*. temporary api to test exception
    @GetMapping("/test-error")
    public String testError() {
        throw new RuntimeException("Testing global exception handler");
    }
*/

    @GetMapping("/test-error")
    public String testError() {
        throw new RuntimeException("Testing SLF4J logging");
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {

        Customer customer = customerService.getCustomerById(id);

        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<Customer>> getAllCustomers() {

        List<Customer> customers = customerService.getAllCustomers();

        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer customer) {

        Customer updatedCustomer = customerService.updateCustomer(id, customer);

        return ResponseEntity.status(HttpStatus.OK).body(updatedCustomer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteCustomer(@PathVariable Long id) {

        customerService.deleteCustomer(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto("200", "Customer deleted successfully"));
    }
}
