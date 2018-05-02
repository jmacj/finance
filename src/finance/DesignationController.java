/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finance;

/**
 *
 * @author Jan
 */
public class DesignationController {
    
    BaseModel designationModel = new BaseModel("TBL_DESIGNATION");
    
    public void createDesignation(String data[]) {
     
        designationModel.insert(data);
    }
    
    public void updateDesignation(String primary_value, String[] keys, String[] values){
        
        designationModel.update(primary_value, keys, values);
    }
    
    public String[][] getAllDesignation(){
        
        return designationModel.get_all();
    }
    
    public void deleteDesignation(String primary_value) {
        
        designationModel.delete(primary_value);
    }
    
    public String[] getDesignationBy(String[] args) {
        
        return designationModel.get_by(args);
    }
}
