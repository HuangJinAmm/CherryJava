package com.cherryjava.mockserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Configuration
public class MockServerConfig {

    /**
     * 配置文档见 https://wiremock.org/docs/
     *
     * @param port
     * @param path
     * @return
     */
    @Bean
    @ConditionalOnProperty(value = "mock.enable", havingValue = "true")
    public MockServer mockServer(@Value("${mock.port}") Integer port, @Value("${mock.filepath}") String path) {
        MockServer mockServer = new MockServer(port, path);
        //代码配置
        mockServer.addStub(s -> {
            s.stubFor(
                    post("/code").withRequestBody(matchingJsonPath("code")).willReturn(okJson("{'code':'ok'}"))
            );
        });

        return mockServer;
    }

}
