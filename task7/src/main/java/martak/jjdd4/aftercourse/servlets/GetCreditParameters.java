package martak.jjdd4.aftercourse.servlets;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import martak.jjdd4.aftercourse.beans.RepaymantSimulation;
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

@WebServlet(urlPatterns = "/credit")
public class GetCreditParameters extends HttpServlet {

    private final Logger LOG = LoggerFactory.getLogger(GetCreditParameters.class);

    @Inject
    private RepaymantSimulation repaymantSimulation;

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
            double installment = repaymantSimulation.calculateInstallment(credit);
            dataModel.put("credit", credit);
            dataModel.put("installment", installment);
            resp.getWriter().write(String.valueOf(installment));
            LOG.info(String.valueOf(installment));

        }

//        try {
//            template.process(dataModel, resp.getWriter());
//        } catch (TemplateException e) {
//            LOG.warn("Template not found");
//        }

    }

    private Credit createCredit(HttpServletRequest req) {

        Credit credit = null;

        try {
            BigDecimal sum = new BigDecimal(req.getParameter("sum"));
            int months = Integer.valueOf(req.getParameter("months"));
            double bankMargin = Double.valueOf(req.getParameter("bankMargin"));
            double bankCommition = Double.valueOf(req.getParameter("bankCommition"));

            return new Credit(sum, months, bankMargin, bankCommition);

        } catch (Exception e) {
            LOG.error("Could not create credit object");
        }

        return credit;
    }
}
