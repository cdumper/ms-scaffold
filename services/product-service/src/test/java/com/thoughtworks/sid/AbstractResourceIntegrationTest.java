package com.thoughtworks.sid;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "eureka.client.enabled=false",
                "spring.config.enabled=false"
        }
)
@AutoConfigureWebTestClient
public abstract class AbstractResourceIntegrationTest {
    @LocalServerPort
    protected int port;

    @Autowired
    protected WebTestClient webClient;

    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    Flyway flyway;

    @Before
    public void before() {
        flyway.clean();
        flyway.migrate();
    }

    @After
    public void after() {
        flyway.clean();
    }
}
