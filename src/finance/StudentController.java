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
        PersonController pc = new PersonController();
        dataStudent[0] = pc.createPerson(data);
        
        studentModel = new BaseModel("TBL_STUDENT");
        studentModel.insert(dataStudent);   
    }
    
    public String[][] getAllStudent(){
        String[][] student, data;
        student = studentModel.get_all();
        data = new String[student.length][9];
        
        for(int i = 0; i < student.length; i++) {
            PersonController pc = new PersonController();
            String[] temp = pc.getPerson(student[i][0]);
            
            for(int j = 0; j < temp.length; j++) {          
                data[i][j] = temp[j];
            }
        }
        
        return data;
    }
    
    public String[] getStudent(String primary_value) {
        PersonController pc = new PersonController();
        return pc.getPerson(primary_value);
//        studentModel.get(primary_value);
    }
    
    public void updateStudent(String primary_value, String[] keys, String[] values) {
        
        studentModel.update(primary_value, keys, values);
    }
    
    public void deleteStudent(String primary_value) {
        
        studentModel.delete(primary_value);
    }
}
