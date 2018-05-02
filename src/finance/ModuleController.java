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
public class ModuleController {
    
    BaseModel ModuleModel = new BaseModel("TBL_MODULE");
    
    public void createModule(String[] data) {
        
        ModuleModel.insert(data);
    }
    
    public void updateModule(String primary_value, String[] keys, String[] values) {
        
        ModuleModel.update(primary_value, keys, values);
    }
    
    public void getAllModule() {
        
        ModuleModel.get_all();
    }
    
    public void deleteModule(String primary_value) {
        
        ModuleModel.delete(primary_value);
    }
}
