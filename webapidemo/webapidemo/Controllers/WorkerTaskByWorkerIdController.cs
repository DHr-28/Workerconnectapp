using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using webapidemo.Models;

namespace webapidemo.Controllers
{
    public class WorkerTaskByWorkerIdController : ApiController
    {
        [System.Web.Http.HttpGet]
        [System.Web.Http.Route("~/Worker_Task_ByWorkerId_ser")]
        public IHttpActionResult Worker_Task_ByWorkerId_ser(int  WorkerId=0)
        {

            try
            {

                System.Data.DataSet ds = dbaccess.WorkerTaskByWorkerId.Worker_TaskBy_WorkerId(WorkerId);
                int rowcount = ds.Tables[0].Rows.Count;
                var lst = ds.Tables[0].AsEnumerable().Select(p => new Worker_Task_ByWorkerIdModel
                {
                    Worker_Task_Id=p.Field<int>("Worker_Task_Id"),
                    WorkerId = p.Field<int>("WorkerId"),
                    Customer_id = p.Field<int>("Customer_id"),
                    Description = p.Field<string>("Description"),
                    Status = p.Field<string>("Status"),
                    Customer_Name = p.Field<string>("Customer_Name"),
                    Ph_No = p.Field<string>("Ph_No"),
                    Date = p.Field<string>("Date"),
                 

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

        [System.Web.Http.HttpGet]
        [System.Web.Http.Route("~/Worker_Task_Status")]
        public IHttpActionResult Worker_Task_Status(int Worker_Task_Id , string Statusmsg)
        {
            try
            {

               int ds = dbaccess.Work_Task.Worker_TaskStatus(Worker_Task_Id, Statusmsg);

                return Ok(new ResponseModel
                {
                    status = "success"
                });
            }
            catch (Exception e)
            {

                return new ErrorResult("Error in request", HttpStatusCode.InternalServerError);
            }
        }


    }

}