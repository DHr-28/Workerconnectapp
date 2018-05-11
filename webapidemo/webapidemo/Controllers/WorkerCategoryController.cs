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
    public class WorkerCategoryController : ApiController
    {
        [System.Web.Http.HttpGet]
        [System.Web.Http.Route("~/WorkerCategorySelectAll")]
        public IHttpActionResult WorkerCategorySelectAll()
        {
            try
            {

                System.Data.DataSet ds = dbaccess.WorkerCategory.WorkerCategorySelectAll();
                int rowcount = ds.Tables[0].Rows.Count;
                var lst = ds.Tables[0].AsEnumerable().Select(p => new
                {
                    Id = p.Field<int>("Id"),
                    Worker_Category = p.Field<string>("Worker_Category"),
                    Worker_Description = p.Field<string>("Worker_Description"),
                    Worker_Image = p.Field<string>("Worker_Image"),
                
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