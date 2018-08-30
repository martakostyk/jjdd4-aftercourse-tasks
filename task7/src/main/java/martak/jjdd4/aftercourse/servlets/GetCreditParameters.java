package martak.jjdd4.aftercourse.servlets;

import martak.jjdd4.aftercourse.beans.ValidateCreditParameters;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/parameters")
public class GetCreditParameters extends HttpServlet {

    @Inject
    ValidateCreditParameters validateCreditParameters;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
