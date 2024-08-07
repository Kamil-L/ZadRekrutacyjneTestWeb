package cucumber.utils;

import cucumber.models.Product;

public class TestContext {
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}