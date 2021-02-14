/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import java.util.Arrays;
import java.util.List;
import rs.ac.bg.fon.ps.domain.InvoiceItem;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.ProductSize;
import rs.ac.bg.fon.ps.domain.Size;

/**
 *
 * @author Katarina
 */
public class DeleteProduct extends AbstractSystemOperation{
    
    private Product product;

    public DeleteProduct(Product product) {
        this.product = product;
    }

    @Override
    protected void checkPreconditions() throws Exception {
        if (!repository.getAll(new InvoiceItem(), Arrays.asList("product_article"), Arrays.asList(product.getArticle())).isEmpty()) {
            throw new Exception("You cannot delete a product for which an invoice has been made.");
        }
    }

    @Override
    protected void executeSpecificOperation() throws Exception {
        List<ProductSize> pss 
                = (List<ProductSize>) repository.getAll(new ProductSize(), Arrays.asList("product_article"), Arrays.asList(product.getArticle()));
        for (ProductSize ps: pss) {
            repository.delete(ps);
        }
        repository.delete(product);
    }
    
}
