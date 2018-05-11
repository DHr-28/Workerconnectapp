using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Http;
using System.Web.Mvc;
using webapidemo.Models;

namespace webapidemo.Controllers
{
    [System.Web.Http.RoutePrefix("webapi")]
    public class UserController : ApiController
    {
        
        [System.Web.Http.HttpPost]
        [System.Web.Http.Route("~/webapi/CustomerRegistration")]
        public IHttpActionResult Registration(UserModel usermodel)
        {

            try
            {

               Int64 insertresutl = dbaccess.Customer.Customer_DetailInsert(usermodel.Ph_no,
                   usermodel.Customer_Name, usermodel.Customer_Address,
                    usermodel.Email, usermodel.Password,2,Convert.ToInt32(usermodel.city_id));
                if (insertresutl >0)
                {
                    return Ok(new ResponseModel
                    {
                        status = "success reg"
                    });
                }
                else
                {
                    return Ok(new ResponseModel
                    {
                        status = "email exist"
                    });
                }

            }
            catch (Exception e)
            {

                return new ErrorResult("Error in request", HttpStatusCode.InternalServerError);
            }
        }


        [System.Web.Http.HttpPost]
        [System.Web.Http.Route("~/webapi/loginservice")]
        public IHttpActionResult loginservice(loginmode objloginmode)
        {

            try
            {

               System.Data.DataSet ds = dbaccess.Customer.logincheck(objloginmode.Email, objloginmode.Password);
                int rowcount = ds.Tables[0].Rows.Count;
                if (rowcount > 0)
                {
                    var lst = ds.Tables[0].AsEnumerable().Select(p => new
                    {
                        Customer_id = p.Field<int>("Customer_id"),
                        UserType = p.Field<int>("UserType"),
                        Email = p.Field<string>("Email")

                    }).ToList();
                    return Ok(new
                    {
                        lstdata = lst
                    });
                }
                else
                {
                    var lst = ds.Tables[0].AsEnumerable().Select(p => new
                    {
                        Customer_id = 0,
                        UserType = 0,
                        Email = ""

                    }).ToList();
                    return Ok(new
                    {
                        lstdata = lst
                    });
                }

            }
            catch (Exception e)
            {

                return new ErrorResult("Error in request", HttpStatusCode.InternalServerError);
            }
        }
    }
}