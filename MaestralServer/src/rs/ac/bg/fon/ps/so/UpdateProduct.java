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
public class UpdateProduct extends AbstractSystemOperation {

    private Product product;

    public UpdateProduct(Product product) {
        this.product = product;
    }

    @Override
    protected void checkPreconditions() throws Exception {
    }

    @Override
    protected void executeSpecificOperation() throws Exception {
        repository.update(product);
        
        List<ProductSize> pss
                = (List<ProductSize>) repository.getAll(
                        new ProductSize(),
                        Arrays.asList("product_article"),
                        Arrays.asList(product.getArticle()));

        List<Size> productSizes = product.getSizes();

        for (ProductSize ps : pss) {
            if (!productSizes.contains(ps.getSize())) {
                repository.delete(ps);
            }
        }
        
        List<Size> dbSizes = new ArrayList<>();
        pss.forEach(ps -> {
            dbSizes.add(ps.getSize());
        });
        for (Size s : productSizes) {
            if (!dbSizes.contains(s)) {
                repository.add(new ProductSize(product, s));
            }
        }
    }

}
