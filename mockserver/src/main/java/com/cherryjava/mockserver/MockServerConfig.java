package com.cherryjava.mockserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockServerConfig {

    @Bean
    @ConditionalOnProperty(value = "mock.enable", havingValue = "true")
    public MockServer mockServer(@Value("$(mock.port)") Integer port, @Value("$(mock.filepath)") String path) {
        MockServer mockServer = new MockServer(port, path);
        return mockServer;
    }

}
