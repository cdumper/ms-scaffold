package com.thoughtworks.sid;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest
public abstract class AbstractResourceUnitTest {
    protected final String BASE_URI = "http://localhost";
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(getTestingController())
                .build();
    }

    protected abstract Object getTestingController();
}
