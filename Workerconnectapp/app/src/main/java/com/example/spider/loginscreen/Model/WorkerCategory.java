package com.example.spider.loginscreen.Model;

/**
 * Created by spider on 12/10/2016.
 */
public class WorkerCategory {
    public int Id;
    public String Worker_Category;
    public String Worker_Description;
    public String Worker_Image;


    public WorkerCategory(String  worker_Category, int id, String worker_Description, String worker_Image) {
        Worker_Category = worker_Category;
        Id = id;
        Worker_Description = worker_Description;
        Worker_Image = worker_Image;
    }

}
