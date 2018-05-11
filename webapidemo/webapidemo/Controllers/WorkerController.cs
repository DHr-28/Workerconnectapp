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
    public class WorkerController : ApiController
    {

        [System.Web.Http.HttpPost]
        [System.Web.Http.Route("~/WorkerRegistration")]
        public IHttpActionResult Registration(WorkerModel Workermodel)
        {

            try
            {

                Int64 insertresutl = dbaccess.Customer.Customer_DetailInsert(Workermodel.Ph_No,
                                 Workermodel.Name, Workermodel.Address,
                                  Workermodel.Email, Workermodel.Password, 1, Convert.ToInt32(Workermodel.City_id));

                if (insertresutl > 0)
                {
                    int insertresult2 = dbaccess.WorkerRegistration.Worker_DetailInsert(Convert.ToInt32(insertresutl),
                        Workermodel.Worker_Exprience,
                    Workermodel.Worker_Pinncode, Workermodel.Basic_Rate, Convert.ToInt32(Workermodel.Category_id));

                    return Ok("success worker reg");
                }
                else
                {
                    return new ErrorResult("Email Already registered");
                }

            }
            catch (Exception e)
            {

                return new ErrorResult("Error in request", HttpStatusCode.InternalServerError);
            }
        }


        //[System.Web.Http.HttpPost]
        //[System.Web.Http.Route("~/loginservice")]
        //public IHttpActionResult loginservice(login objloginmode)
        //{

        //    try
        //    {

        //        System.Data.DataSet ds = dbaccess.WorkerRegistration.logincheck(objloginmode.Email, objloginmode.Password);
        //        int rowcount = ds.Tables[0].Rows.Count;
        //        if (rowcount > 0)
        //        {


        //            var lst = ds.Tables[0].AsEnumerable().Select(p => new
        //            {
        //                Customer_id = p.Field<int>("Customer_id"),
        //                UserType = p.Field<int>("UserType"),
        //                Email = p.Field<string>("Email")
                        
        //            }).ToList();
        //            return Ok(new
        //            {
        //                lstdata = lst
        //            });
        //        }
        //        else
        //        {
        //            var lst = ds.Tables[0].AsEnumerable().Select(p => new
        //            {
        //                Customer_id =0,
        //                UserType = 0,
        //                Email = ""

        //            }).ToList();
        //            return Ok(new
        //            {
        //                lstdata = lst
        //            });
        //        }

        //    }
        //    catch (Exception e)
        //    {

        //        return new ErrorResult("Error in request", HttpStatusCode.InternalServerError);
        //    }
        //}

        [System.Web.Http.HttpPost]
        [System.Web.Http.Route("~/Worker_TaskInsert")]
        public IHttpActionResult Worker_TaskInsert(WorkerTask workertask)
        {

            try
            {

                int a = dbaccess.Work_Task.Worker_TaskInsert(Convert.ToInt32(workertask.Customer_id),Convert.ToInt32(workertask.WorkerId),
                    workertask.Description
                    , workertask.Status);


                return Ok(new Response
                {
                    status = "success"
                });

            }
            catch (Exception e)
            {

                return new ErrorResult(e.Message, HttpStatusCode.InternalServerError);
            }
        }


    }
}