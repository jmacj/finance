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
public class ModuleAssessmentController {
 
    BaseModel MAModel = new BaseModel("TBL_MODULE_ASSESSMENT");
    
    public void createMA(String[] data) {
    
        MAModel.insert(data);
    }
    
    public void updateMA(String primary_value, String[] keys, String[] values) {
        
        MAModel.update(primary_value, keys, values);
    }
    
    public void getMABy(String[] args) {
        
        MAModel.get_by(args);
    }
}
