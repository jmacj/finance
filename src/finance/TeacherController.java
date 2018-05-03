/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finance;

import java.util.Arrays;

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
        designation = dc.getDesignationBy(new String[] {"NAME", data[5]});
        
        DepartmentController depC = new DepartmentController();
        department = depC.getDepartmentBy(new String[] {"NAME", data[6]});
        
        dataTeacher[1] = "'" + designation[0] + "'";
        dataTeacher[2] = "'" + department[0] +  "'";
        int hours_rendered = 0;
        
        teacherModel.insert(dataTeacher, hours_rendered);
    }
    
    public void updateTeacher(String[] data) {
        
        DesignationController dc = new DesignationController();
        String[] designation, department;
        designation = dc.getDesignationBy(new String[] {"NAME", data[6]});
        
        DepartmentController depC = new DepartmentController();
        department = depC.getDepartmentBy(new String[] {"NAME", data[7]});
        
        String[] keysTeacher = new String[] {"DESIGNATION_ID", "DEPARTMENT_ID"},
                 valTeacher = new String[] {"'" + designation[0] + "'", "'" + department[0] + "'"};
        
        teacherModel.__construct(teacherModel._tableName);
        teacherModel.update(data[0], keysTeacher, valTeacher);
        
        for(int i = 1; i < 6; i++)
            data[i] = "'" + data[i] + "'";
        PersonController pc = new PersonController();
        String[] keysPerson = new String[] { "FIRST_NAME", "LAST_NAME", "GENDER", "PHONE_NUMBER", "ADDRESS" },
                 valPerson = new String[] { data[1], data[2], data[3], data[4], data[5]};
        
        pc.updatePerson(data[0], keysPerson, valPerson);
        
    }
    /*
     * [0] id             [6] designation     [9] ot_hrs
     * [1] first_name     [7] department      [10] base_sal
     * [2] last_name      [8] hrs_rendered    [11] ot_pay
     * [3] gender                             [12] total_sal
     * [4] phone_number                       [13] ha
     * [5] address                            [14] ma
     *                                        [15] ta
     *                                        [16] net_sal
    */
    public String[] getTeacher(String primary_value) {
        
        String[] data = new String[17], person = new String[5], teacher = new String[4], salary = new String[8];
        
        teacher = teacherModel.get(primary_value);
     
        PersonController pc = new PersonController();
        person = pc.getPerson(primary_value);
        
        salary = this.getSalary(teacher[1], teacher[3]);
        for (int i = 0; i < data.length; i++){
            if(i < 6)
                data[i] = person[i];
            if(i >= 6 && i < 9)
                data[i] = teacher[i-5];
            if(i >= 9)
                data[i] = salary[i-9];
        }
        
        BaseModel dm = new BaseModel("TBL_DESIGNATION");
        data[6] = dm.get(data[6])[1];
        BaseModel de = new BaseModel("TBL_DEPARTMENT");
        data[7] = de.get(data[7])[1];
        
        return data;
        
        
    }
    
    public String[][] getAllTeacher() {

        String[][] teacher, data;
        teacher = teacherModel.get_all();
        data = new String[teacher.length][9];
        
        for(int i = 0; i < teacher.length; i++) {
            PersonController pc = new PersonController();
            String[] temp = pc.getPerson(teacher[i][0]);
            
            for(int j = 0; j < temp.length; j++) {          
                data[i][j] = temp[j];
            }
            BaseModel dm = new BaseModel("TBL_DESIGNATION");
            data[i][temp.length] = dm.get(teacher[i][1])[1];
            BaseModel de = new BaseModel("TBL_DEPARTMENT");        
            data[i][temp.length+1] = de.get(teacher[i][2])[1];
            data[i][temp.length+2] = teacher[i][3];
        }
        
        return data;
    }
    
    public void updateHour(String id, String hrs) {
        teacherModel = new TeacherModel();
        teacherModel.update(id, new String[] {"HOURS_RENDERED"}, new String[] {hrs});
    }
    
    public String[][] getManyTeacherBy(String key) {
        String[][] teacher, data;
        BaseModel dp = new BaseModel("TBL_DEPARTMENT");
        String arg = dp.get_by(new String[] { "NAME", key})[0];
        teacherModel = new TeacherModel();
        teacher = teacherModel.get_many_by(new String[] {"DEPARTMENT_ID", arg});
        data = new String[teacher.length][9];
        
        for(int i = 0; i < teacher.length; i++) {
            PersonController pc = new PersonController();
            String[] temp = pc.getPerson(teacher[i][0]);
            
            for(int j = 0; j < temp.length; j++) {          
                data[i][j] = temp[j];
            }
            BaseModel dm = new BaseModel("TBL_DESIGNATION");
            data[i][temp.length] = dm.get(teacher[i][1])[1];
            BaseModel de = new BaseModel("TBL_DEPARTMENT");        
            data[i][temp.length+1] = de.get(teacher[i][2])[1];
            data[i][temp.length+2] = teacher[i][3];
        }
        
        return data;
    }
    
    public void deleteTeacher(String primary_value) {
        
        teacherModel.delete(primary_value);
    }
    
    /*
     * data[0] = OT_HRS               
     * data[1] = BASE_SALARY           var[0] = base_sal
     * data[2] = OT_PAY                var[1] = ot_rate
     * data[3] = TOTAL_SALARY
     * data[4] = HOUSING_ALLOWANCE     var[2] = ha_rate
     * data[5] = MEDICAL_ALLOWANCE     var[3] = ma_rate
     * data[6] = TRAVEL_ALLOWANCE      var[4] = ta_rate
     * data[7] = NET_SALARY
     */
    public String[] getSalary(String designation, String hrs) {
        
       double[] data = new double[8];
       String[] strData = new String[8];
        
        int required_hrs = 0, rendered_hrs = Integer.parseInt(hrs);
        int ot_hrs;
        switch(designation){
            case "HEADOFFACUL": required_hrs = 8; break;
            case "COORDINATOR": required_hrs = 13; break;
            case "LECTURER123": required_hrs = 18; break;
        }
        
        ot_hrs = rendered_hrs - required_hrs;
        SalaryVariableController svc = new SalaryVariableController();
        
        double[] var = svc.getAllSalVal();
        if(rendered_hrs >= required_hrs){
            data[0] = rendered_hrs > required_hrs ? Double.parseDouble(Integer.toString(ot_hrs)) : 0.0;
            data[1] = var[0];
            data[2] = data[0] * var[1];
            data[3] = data[1] + data[2];
            data[4] = data[3] * var[2];
            data[5] = data[3] * var[3];
            data[6] = data[3] * var[4];
            data[7] = 0.0;
            for(int i = 3; i < 7; i++)
                data[7] += data[i];
        }
        strData[0] = Integer.toString(ot_hrs);
        for(int i = 1; i < strData.length; i++){
            
            strData[i] = Double.toString(data[i]);
        }
        return strData;
    }
}
