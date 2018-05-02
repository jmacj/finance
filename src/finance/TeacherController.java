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
    
    TeacherModel teacherModel = new TeacherModel();
    
    /*
     * data[0] first_name
     *     [1] last_name
     *     [2] gender
     *     [3] phone_number
     *     [4] address
     *     [5] designation_id
     *     [6] department_id
     */
    public void createTeacher(String[] data) {
        
        PersonController pc = new PersonController();
        String dataPerson[] = new String[7];
        for(int i = 0; i < 5; i++) {
            dataPerson[i] = data[i];
        }
        String dataTeacher[] = new String[3];
        dataTeacher[0] = pc.createPerson(dataPerson);
        
        
        DesignationController dc = new DesignationController();
        String[] designation, department;
        designation = dc.getDesignationBy(new String[] {"name", data[5]});
        
        DepartmentController depC = new DepartmentController();
        department = depC.getDepartmentBy(new String[] {"name", data[6]});
        
        dataTeacher[1] = "'" + designation[0] + "'";
        dataTeacher[2] = "'" + department[0] +  "'";
        int hours_rendered = 0;
        
        teacherModel.insert(dataTeacher, hours_rendered);
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
