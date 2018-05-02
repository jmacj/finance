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
public class TeacherController extends PersonController{
    
    BaseModel teacherModel = new BaseModel("TBL_TEACHER");
    
    /*
     * data[0] first_name
     *     [1] last_name
     *     [2] gender
     *     [3] phone_number
     *     [4] address
     *     [5] designation_id
     *
     */
    public void createTeacher(String[] data) {
        
        String dataPerson[] = new String[6];
        for(int i = 0; i < 5; i++) {
            dataPerson[i] = data[i];
        }
        String dataTeacher[] = new String[3];
        dataTeacher[0] = this.createPerson(dataPerson);
        dataTeacher[1] = data[5];
        dataTeacher[2] = "0"; // hours_rendered
        
        teacherModel.insert(dataTeacher);
    }
    
    public void updateTeacher(String primary_value, String[] keys, String[] values) {
        
        teacherModel.update(primary_value, keys, values);
    }
    
    public void getAllTeacher() {
        
        teacherModel.get_all();
    }
    
    public void deleteTeacher(String primary_value) {
        
        teacherModel.delete(primary_value);
    }
}
