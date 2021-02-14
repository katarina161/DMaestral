/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import java.util.List;
import rs.ac.bg.fon.ps.domain.Size;

/**
 *
 * @author Katarina
 */
public class GetAllSizes extends AbstractSystemOperation{
    
    private List<Size> sizes;

    @Override
    protected void checkPreconditions() throws Exception {
    }

    @Override
    protected void executeSpecificOperation() throws Exception {
        sizes = (List<Size>) repository.getAll(new Size());
    }

    public List<Size> getSizes() {
        return sizes;
    }
    
}
