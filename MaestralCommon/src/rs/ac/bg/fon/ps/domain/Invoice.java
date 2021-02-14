/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Katarina
 */
public class Invoice implements DomainObject, Serializable {

    private Long id;
    private String number;
    private String partner;
    private Date date;
    private BigDecimal total;
    private boolean processed;
    private boolean canceld;
    private User user;
    private List<InvoiceItem> items;

    public Invoice() {
        this.items = new ArrayList<>();
        this.total = BigDecimal.ZERO;
    }

    public Invoice(String number, String partner, Date date, BigDecimal total, boolean processed, boolean canceld, User user) {
        this.number = number;
        this.partner = partner;
        this.date = date;
        this.total = total;
        this.processed = processed;
        this.canceld = canceld;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public boolean isCanceld() {
        return canceld;
    }

    public void setCanceld(boolean canceld) {
        this.canceld = canceld;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
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
        final Invoice other = (Invoice) obj;
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "invoice";
    }

    @Override
    public String getParameterNames() {
        return "id, number, partner, date, total, processed, canceled, user_id";
    }

    @Override
    public String getParameterValues() {
        return String.format("%s, '%s', '%s', '%s', %s, %s, %s, %s",
                id, number, partner, new java.sql.Date(date.getTime()), total, processed, canceld, user.getId());
    }

    @Override
    public String getPrimaryKeyName() {
        return "id";
    }

    @Override
    public Long getPrimaryKeyValue() {
        return id;
    }

    @Override
    public void setPrimaryKey(Long key) {
        this.id = key;
    }

    @Override
    public String getJoinCondition() {
        return "JOIN user u ON i.user_id = u.id";
    }

    @Override
    public String getAllias() {
        return "i";
    }

    @Override
    public String getUpdateQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("partner='").append(partner).append("'")
                .append(", date='").append(new java.sql.Date(date.getTime())).append("'")
                .append(", total=").append(total)
                .append(", user_id=").append(user.getId());
        return sb.toString();
    }

    @Override
    public List<DomainObject> convertRSList(ResultSet rs) {
        List<DomainObject> list = new ArrayList<>();
        try {
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getLong("u.id"));
                u.setFirstName(rs.getString("u.first_name"));
                u.setLastName(rs.getString("u.last_name"));
                u.setUsername(rs.getString("u.username"));
                u.setPassword(rs.getString("u.password"));

                Invoice i = new Invoice();
                i.setId(rs.getLong("i.id"));
                i.setNumber(rs.getString("i.number"));
                i.setPartner(rs.getString("i.partner"));
                i.setDate(new java.util.Date(rs.getDate("i.date").getTime()));
                i.setTotal(rs.getBigDecimal("i.total"));
                i.setProcessed(rs.getBoolean("i.processed"));
                i.setCanceld(rs.getBoolean("i.canceled"));
                i.setUser(u);
                
                list.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR ResultSet " + getTableName());
        }
        return list;
    }

}
