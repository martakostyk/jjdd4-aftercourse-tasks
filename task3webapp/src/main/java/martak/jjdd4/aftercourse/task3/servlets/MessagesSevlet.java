package martak.jjdd4.aftercourse.task3.servlets;

import martak.jjdd4.aftercourse.task3.data.MessageRepository;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/message")
public class MessagesSevlet extends HttpServlet {

    @Inject
    private MessageRepository messageRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final PrintWriter printWriter = resp.getWriter();

        messageRepository.getMessages().forEach((date, message) -> {
            printWriter.println(date + ": " + message);
        });
    }
}
