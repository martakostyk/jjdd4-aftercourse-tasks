package martak.jjdd4.aftercourse.model;

import java.math.BigDecimal;

public class Credit {

    private BigDecimal sum;
    private int months;
    private int margin;
    private int commition;

    public Credit(BigDecimal sum, int months, int margin, int commition) {
        this.sum = sum;
        this.months = months;
        this.margin = margin;
        this.commition = commition;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public int getCommition() {
        return commition;
    }

    public void setCommition(int commition) {
        this.commition = commition;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Credit{");
        sb.append("sum=").append(sum);
        sb.append(", months=").append(months);
        sb.append(", margin=").append(margin);
        sb.append(", commition=").append(commition);
        sb.append('}');
        return sb.toString();
    }
}
