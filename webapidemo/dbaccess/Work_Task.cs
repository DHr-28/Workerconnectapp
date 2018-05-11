using Microsoft.Practices.EnterpriseLibrary.Data;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace dbaccess
{
  public  class Work_Task
    {
        public static int Worker_TaskInsert(int Customer_id,int WorkerId ,string Description,
           string Status)
        {
            Database db = null;
            DbCommand dbCommand = null;
            try
            {
                db = DatabaseFactory.CreateDatabase();
                dbCommand = db.GetStoredProcCommand("Worker_TaskInsert");

                db.AddInParameter(dbCommand, "@Customer_id", DbType.Int32, Customer_id);
                db.AddInParameter(dbCommand, "@WorkerId", DbType.Int32, WorkerId);
                db.AddInParameter(dbCommand, "@Description", DbType.String, Description);
                db.AddInParameter(dbCommand, "@Status", DbType.String, Status);

                int sqlresult = (int)db.ExecuteScalar(dbCommand);
                return sqlresult;
            }
            catch (Exception)
            {
                throw;
            }
            finally
            {
                if (dbCommand != null)
                {
                    dbCommand.Dispose();
                    dbCommand = null;
                }
                if (db != null)
                    db = null;
            }

        }

        public static int Worker_TaskStatus(int Worker_Task_Id,String Status)
        {
            Database db = null;
            DbCommand dbCommand = null;
            try
            {
                db = DatabaseFactory.CreateDatabase();
                dbCommand = db.GetStoredProcCommand("Worker_TaskStatus");

                db.AddInParameter(dbCommand, "@Worker_Task_Id", DbType.Int32, Worker_Task_Id);
                db.AddInParameter(dbCommand, "@Status", DbType.String, Status);

                int sqlresult = (int)db.ExecuteScalar(dbCommand);
                return sqlresult;
            }
            catch (Exception)
            {
                throw;
            }
            finally
            {
                if (dbCommand != null)
                {
                    dbCommand.Dispose();
                    dbCommand = null;
                }
                if (db != null)
                    db = null;
            }

        }


    }
}
