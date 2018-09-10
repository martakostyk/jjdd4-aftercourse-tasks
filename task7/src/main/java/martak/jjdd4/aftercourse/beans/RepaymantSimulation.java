package martak.jjdd4.aftercourse.beans;

import martak.jjdd4.aftercourse.model.Credit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@RequestScoped
public class RepaymantSimulation {

    private final Logger LOG = LoggerFactory.getLogger(RepaymantSimulation.class);

    public BigDecimal getSimulation(Credit credit) {

        final BigDecimal loanSum = credit.getSum()
                .multiply(new BigDecimal((100 + credit.getBankCommition()) / 100));
        LOG.info("loan sum {}", loanSum);

        final double nominalInterest = credit.getWIBOR_3M() + credit.getBankMargin();
        LOG.info("nominal interest {}", nominalInterest);

        final BigDecimal percentageRatio = new BigDecimal(1 + nominalInterest/1200);
        LOG.info("percentage ratio {}", percentageRatio);

        BigDecimal installment = new BigDecimal(loanSum.doubleValue()
                * Math.pow(percentageRatio.doubleValue(), credit.getMonths())
                * (percentageRatio.doubleValue() - 1)
                / (Math.pow(percentageRatio.doubleValue(), credit.getMonths()) - 1))
                .setScale(2, RoundingMode.HALF_UP);
        LOG.info("installment {}", installment);

        return installment;

    }
}
