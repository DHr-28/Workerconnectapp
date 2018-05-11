using dbaccess;
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
    public class WorkerProfileCheckController : ApiController
{
    [System.Web.Http.HttpGet]
    [System.Web.Http.Route("~/WorkerProfileCheck")]
    public IHttpActionResult WorkerProfileCheck(int Worker_id = 0)
    {
        try
        {

            System.Data.DataSet ds = dbaccess.WorkerProfileCheck.Worker_Profile_ByWorkerId(Worker_id);
            int rowcount = ds.Tables[0].Rows.Count;
                var lst = ds.Tables[0].AsEnumerable().Select(p => new WorkerProfileByWokerId
                {
                   
                    Email = p.Field<string>("Email"),
                    Ph_No = p.Field<string>("ph_No"),
                    Password = p.Field<string>("Password"),
                    Worker_Exprience = p.Field<string>("Worker_Exprience"),
                    Basic_Rate = p.Field<string>("Basic_Rate"),
                    Customer_Address = p.Field<string>("Customer_Address")
            }).ToList();
            return Ok(new
            {
                lstdata = lst
            });
        }
        catch (Exception e)
        {

                throw e;
        }
    }


        [System.Web.Http.HttpPost]
        [System.Web.Http.Route("~/WorkerProfileCheck")]
        public IHttpActionResult WorkerProfileCheck(WorkerProfileByWokerId usermodel)
        {

            try
            {

                dbaccess.WorkerProfileCheck.Worker_Profile_Update_ByWorkerId(Convert.ToInt32(usermodel.Worker_id), usermodel.Customer_Address,
                    usermodel.Ph_No, usermodel.Worker_Exprience,
                     usermodel.Email, usermodel.Password);
                
                    return Ok(new ResponseModel
                    {
                        status = "success reg"
                    });
               

            }
            catch (Exception e)
            {

                return new ErrorResult("Error in request", HttpStatusCode.InternalServerError);
            }
        }
    }
}