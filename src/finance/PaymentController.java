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
public class PaymentController {
    
    BaseModel paymentModel = new BaseModel("TBL_PAYMENT_HISTORY");
    
    public void createPayment(String[] data) {
        
        paymentModel.insert(data);
    }
        
    public void getPaymentBy(String[] args) {
        
        paymentModel.get_by(args);
    }
    
    public void getAllPayment() {
        
        paymentModel.get_all();
    }
}
