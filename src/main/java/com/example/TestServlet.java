package com.example;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class TestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String param1 = request.getParameter("param1");

        String message = "success";

        try {
            doAction(param1);
        } catch(InvalidInputException e) {
            message = "failure: " + e.getMessage();
        }

        response.setContentType("application/json");

        PrintWriter pw = response.getWriter();

        pw.println(message);
        pw.flush();
    }

    private void doAction(String param1) throws InvalidInputException {
        if(param1 == null) {
            throw new InvalidInputException("param1 is required");
        }
    }

    static class InvalidInputException extends Exception {
        public InvalidInputException(String msg) {
            super(msg);
        }
    }
}
