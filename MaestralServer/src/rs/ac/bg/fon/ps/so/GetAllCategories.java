/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import java.util.List;
import rs.ac.bg.fon.ps.domain.Category;

/**
 *
 * @author Katarina
 */
public class GetAllCategories extends AbstractSystemOperation{
    
    private List<Category> categories;

    @Override
    protected void checkPreconditions() throws Exception {
    }

    @Override
    protected void executeSpecificOperation() throws Exception {
        categories = (List<Category>) repository.getAll(new Category());
    }

    public List<Category> getCategories() {
        return categories;
    }
    
}
