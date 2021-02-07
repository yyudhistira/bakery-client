package net.yasri.bakeryclient.web.client;

import net.yasri.bakeryclient.web.model.BreadDto;
import net.yasri.bakeryclient.web.model.BreadStyleEnum;
import net.yasri.bakeryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BakeryClientTest {

    @Autowired
    BakeryClient bakeryClient;

    @Test
    void getBreadById() {
        BreadDto breadDto = bakeryClient.getBreadById(UUID.randomUUID());

        assertNotNull(breadDto);
    }

    @Test
    void saveNewBread() {
        // GIVEN
        BreadDto newBread = BreadDto.builder()
            .breadName("New bread")
            .breadStyle(BreadStyleEnum.STUFFED)
            .upc(new Random().nextLong())
            .build();

        // WHEN
        URI uri = bakeryClient.saveNewBread(newBread);

        // THEN
        assertNotNull(uri);
    }

    @Test
    void updateBread() {
        // GIVEN
        BreadDto breadDto = BreadDto.builder()
            .breadName("Updated bread")
            .build();

        // WHEN
        bakeryClient.updateBread(UUID.randomUUID(), breadDto);

        // THEN
    }

    @Test
    void deleteBread() {
        // WHEN
        bakeryClient.deleteBread(UUID.randomUUID());

        // THEN
    }

    @Test
    void getCustomerById() {
        // WHEN
        CustomerDto customerDto = bakeryClient.getCustomerById(UUID.randomUUID());

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
        URI newCustomer = bakeryClient.saveNewCustomer(customerDto);

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
        bakeryClient.updateCustomer(UUID.randomUUID(), customerDto);

        // THEN
    }

    @Test
    void deleteCustomer() {
        // WHEN
        bakeryClient.deleteCustomer(UUID.randomUUID());

        // THEN
    }

}