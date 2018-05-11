using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace webapidemo.Models
{
    public class Worker_Task_ByWorkerIdModel
    {
        public int WorkerId { get; set; }
        public int Customer_id { get; set; }
        public String Description { get; set; }
        public String Status { get; set; }
        public String Customer_Name { get; set; }
        public String Ph_No { get; set; }
        public String Date  { get; set; }
        public int Worker_Task_Id { get; set; }

    }
}