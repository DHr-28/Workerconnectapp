package com.example.spider.loginscreen.Model;

/**
 * Created by spider on 3/26/2017.
 */
public class UpdateWorker {
    public int Worker_id;
    public String Ph_No;
    public String Worker_Exprience;
    public String Email;
    public String Password;
    public String Basic_Rate;
    public String Customer_Address;



    public UpdateWorker(int worker_id, String ph_No, String worker_Exprience, String email, String password,String basic_Rate,String customer_Address) {
        Worker_id = worker_id;
        Ph_No = ph_No;
        Worker_Exprience = worker_Exprience;
        Email = email;
        Password = password;
        Basic_Rate = basic_Rate;
        Customer_Address = customer_Address;


    }
}
