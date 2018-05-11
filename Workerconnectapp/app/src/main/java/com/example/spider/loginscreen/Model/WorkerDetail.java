package com.example.spider.loginscreen.Model;

/**
 * Created by spider on 12/10/2016.
 */
public class WorkerDetail {
    public int Worker_id;
    public String Worker_Name;
    public String Worker_Address;
    public String Worker_City;
    public int Worker_Category;
    public String Worker_Ph_No;
    public String Worker_Exprience;
    public String Worker_Pinncode;
    public String Basic_Rate;

    public WorkerDetail(int worker_id, String worker_Name, String worker_Address,
                        String worker_City, int worker_Category, String worker_Ph_No,
                        String worker_Exprience, String worker_Pinncode, String basic_Rate) {
        Worker_id = worker_id;
        Worker_Name = worker_Name;
        Worker_Address = worker_Address;
        Worker_City = worker_City;
        Worker_Category = worker_Category;
        Worker_Ph_No = worker_Ph_No;
        Worker_Exprience = worker_Exprience;
        Worker_Pinncode = worker_Pinncode;
        Basic_Rate = basic_Rate;
    }
}
