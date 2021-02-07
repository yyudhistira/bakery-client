package net.yasri.bakeryclient.web.client;

import net.yasri.bakeryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerClientTest {

    @Autowired
    CustomerClient customerClient;

    @Test
    void getCustomerById() {
        // WHEN
        CustomerDto customerDto = customerClient.getCustomerById(UUID.randomUUID());

        // THEN
        assertNotNull(customerDto);
    }

    @Test
    void saveNewCustomer() {
        // GIVEN
        CustomerDto customerDto = CustomerDto.builder()
            .name("New Customer")
            .build();

        // WHEN
        URI newCustomer = customerClient.saveNewCustomer(customerDto);

        // THEN
        assertNotNull(newCustomer);
    }

    @Test
    void updateCustomer() {
        // GIVEN
        CustomerDto customerDto = CustomerDto.builder()
            .name("New Customer")
            .build();

        // WHEN
        customerClient.updateCustomer(UUID.randomUUID(), customerDto);

        // THEN
    }

    @Test
    void deleteCustomer() {
        // WHEN
        customerClient.deleteCustomer(UUID.randomUUID());

        // THEN
    }

}