/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finance;

import java.sql.SQLException;

/**
 *
 * @author Jan
 */
public class TeacherModel extends BaseModel{
    
    protected static String _tableName = "TBL_TEACHER";
    
    public TeacherModel() {
        super.__construct(_tableName);
    }
    
    public void insert(String[] data, int hrs_rendered) {
        
        try {
            
            stmt = conn.createStatement();
            String sql = "INSERT INTO " + this._tableName + " VALUES (";
            
            for(int i = 0; i < data.length ; i++) {
                sql += data[i] + ", ";
            }
            
            sql += hrs_rendered;
            
            sql += ")";
            
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
    }
}
