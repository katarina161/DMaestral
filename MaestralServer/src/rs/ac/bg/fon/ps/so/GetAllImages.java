/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.UserImage;

/**
 *
 * @author Katarina
 */
public class GetAllImages extends AbstractSystemOperation{
    
    private List<UserImage> images;

    public GetAllImages() {
        this.images = new ArrayList<>();
    }

    @Override
    protected void checkPreconditions() throws Exception {
    }

    @Override
    protected void executeSpecificOperation() throws Exception {
        images = (List<UserImage>) repository.getAll(new UserImage());
    }

    public List<UserImage> getImages() {
        return images;
    }
    
}
