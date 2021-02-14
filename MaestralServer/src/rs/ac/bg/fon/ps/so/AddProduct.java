/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.ProductSize;
import rs.ac.bg.fon.ps.domain.Size;

/**
 *
 * @author Katarina
 */
public class AddProduct extends AbstractSystemOperation{
    
    private Product product;

    public AddProduct(Product product) {
        this.product = product;
    }

    @Override
    protected void checkPreconditions() throws Exception {
        if (repository.getByPrimaryKey(new Product(), this.product.getArticle()) != null) {
            throw new Exception("Product with article " +product.getArticle()+ " already exist in the system.");
        }
    }

    @Override
    protected void executeSpecificOperation() throws Exception {
        repository.add(product);
        for (Size s: product.getSizes()) {
            repository.add(new ProductSize(product, s));
        }
    }

    public Product getProduct() {
        return product;
    }
    
}
