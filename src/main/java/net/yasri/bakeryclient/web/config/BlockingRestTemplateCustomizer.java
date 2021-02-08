package net.yasri.bakeryclient.web.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    private final Integer maxTotalConnections;
    private final Integer defaultMaxPerRouteConnections;
    private final Integer connectionRequestTimeout;
    private final Integer socketTimeout;

    public BlockingRestTemplateCustomizer(@Value("${net.yasri.maxtotalconnections}") Integer maxTotalConnections,
                                          @Value("${net.yasri.defaultmaxperrouteconnections}") Integer defaultMaxPerRouteConnections,
                                          @Value("${net.yasri.connectionrequesttimeout}") Integer connectionRequestTimeout,
                                          @Value("${net.yasri.sockettimeout}") Integer socketTimeout) {
        this.maxTotalConnections = maxTotalConnections;
        this.defaultMaxPerRouteConnections = defaultMaxPerRouteConnections;
        this.connectionRequestTimeout = connectionRequestTimeout;
        this.socketTimeout = socketTimeout;
    }

    public ClientHttpRequestFactory clientHttpRequestFactory(){
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotalConnections);
        connectionManager.setDefaultMaxPerRoute(defaultMaxPerRouteConnections);

        RequestConfig requestConfig = RequestConfig
            .custom()
            .setConnectionRequestTimeout(connectionRequestTimeout)
            .setSocketTimeout(socketTimeout)
            .build();

        CloseableHttpClient httpClient = HttpClients
            .custom()
            .setConnectionManager(connectionManager)
            .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
            .setDefaultRequestConfig(requestConfig)
            .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}