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
public class DDMappingController {
    
    BaseModel ddMappingModel = new BaseModel("TBL_DD_MAPPING");
    
    public void createMapping(String[] data) {
        
        ddMappingModel.insert(data);
    }
    
    public void getManyMappingBy(String[] args){
        
        ddMappingModel.get_many_by(args);
    }
}
