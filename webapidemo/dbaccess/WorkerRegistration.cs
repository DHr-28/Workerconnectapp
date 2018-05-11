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
    public class WorkerRegistration
    {
        //public static DataSet logincheck(String Email, String Password)
        //{
        //    Database db = null;
        //    DbCommand dbCommand = null;
        //    try
        //    {
        //        db = DatabaseFactory.CreateDatabase();
        //        dbCommand = db.GetStoredProcCommand("WorkerLoginCheck");
        //        db.AddInParameter(dbCommand, "@Email", DbType.String, Email);
        //        db.AddInParameter(dbCommand, "@Password", DbType.String, Password);
        //        DataSet dsCompanyInfo = (DataSet)db.ExecuteDataSet(dbCommand);
        //        return dsCompanyInfo;
        //    }
        //    catch (Exception)
        //    {
        //        throw;
        //    }
        //    finally
        //    {
        //        if (dbCommand != null)
        //        {
        //            dbCommand.Dispose();
        //            dbCommand = null;
        //        }
        //        if (db != null)
        //            db = null;
        //    }
        //}
        public static int Worker_DetailInsert(int Worker_id,string Worker_Exprience,
            string Worker_Pinncode,string Basic_Rate,int Category_id)
        {
            Database db = null;
            DbCommand dbCommand = null;
            try
            {
                db = DatabaseFactory.CreateDatabase();
                dbCommand = db.GetStoredProcCommand("Worker_DetailInsert");
           
                db.AddInParameter(dbCommand, "@Worker_id", DbType.Int32, Worker_id);
                db.AddInParameter(dbCommand, "@Worker_Exprience", DbType.String, Worker_Exprience);
                db.AddInParameter(dbCommand, "@Worker_Pinncode", DbType.String, Worker_Pinncode);
                db.AddInParameter(dbCommand, "@Basic_Rate", DbType.String, Basic_Rate);
                db.AddInParameter(dbCommand, "@Category_id", DbType.String, Category_id);
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
