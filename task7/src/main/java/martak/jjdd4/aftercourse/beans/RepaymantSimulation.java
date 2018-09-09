package martak.jjdd4.aftercourse.beans;

import martak.jjdd4.aftercourse.model.Credit;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class RepaymantSimulation {

    private static final double WIBOR_3M = 1.71;

    public double calculateInstallment(Credit credit) {

        final double nominalInterest = WIBOR_3M + credit.getBankMargin();

        final double percentageRatio = 1 + 1/12 * nominalInterest/100;

        final double loanSum = credit.getSum().doubleValue() * (100 + credit.getBankCommition()) / 100;

        return loanSum * Math.pow(percentageRatio, credit.getMonths()) * (percentageRatio -1)
                / (Math.pow(percentageRatio, credit.getMonths()) -1);
    }
}
