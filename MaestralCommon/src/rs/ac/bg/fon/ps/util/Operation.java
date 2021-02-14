/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.util;

import java.io.Serializable;

/**
 *
 * @author Katarina
 */
public enum Operation implements Serializable{
    LOG_IN, 
    LOG_OUT,
    SAVE_PRODUCT, 
    UPDATE_PRODUCT, 
    DELETE_PRODUCT,
    GET_ALL_PRODUCTS, 
    REFRESH_PRODUCTS,
    SAVE_INVOICE,
    UPDATE_INVOICE,
    GET_ALL_INVOICES,
    REFRESH_INVOICES,
    GET_ALL_CATEGORIES, 
    GET_ALL_SIZES,
    GENERATE_INVOICE_NUMBER,
    
}
