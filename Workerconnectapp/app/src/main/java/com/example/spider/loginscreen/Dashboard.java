package com.example.spider.loginscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spider.loginscreen.Model.WorkerTask;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView cust_name,cust_email;
    Fragment subfragment;

    SessionManager objUserSessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        objUserSessionManager = new SessionManager(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View child = navigationView.getHeaderView(0);

        cust_name=(TextView)child.findViewById(R.id.name);
        cust_email=(TextView)child.findViewById(R.id.name);
        String email = getIntent().getStringExtra("email");
        cust_email.setText(email);

        gotoHomepage();

    }

    private void gotoHomepage() {
        subfragment = new WorkerCategory_Fragment();// homePage();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentFrame, subfragment);
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("");
    }

    private void gotoWorkerTask() {
        subfragment = new WorkerTaskList_Fragment();// homePage();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentFrame, subfragment);
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("");
    }

    public void gotoWorlerList(Integer... workcategoryid) {

        subfragment = new workerList_fragment();// homePage();
        if(workcategoryid.length == 0)
        {
            //direct clik from left slider meniu

        }
        else
        {
            Bundle bundle = new Bundle();
            bundle.putInt("workcategoryid", workcategoryid[0]);
            subfragment.setArguments(bundle);
        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentFrame, subfragment);
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("");
    }

    public void gotoWorkerTaskAssign(int workerid) {

        Intent myIntent = new Intent(this, TaskAssignDesc.class);
        myIntent.putExtra("workerid", workerid);
        startActivity(myIntent);
    }
    public void gotoworkerupdate( ) {

        Intent myIntent = new Intent(this, profile_update.class);
        startActivity(myIntent);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_workerType) {
            gotoHomepage();
        }
        else  if (id == R.id.nav_workertask) {
            gotoWorkerTask();
        }
        else  if (id == R.id.nav_workerupdate) {
            gotoworkerupdate();
        }
        if (id == R.id.loginlogout) {



            objUserSessionManager.logoutuser();

           // finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)  {
        getMenuInflater().inflate(R.menu.activity_menu,menu);
        MenuItem menuItem =menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(menuItem);


        return true;

    }


}
