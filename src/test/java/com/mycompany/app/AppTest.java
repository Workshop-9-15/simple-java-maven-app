package com.mycompany.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @Test
    public void testRestEndpointResponseEntity() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        String url = "http://localhost:" + port + "/hello";
        ResponseEntity<App.MessageResponse> responseEntity = restTemplate.getForEntity(url, App.MessageResponse.class);
        
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Hello World!", responseEntity.getBody().getMessage());
    }

    @Test
    public void testMessageResponseConstructor() {
        App.MessageResponse response = new App.MessageResponse("Test Message");
        assertEquals("Test Message", response.getMessage());
    }

    @Test
    public void testMessageResponseSetter() {
        App.MessageResponse response = new App.MessageResponse("Initial");
        response.setMessage("Updated Message");
        assertEquals("Updated Message", response.getMessage());
    }

    @Test
    public void testMessageResponseWithNullValue() {
        App.MessageResponse response = new App.MessageResponse(null);
        assertEquals(null, response.getMessage());
        
        response.setMessage("Not null anymore");
        assertEquals("Not null anymore", response.getMessage());
    }

    @Test
    public void testAppHelloMethod() {
        App app = new App();
        App.MessageResponse response = app.hello();
        assertNotNull(response);
        assertEquals("Hello World!", response.getMessage());
    }
}
