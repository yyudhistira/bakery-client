package net.yasri.bakeryclient.web.client;

import net.yasri.bakeryclient.web.model.BreadDto;
import net.yasri.bakeryclient.web.model.BreadStyleEnum;
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
}