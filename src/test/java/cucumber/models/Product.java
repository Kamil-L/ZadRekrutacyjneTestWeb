package cucumber.models;

import java.math.BigDecimal;

public class Product {

    private String name;
    private BigDecimal initialPaymentPrice;
    private BigDecimal monthlyPaymentPrice;

    public Product(String name, BigDecimal initialPaymentPrice, BigDecimal monthlyPaymentPrice) {
        this.name = name;
        this.initialPaymentPrice = initialPaymentPrice;
        this.monthlyPaymentPrice = monthlyPaymentPrice;
    }
}