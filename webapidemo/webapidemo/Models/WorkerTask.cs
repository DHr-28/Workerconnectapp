using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace webapidemo.Models
{
    public class WorkerTask
    {
        public String Customer_id { get; set; }

        public String WorkerId { get; set; }

        public String Description { get; set; }
        
        public String Status { get; set; }
    }
}