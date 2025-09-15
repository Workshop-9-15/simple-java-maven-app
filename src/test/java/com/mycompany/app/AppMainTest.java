package com.mycompany.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for App main method and console output.
 */
@SpringBootTest
@TestPropertySource(properties = {"server.port=0"})
public class AppMainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testMainMethodPrintsMessage() {
        String[] args = {};
        
        Thread mainThread = new Thread(() -> {
            try {
                App.main(args);
            } catch (Exception e) {
            }
        });
        
        mainThread.start();
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        String output = outContent.toString();
        assertTrue(output.contains("Hello World!"), 
                   "Main method should print 'Hello World!' to console. Actual output: " + output);
    }
}
