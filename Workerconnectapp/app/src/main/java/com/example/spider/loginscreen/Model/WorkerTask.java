package com.example.spider.loginscreen.Model;

/**
 * Created by spider on 2/19/2017.
 */
public class WorkerTask {
    public String Description;
    public int WorkerId;
    public int Customer_id;
    public String Status;
    public String Customer_Name;
    public String Ph_No;
    public String Date;
    public int Worker_Task_Id;


    public WorkerTask(String  description, int workerId,int customer_id, String status, String customer_Name, String ph_No
            , String date,int worker_Task_Id) {
        Description = description;
        WorkerId = workerId;
        Customer_id = customer_id;
        Status = status;
        Customer_Name=customer_Name;
        Ph_No=ph_No;
        Date=date;
        Worker_Task_Id=worker_Task_Id;
    }
}
