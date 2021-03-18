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
    GET_ALL_IMAGES,
    LOG_IN, 
    LOG_OUT,
    SAVE_USER,
    UPDATE_USER,
    GET_ALL_USERS,
    GET_FILTERED_USERS,
    REFRESH_USERS,
    SAVE_PRODUCT, 
    UPDATE_PRODUCT, 
    DELETE_PRODUCT,
    GET_ALL_PRODUCTS, 
    REFRESH_PRODUCTS,
    GET_FILTERED_PRODUCTS,
    SAVE_INVOICE,
    UPDATE_INVOICE,
    DELETE_INVOICE,
    PROCESS_INVOICE,
    CANCEL_INVOICE,
    GET_ALL_INVOICES,
    GET_FILTERED_INVOICES,
    REFRESH_INVOICES,
    GET_ALL_CATEGORIES, 
    GET_ALL_SIZES,
    GENERATE_INVOICE_NUMBER,
    GENERATE_REPORT,
    SET_LANGUAGE
}
