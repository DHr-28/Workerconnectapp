using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace webapidemo.Models
{
    public class Worker_Detail
    {
        public int Worker_id { get; set; }
        public String Worker_Name { get; set; }
        public String Worker_Address { get; set; }
        public String Worker_City { get; set; }

        public String Worker_Ph_No { get; set; }
        public String Worker_Exprience { get; set; }
        public String Worker_Pinncode { get; set; }
        public String Basic_Rate { get; set; }
    }
}