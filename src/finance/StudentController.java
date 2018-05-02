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
public class StudentController extends PersonController{
    
    BaseModel studentModel = new BaseModel("TBL_STUDENT");
    
    
    public void createStudent(String[] data) {
               
        String[] dataStudent = new String[1];
        dataStudent[0] = this.createPerson(data);
        
        studentModel.insert(dataStudent);   
    }
    
    public void getStudent(String primary_value) {
        
        studentModel.get(primary_value);
    }
    
    public void updateStudent(String primary_value, String[] keys, String[] values) {
        
        studentModel.update(primary_value, keys, values);
    }
    
    public void deleteStudent(String primary_value) {
        
        studentModel.delete(primary_value);
    }
}
