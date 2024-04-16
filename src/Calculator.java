import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {
    public static BigDecimal calculateCost(BigDecimal rate, double quantity, double gstRate) {
        BigDecimal quantityBD = BigDecimal.valueOf(quantity);
        BigDecimal cost = rate.multiply(quantityBD);
        BigDecimal gst = cost.multiply(BigDecimal.valueOf(gstRate)).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        return cost.add(gst).setScale(2, RoundingMode.HALF_UP);
    }
}
