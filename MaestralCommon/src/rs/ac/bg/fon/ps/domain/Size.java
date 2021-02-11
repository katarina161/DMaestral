/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.io.Serializable;

/**
 *
 * @author Katarina
 */
public class Size implements Serializable{
    
    private Long id;
    private int sizeNumber;

    public Size() {
    }

    public Size(Long id, int sizeNumber) {
        this.id = id;
        this.sizeNumber = sizeNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSizeNumber() {
        return sizeNumber;
    }

    public void setSizeNumber(int sizeNumber) {
        this.sizeNumber = sizeNumber;
    }

    @Override
    public String toString() {
        return String.valueOf(sizeNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Size other = (Size) obj;
        if (this.sizeNumber != other.sizeNumber) {
            return false;
        }
        return true;
    }
    
    
    
}
