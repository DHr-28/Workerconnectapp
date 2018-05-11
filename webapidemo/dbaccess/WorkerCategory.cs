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
 public   class WorkerCategory
    {
        public static DataSet WorkerCategorySelectAll()
        {
            Database db = null;
            DbCommand dbCommand = null;
            try
            {
                db = DatabaseFactory.CreateDatabase();
                dbCommand = db.GetStoredProcCommand("Worker_Category_selectall");
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

        public static int WorkerCategoryInsert(string Worker_Category, string Worker_Image)
        {
            Database db = null;
            DbCommand dbCommand = null;
            try
            {
                db = DatabaseFactory.CreateDatabase();
                dbCommand = db.GetStoredProcCommand("WorkerCategoryInsert");
                db.AddInParameter(dbCommand, "@Worker_Category", DbType.String, Worker_Category);
                db.AddInParameter(dbCommand, "@Worker_Image", DbType.String, Worker_Image);
                // Execute the query and return the new identity value
                int returnValue = Convert.ToInt32(db.ExecuteScalar(dbCommand));
                return returnValue;
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

        public static int WorkerCategoryUpdate(int Id, string Worker_Category, string Worker_Description,string Worker_Image)
        {
            Database db = null;
            DbCommand dbCommand = null;
            try
            {
                db = DatabaseFactory.CreateDatabase();
                dbCommand = db.GetStoredProcCommand("WorkerCategoryUpdate");
                db.AddInParameter(dbCommand, "@Id", DbType.Int32, Id);
                db.AddInParameter(dbCommand, "@Worker_Category", DbType.String, Worker_Category);
                db.AddInParameter(dbCommand, "@Worker_Description", DbType.String, Worker_Description);
                db.AddInParameter(dbCommand, "@Worker_Image", DbType.String, Worker_Image);
                // Execute the query and return the new identity value
                int returnValue = Convert.ToInt32(db.ExecuteScalar(dbCommand));
                return returnValue;
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

        public static int WorkerCategoryDelete(int Id)
        {
            Database db = null;
            DbCommand dbCommand = null;
            try
            {
                db = DatabaseFactory.CreateDatabase();
                dbCommand = db.GetStoredProcCommand("WorkerCategoryDelete");
                db.AddInParameter(dbCommand, "@Id", DbType.Int32, Id);
                // Execute the query and return the new identity value
                int returnValue = Convert.ToInt32(db.ExecuteScalar(dbCommand));
                return returnValue;
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

        public static DataSet WorkerCategorySelectById(int Id)
        {
            Database db = null;
            DbCommand dbCommand = null;
            try
            {
                db = DatabaseFactory.CreateDatabase();
                dbCommand = db.GetStoredProcCommand("WorkerCategorySelectById");
                db.AddInParameter(dbCommand, "@Id", DbType.Int32, Id);
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
    }
}
