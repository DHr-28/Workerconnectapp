using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace webapidemo.Models
{
    public class WorkerModel
    {
    
        public String Name { get; set; }
        public String City_id { get; set; }
        public String Email { get; set; }
     
        public String Address { get; set; }
        public String Ph_No { get; set; }
        public String Password { get; set; }

        public String Worker_Exprience { get; set; }

        public String Worker_Pinncode { get; set; }
        public String Category_id { get; set; }
        public String Basic_Rate { get; set; }
        public String Worker_id { get; set; }

    }
    public class Response
    {
        public String status { get; set; }
    }

    public class login
    {
        public String Email { get; set; }
        public String Password { get; set; }
    }
}