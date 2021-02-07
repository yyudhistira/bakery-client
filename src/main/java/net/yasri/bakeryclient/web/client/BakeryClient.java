package net.yasri.bakeryclient.web.client;

import net.yasri.bakeryclient.web.model.BreadDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "net.yasri", ignoreUnknownFields = false)
public class BakeryClient {
    public final String BREAD_PATH_V1 = "/api/v1/bread/";
    private String apihost;

    private final RestTemplate restTemplate;

    public BakeryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BreadDto getBreadById(UUID uuid) {
        return restTemplate.getForObject(apihost + BREAD_PATH_V1 + uuid.toString(), BreadDto.class);
    }

    public URI saveNewBread(BreadDto breadDto) {
        return restTemplate.postForLocation(apihost + BREAD_PATH_V1, breadDto);
    }

    public BreadDto saveNewBreadAsObject(BreadDto breadDto) {
        return restTemplate.postForObject(apihost + BREAD_PATH_V1, breadDto, BreadDto.class);
    }

    public void updateBread(UUID uuid, BreadDto breadDto) {
        restTemplate.put(apihost + BREAD_PATH_V1 + "/" + uuid.toString(), breadDto);
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }
}
