/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Katarina
 */
public interface DomainObject {

    public String getTableName();
    public String getParameterNames();
    public String getParameterValues();
    public String getPrimaryKeyName();
    public Long getPrimaryKeyValue();
    public void setPrimaryKey(Long key);
    public String getJoinCondition();
    public String getAllias();
    public String getUpdateQuery();
    public List<DomainObject> convertRSList(ResultSet rs);
}
