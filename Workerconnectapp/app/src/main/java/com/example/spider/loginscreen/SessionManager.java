package com.example.spider.loginscreen;

/**
 * Created by spider on 12/8/2016.
 */

import android.content.Context;


import android.content.Intent;
        import android.content.SharedPreferences;

/**

 */
public class SessionManager {
    Context _context;
    SharedPreferences _pref;
    SharedPreferences.Editor _edit;
    public static final String preFileName="PreFileName";
    public static final String Loginnamekey ="LoginKey";
    public static final String LoginPassKey="PassKey";
    public static final String isLogin="isLogin";
    public static final String Customerid = "Customerid";
    public static final String User_Type = "userid";

    public SessionManager(Context c)
    {
        _context=c;
        _pref=c.getSharedPreferences(preFileName,0);
        _edit= _pref.edit();
    }
    public void CreateUserSessionData(int Customer_id,int UserType, String loginname)
    {
        _edit.putInt(Customerid, Customer_id);

        _edit.putInt(User_Type, UserType);

        _edit.putString(Loginnamekey,loginname);
       // _edit.putString(LoginPassKey,Password);
        _edit.putBoolean(isLogin,true);
        _edit.commit();
    }
    public boolean checkLogin(){

        return _pref.getBoolean(Loginnamekey, false);
    }


    public int getLoginUSerid(){

        return _pref.getInt(Customerid, 0);
    }

    /*{
        boolean checkuserlogin=_pref.getBoolean(isLogin,false);

        if(checkuserlogin)
        {
            return true;
        }
        else{
            return false;
        }
    }*/
    public void logoutuser()
    {
        _edit.clear();
        _edit.commit();

        Intent loginIntent = new Intent(_context, MainActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(loginIntent);
    }

}
