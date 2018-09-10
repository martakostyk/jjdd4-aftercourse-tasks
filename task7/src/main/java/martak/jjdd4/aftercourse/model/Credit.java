package martak.jjdd4.aftercourse.model;

import java.math.BigDecimal;

public class Credit {

    private final double WIBOR_3M = 1.71;
    private BigDecimal sum;
    private int months;
    private double bankMargin;
    private double bankCommition;

    public Credit(BigDecimal sum, int months, double bankMargin, double bankCommition) {
        this.sum = sum;
        this.months = months;
        this.bankMargin = bankMargin;
        this.bankCommition = bankCommition;
    }

    public Credit() {
    }

    public double getWIBOR_3M() { return WIBOR_3M; }

    public BigDecimal getSum() {
        return sum;
    }

    public int getMonths() {
        return months;
    }

    public double getBankMargin() {
        return bankMargin;
    }

    public double getBankCommition() {
        return bankCommition;
    }

}
