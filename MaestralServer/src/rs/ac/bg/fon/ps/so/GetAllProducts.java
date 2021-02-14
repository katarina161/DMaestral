/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.ProductSize;
import rs.ac.bg.fon.ps.domain.Size;

/**
 *
 * @author Katarina
 */
public class GetAllProducts extends AbstractSystemOperation {

    private List<Product> products;

    @Override
    protected void checkPreconditions() throws Exception {
    }

    @Override
    protected void executeSpecificOperation() throws Exception {
        products = repository.getAll(new Product());
        if (products != null) {
            for (Product product : products) {
                List<Size> sizes = new ArrayList<>();
                List<ProductSize> productSizes
                        = (List<ProductSize>) repository.getAll(
                                new ProductSize(),
                                Arrays.asList("product_article"),
                                Arrays.asList(product.getArticle()));
                productSizes.forEach(ps -> {
                    sizes.add(ps.getSize());
                });
                product.setSizes(sizes);
            }
        }
    }

    public List<Product> getProducts() {
        return products;
    }
}
