package com.yourcompany.webapp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MyServletTest {

    @Test
    void doGet_writesWelcomeMessage() throws ServletException, IOException {
        // Mock objects for request and response
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Create a MyServlet instance
        MyServlet servlet = new MyServlet();

        // Call the doGet method of the servlet
        servlet.doGet(request, response);

        // Verify that the response content type is set to text/html
        Mockito.verify(response).setContentType("text/html");

        // Capture the output written to the response
        StringBuilder output = new StringBuilder();
        Mockito.verify(response).getWriter().println(Mockito.anyString());
        Mockito.doAnswer(invocation -> {
            String message = (String) invocation.getArguments()[0];
            output.append(message);
            return null;
        }).when(response.getWriter()).println(Mockito.anyString());

        // Assert that the output contains the welcome message
        assertEquals("<h1>Welcome to Jenkins Practice App!</h1>", output.toString());
    }
}

