package com.mycompany.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AppTest
{
    @LocalServerPort
    private int port;

    @Test
    public void testAppConstructor() {
        App app1 = new App();
        App app2 = new App();
        assertEquals(app1.getMessage(), app2.getMessage());
    }

    @Test
    public void testAppMessage()
    {
        App app = new App();
        assertEquals("Hello World!", app.getMessage());
    }

    @Test
    public void testRestEndpoint() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        String url = "http://localhost:" + port + "/hello";
        App.MessageResponse response = restTemplate.getForObject(url, App.MessageResponse.class);
        assertEquals("Hello World!", response.getMessage());
    }
}
