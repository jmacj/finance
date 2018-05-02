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
public class StudentAssessmentController {
    
    BaseModel SAModel = new BaseModel("TBL_STUDENT_ASSESSMENT");
    
    public void createSA(String[] data){
        
        SAModel.insert(data);
    }
    
    public void updateSA(String primary_value, String[] keys, String[] values) {
        
        SAModel.update(primary_value, keys, values);
    }
    
    public void getSA(String primary_value) {
        
        SAModel.get(primary_value);
    }
}
