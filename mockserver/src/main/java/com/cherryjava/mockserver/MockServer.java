package com.cherryjava.mockserver;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ClasspathFileSource;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.standalone.JsonFileMappingsSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

public class MockServer implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(MockServer.class);

    private WireMockServer mockServer;

    public MockServer(Integer port, String path) {
        try {
            ClasspathFileSource classpathFileSource = new ClasspathFileSource(path);
            JsonFileMappingsSource jsonFileMappingsSource = new JsonFileMappingsSource(classpathFileSource);
            FakeTemplateTransformer fakeTemplateTransformer = new FakeTemplateTransformer();
            for (FakeTemplateHelpers fakeTemplateHelpers : FakeTemplateHelpers.values()) {
                fakeTemplateTransformer.addHelpers(fakeTemplateHelpers.name(), fakeTemplateHelpers);
            }
            fakeTemplateTransformer.init();
            WireMockConfiguration config = WireMockConfiguration.options().mappingSource(jsonFileMappingsSource).notifier(new ConsoleNotifier(true)).extensions(fakeTemplateTransformer).port(port);
            WireMockServer wireMockServer = new WireMockServer(config);
            this.mockServer = wireMockServer;

        } catch (Exception e) {
            log.debug("mocksever started failed" + e.getMessage());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        mockServer.start();
    }
}
