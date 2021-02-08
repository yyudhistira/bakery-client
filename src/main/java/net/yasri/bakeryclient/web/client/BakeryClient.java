package net.yasri.bakeryclient.web.client;

import net.yasri.bakeryclient.web.model.BreadDto;
import net.yasri.bakeryclient.web.model.CustomerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
public class BakeryClient {
    public final String BREAD_PATH_V1 = "/api/v1/bread/";
    private final String CUSTOMER_PATH_V1 = "/api/v1/customer";
    private String apihost;

    private final RestTemplate restTemplate;

    public BakeryClient(@Value("${net.yasri.apihost}") String apihost,
                        RestTemplateBuilder restTemplateBuilder) {
        this.apihost = apihost;
        this.restTemplate = restTemplateBuilder.build();
    }

    public BreadDto getBreadById(UUID uuid) {
        return restTemplate.getForObject(apihost + BREAD_PATH_V1 + uuid, BreadDto.class);
    }

    public URI saveNewBread(BreadDto breadDto) {
        return restTemplate.postForLocation(apihost + BREAD_PATH_V1, breadDto);
    }

    public void updateBread(UUID uuid, BreadDto breadDto) {
        restTemplate.put(apihost + BREAD_PATH_V1 + "/" + uuid, breadDto);
    }

    public void deleteBread(UUID uuid) {
        restTemplate.delete(apihost + BREAD_PATH_V1 + "/" + uuid);
    }

    public CustomerDto getCustomerById(UUID uuid) {
        return restTemplate.getForObject(apihost + CUSTOMER_PATH_V1 + "/" + uuid, CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto newCustomer) {
        return restTemplate.postForLocation(apihost + CUSTOMER_PATH_V1, newCustomer);
    }

    public void updateCustomer(UUID uuid, CustomerDto customerDto) {
        restTemplate.put(apihost + CUSTOMER_PATH_V1 + "/" + uuid, customerDto);
    }

    public void deleteCustomer(UUID uuid) {
        restTemplate.delete(apihost + CUSTOMER_PATH_V1 + "/" + uuid);
    }

}
