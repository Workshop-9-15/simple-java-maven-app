package com.mycompany.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Unit test for simple App.
 */
public class AppTest
{
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
    public void testMainMethodOutputCapture() {
        App.main(new String[]{});
        assertEquals("Hello World!" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void testMainMethodInvocation() {
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }

    @Test
    public void testMainMethodWithNullArguments() {
        assertDoesNotThrow(() -> App.main(null));
    }

    @Test
    public void testMainMethodWithEmptyArguments() {
        App.main(new String[]{});
        String output = outContent.toString();
        assertTrue(output.contains("Hello World!"));
    }

    @Test
    public void testMainMethodWithPopulatedArguments() {
        App.main(new String[]{"arg1", "arg2", "arg3"});
        String output = outContent.toString();
        assertEquals("Hello World!" + System.lineSeparator(), output);
    }
}
