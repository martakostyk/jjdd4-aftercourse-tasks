package martak.jjdd4.aftercourse.servlets;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import martak.jjdd4.aftercourse.beans.ValidateCreditParameters;
import martak.jjdd4.aftercourse.freemarker.TemplateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/parameters")
public class GetCreditParameters extends HttpServlet {

    private final Logger LOG = LoggerFactory.getLogger(GetCreditParameters.class);

    @Inject
    private ValidateCreditParameters validateCreditParameters;

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Template template = templateProvider.getTemplate(getServletContext(), "get-credit-parameters.ftlh");
        resp.setContentType("text/html;charset=UTF-8");

        Map<String, Object> dataModel = new HashMap<>();

        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            LOG.warn("Template not found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().println("super!");
    }
}
