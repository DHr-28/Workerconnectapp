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
   public class WorkerProfileCheck
    {
        public static DataSet Worker_Profile_ByWorkerId(int Worker_id)
        {
            Database db = null;
            DbCommand dbCommand = null;
            try
            {
                db = DatabaseFactory.CreateDatabase();
                dbCommand = db.GetStoredProcCommand("Worker_Profile_ByWorkerId");
                db.AddInParameter(dbCommand, "@Worker_id", DbType.Int32, Worker_id);

                return db.ExecuteDataSet(dbCommand);
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

        public static void Worker_Profile_Update_ByWorkerId(int Worker_id , String Customer_Address,String ph_No,String  Worker_Exprience,
            String Email, String Password)
        {
            Database db = null;
            DbCommand dbCommand = null;
            try
            {
                db = DatabaseFactory.CreateDatabase();
                dbCommand = db.GetStoredProcCommand("Worker_Profile_Update_ByWorkerId");
                db.AddInParameter(dbCommand, "@Worker_id", DbType.Int32, Worker_id);
                db.AddInParameter(dbCommand, "@Customer_Address", DbType.String, Customer_Address);
                db.AddInParameter(dbCommand, "@ph_No", DbType.String, ph_No);
                db.AddInParameter(dbCommand, "@Worker_Exprience", DbType.String, Worker_Exprience);
                db.AddInParameter(dbCommand, "@Email", DbType.String, Email);
                db.AddInParameter(dbCommand, "@Password", DbType.String, Password);
               db.ExecuteNonQuery(dbCommand);
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
