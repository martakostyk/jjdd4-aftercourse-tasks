package martak.jjdd4.aftercourse.servlets;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import martak.jjdd4.aftercourse.freemarker.TemplateProvider;
import martak.jjdd4.aftercourse.model.Credit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/parameters")
public class GetCreditParameters extends HttpServlet {

    private final Logger LOG = LoggerFactory.getLogger(GetCreditParameters.class);


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

        Template template = templateProvider.getTemplate(getServletContext(), "credit-repayment-simulation.ftlh");
        resp.setContentType("text/html;charset=UTF-8");

        Credit credit = createCredit(req);

        Map<String, Object> dataModel = new HashMap<>();

        if (credit != null) {
            dataModel.put("credit", credit);
        }

        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            LOG.warn("Template not found");
        }

    }

    private Credit createCredit(HttpServletRequest req) {

        Credit credit = null;

        try {
            BigDecimal sum = new BigDecimal(req.getParameter("sum"));
            int months = Integer.valueOf(req.getParameter("months"));
            int margin = Integer.valueOf(req.getParameter("margin"));
            int commition = Integer.valueOf(req.getParameter("commition"));

            return new Credit(sum, months, margin, commition);

        } catch (Exception e) {
            LOG.error("Could not create credit credit object, error message: " + e.getMessage());
        }

        return credit;
    }
}
