package com.thoughtworks.sid.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.sid.ProductServiceApplication;
import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductServiceApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "eureka.client.enabled=false",
                "spring.config.enabled=false"
        }
)
@AutoConfigureWebTestClient
abstract class IntegrationTest {
    @LocalServerPort
    int port;

    @Autowired
    WebTestClient webClient;

    @Autowired
    ObjectMapper mapper;

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
