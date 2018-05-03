/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finance;

import org.apache.commons.lang3.RandomStringUtils;


/**
 *
 * @author Jan
 */
public class PersonController {
    
    BaseModel personModel = new BaseModel("TBL_PERSON");
    
    public PersonController() {
        
        personModel = new BaseModel("TBL_PERSON");
    }
    
    public String createPerson(String[] data) {
        String[] personData = new String[6];
        for(int i = 0; i < 6; i++) {
            if(i == 0)
                personData[i] = "'" + RandomStringUtils.randomAlphanumeric(11) + "'";
            else
                personData[i] = "'" + data[i-1] + "'";
        }
        personModel.insert(personData);
        
        return personData[0];
    }
    
    public void updatePerson(String id, String[] keys, String[] values) {
        
        
        personModel.update(id, keys, values);
    }
    
    public String[] getPerson(String id) {
        
        return personModel.get(id);
    }
    
    public void deletePerson(String id) {
        
        personModel.delete(id);
    }
}
