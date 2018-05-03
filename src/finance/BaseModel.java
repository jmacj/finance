/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jan
 */
public class BaseModel {
    
    protected static String _database = "jdbc:derby://localhost:1527/FINANCE;create=true;user=finance;password=department;";
    protected static String _tableName = "";
    protected static String primary_key = "ID";
    protected static Connection conn = null;
    protected static Statement stmt = null;
    protected static Statement stmt2 = null;
    
    public BaseModel() {
        
    }
    public BaseModel(String _tablename){
        this.__construct(_tablename);
    }
    
    public void __construct(String _tableName) {
        
        this._tableName = _tableName;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(_database);            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void insert(String[] data){
        
        try {
            
            stmt = conn.createStatement();
            String sql = "INSERT INTO " + this._tableName + " VALUES (";
            
            for(int i = 0; i < data.length ; i++) {
                sql += data[i];
                if(i!= data.length - 1) 
                    sql += ", ";
            }
            sql += ")";
            
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
    }
    
    public String[][] get_all() {
        String[][] data = null;
        try {
           stmt = conn.createStatement();
           stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_UPDATABLE);
           ResultSet results = stmt.executeQuery("SELECT * FROM " + this._tableName);
           ResultSet count = stmt2.executeQuery("SELECT * FROM " + this._tableName);
           count.last();
           ResultSetMetaData rsmd = results.getMetaData();
           data = new String [count.getRow()][rsmd.getColumnCount()];
           while(results.next()){
               
               for(int col = 1; col <= rsmd.getColumnCount(); col++)
                   data[results.getRow()-1][col-1] = results.getString(col);
           }
           results.close();
           count.close();
           stmt.close();
           stmt2.close();
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return data;
        }
    }
    
    /*
     * args[0] = key
     * args[1] = value
     */
    public String[] get_by(String[] args) {
        
        String[] data = null;
        
        try {
           stmt = conn.createStatement();
           ResultSet results = stmt.executeQuery("SELECT * FROM " + this._tableName + " WHERE " + args[0] + " = '" + args[1] + "'");
           ResultSetMetaData rsmd = results.getMetaData();
           data = new String [rsmd.getColumnCount()];
           while(results.next()){
               
               for(int col = 1; col <= rsmd.getColumnCount(); col++)
                   data[col-1] = results.getString(col);
           }
           results.close();
           stmt.close();
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return data;
        }
    }
    
    /*
     * args[0] = key
     * args[1] = value
     */
    public String[][] get_many_by(String[] args) {
        
        String[][] data = null;
        
        try {
           stmt = conn.createStatement();
           stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_UPDATABLE);
           ResultSet results = stmt.executeQuery("SELECT * FROM " + this._tableName + " WHERE " + args[0] + " = '" + args[1] + "'");
           ResultSet count = stmt2.executeQuery("SELECT * FROM " + this._tableName + " WHERE " + args[0] + " = '" + args[1] + "'");
           count.last();
           ResultSetMetaData rsmd = results.getMetaData();
           data = new String [count.getRow()][rsmd.getColumnCount()];
           while(results.next()){
               
               for(int col = 1; col <= rsmd.getColumnCount(); col++)
                   data[results.getRow() - 1][col-1] = results.getString(col);
           }
           results.close();
           count.close();
           stmt.close();
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return data;
        }
    }
    
    public String[] get(String primary_value) {
        
        String[] args = new String[2]; 
        args[0] = this.primary_key;
        args[1] = primary_value;
        
        return this.get_by(args);
    }
    
    public String[][] get_many(String primary_value) {
        
        String[] args = new String[2];
        args[0] = this.primary_key;
        args[1] = primary_value;
        
        return this.get_many_by(args);
    }
    
    public void update(String primary_value, String keys[], String values[]){
       
        try {
            
            stmt = conn.createStatement();
            String sql = "UPDATE " + this._tableName + " SET ";
            
            for(int i = 0; i < keys.length ; i++) {
                sql += keys[i] + " = " + values[i];
                if(i!= keys.length - 1) 
                    sql += ", ";
            }
            
            sql += " WHERE " + this.primary_key + " = '" + primary_value + "'"; 
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
    }
    
    public void delete(String primary_value) {
        try {
            
            stmt = conn.createStatement();
            String sql = "DELETE FROM  " + this._tableName + " WHERE " + this.primary_key + " = '" + primary_value + "'";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
    }
}
