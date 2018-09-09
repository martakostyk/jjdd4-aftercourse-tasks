package martak.jjdd4.aftercourse.beans;

import martak.jjdd4.aftercourse.model.Credit;
import martak.jjdd4.aftercourse.servlets.GetCreditParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import java.math.BigDecimal;

@RequestScoped
public class RepaymantSimulation {

    private final Logger LOG = LoggerFactory.getLogger(RepaymantSimulation.class);

    private static final double WIBOR_3M = 1.71;

    public BigDecimal calculateInstallment(Credit credit) {

        final double nominalInterest = WIBOR_3M + credit.getBankMargin();
        LOG.info("nominal interest {}", nominalInterest);

        final BigDecimal percentageRatio = new BigDecimal(1 + nominalInterest/1200);
        LOG.info("percentage ratio {}", percentageRatio);

        final BigDecimal loanSum = new BigDecimal(credit.getSum().doubleValue() * (100 + credit.getBankCommition()) / 100);
        LOG.info("loan sum {}", loanSum);

        return new BigDecimal(loanSum.doubleValue() * Math.pow(percentageRatio.doubleValue(), credit.getMonths()) * (percentageRatio.doubleValue() -1)
                / (Math.pow(percentageRatio.doubleValue(), credit.getMonths()) -1));

    }
}
