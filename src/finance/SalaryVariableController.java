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
public class SalaryVariableController {
    
    BaseModel SalValModel = new BaseModel("TBL_SALARY_VARIABLE");
    
    public void createSalVal(String[] data) {
        
        SalValModel.insert(data);
    }
    
    public void getAllSalVal() {
        
        SalValModel.get_all();
    }
}
