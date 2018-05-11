using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace webapidemo.Models
{
    public class UserModel
    {
        public String Ph_no { get; set; }
        public String Customer_Name { get; set; }
        public String Customer_Address { get; set; }
        public String Email { get; set; }
        public String Password { get; set; }
        public String city_id { get; set; }
        
    }
    public class ResponseModel
    {
        public String status { get; set; }
    }

    public class loginmode
    {
        public String Email { get; set; }
        public String Password { get; set; }
    }
    public class Forgot_Password
    {
        public String Email { get; set; }
       

    }
}