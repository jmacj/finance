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
public class DepartmentController {
    
    BaseModel departmentModel = new BaseModel("TBL_DEPARTMENT");
    
    public void createDepartment(String[] data) {
        
        departmentModel.insert(data);
    }
    
    public void updateDepartment(String primary_value, String[] keys, String[] values) {
        
        departmentModel.update(primary_value, values, values);
    }
    
    public void getAllDepartment() {
        
        departmentModel.get_all();
    }
    
    public void deleteDepartment(String primary_value) {
        
        departmentModel.delete(primary_value);
    }
}
