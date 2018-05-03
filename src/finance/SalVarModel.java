/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finance;

import static finance.BaseModel.stmt;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author Jan
 */
public class SalVarModel extends BaseModel {
    
    protected String _tableName = "TBL_SALARY_VARIABLE";
    
    public SalVarModel() {
        super.__construct(_tableName);
    }

    
    public double[] get() {
        
        double[] data = null;
        
        try {
            
            stmt =  conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_UPDATABLE);
            ResultSet results = stmt.executeQuery("SELECT * FROM " + this._tableName);
            results.last();
            ResultSetMetaData rsmd = results.getMetaData();
            data = new double[rsmd.getColumnCount()-2];

            for(int col = 2; col < rsmd.getColumnCount(); col++)
                data[col-2] = results.getDouble(col);
            
            results.close();
            stmt.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return data;
        }
    }
}
