/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.exception;

/**
 *
 * @author Katarina
 */
public class ProductAlreadyExistException extends Exception{

    public ProductAlreadyExistException(String message) {
        super(message);
    }
    
}
