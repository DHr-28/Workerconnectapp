using webapidemo.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Http;
using System.Web.Mvc;

namespace webapidemo.Controllers
{
    public class WorkerDetailController : ApiController
    {
        [System.Web.Http.HttpGet]
        [System.Web.Http.Route("~/WorkerDetailSelectAll")]
        public IHttpActionResult WorkerDetailSelectAll(int categoryid=0)
        {
            try
            {
                
                System.Data.DataSet ds = dbaccess.Worker_Detail.Select_Workerby_CategoryId(categoryid);
                int rowcount = ds.Tables[0].Rows.Count;
                var lst = ds.Tables[0].AsEnumerable().Select(p => new Worker_Detail
                {
                    Worker_id = p.Field<int>("Worker_Detail_id"),
                    Worker_Address = p.Field<string>("Customer_Address"),
                    Worker_Name = p.Field<string>("Customer_Name"),
                    Worker_Ph_No = p.Field<string>("Worker_Pinncode"),
                    Worker_Exprience=p.Field<string>("Worker_Exprience"),
                    Worker_Pinncode = p.Field<string>("Worker_Pinncode"),
                    Basic_Rate = p.Field<string>("Basic_Rate"),
                    Worker_City = p.Field<string>("CityName")
                }).ToList();
                return Ok(new
                {
                    lstdata = lst
                });
            }
            catch (Exception e)
            {

                return new ErrorResult("Error in request", HttpStatusCode.InternalServerError);
            }
        }

        [System.Web.Http.Route("~/CitySelectAll")]
        [System.Web.Http.HttpGet]
        public IHttpActionResult CitySelectAll()
        {
            try
            {

                System.Data.DataSet ds = dbaccess.Worker_Detail.CitySelectAll();
                int rowcount = ds.Tables[0].Rows.Count;
                var lst = ds.Tables[0].AsEnumerable().Select(p => new
                {
                    Id = p.Field<int>("CityId"),
                    Worker_Category = p.Field<string>("CityName")
                }).ToList();
                return Ok(new
                {
                    lstdata = lst
                });
            }
            catch (Exception e)
            {

                return new ErrorResult("Error in request", HttpStatusCode.InternalServerError);
            }
        }

    }
}