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
    
    SalVarModel salVarModel = new SalVarModel();
        
    public void createSalVal(String[] data) {
        
        salVarModel.insert(data);
    }
    
    public double[] getAllSalVal() {
        
        return salVarModel.get();
    }
}
