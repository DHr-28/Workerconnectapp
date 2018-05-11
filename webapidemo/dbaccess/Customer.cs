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
   public class Customer
    {
        public static DataSet logincheck( String Email,String Password)
        {
            Database db = null;
            DbCommand dbCommand = null;
            try
            {
                db = DatabaseFactory.CreateDatabase();
                dbCommand = db.GetStoredProcCommand("CustomerLoginCheck");
                db.AddInParameter(dbCommand, "@Email", DbType.String, Email);
                db.AddInParameter(dbCommand, "@Password", DbType.String, Password);
                DataSet dsCompanyInfo = (DataSet)db.ExecuteDataSet(dbCommand);
                return dsCompanyInfo;
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
        public static Int64 Customer_DetailInsert(String Ph_No,
       String Customer_Name, String Customer_Address, String Email,
         String Password,int UserType,int City_id)
        {
            Database db = null;
            DbCommand dbCommand = null;
            try
            {
                db = DatabaseFactory.CreateDatabase();
                dbCommand = db.GetStoredProcCommand("Customer_DetailInsert");
                db.AddInParameter(dbCommand, "@Ph_No", DbType.String, Ph_No);
                db.AddInParameter(dbCommand, "@Customer_Name", DbType.String, Customer_Name);
                db.AddInParameter(dbCommand, "@Customer_Address", DbType.String, Customer_Address);
                db.AddInParameter(dbCommand, "@Email", DbType.String, Email);
                db.AddInParameter(dbCommand, "@Password", DbType.String, Password);
                db.AddInParameter(dbCommand, "@UserType", DbType.Int32, UserType);
                db.AddInParameter(dbCommand, "@City_id", DbType.Int32, City_id);
                Int64 sqlresult =Convert.ToInt64(db.ExecuteScalar(dbCommand));
                return sqlresult;
            }
            catch (Exception e)
            {
                throw e;
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
