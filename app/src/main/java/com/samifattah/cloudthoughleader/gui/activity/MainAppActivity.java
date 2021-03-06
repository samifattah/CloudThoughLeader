package com.samifattah.cloudthoughleader.gui.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.samifattah.cloudthoughleader.R;
import com.samifattah.cloudthoughleader.gui.fragment.ContactMeFragment;
import com.samifattah.cloudthoughleader.gui.fragment.WebBroswerFragment;
import com.samifattah.cloudthoughleader.util.BaseActivity;
import com.samifattah.cloudthoughleader.util.BaseFragment;
import com.samifattah.cloudthoughleader.util.FragmentsManager;
import com.samifattah.cloudthoughleader.util.Utility;

import java.io.IOException;

public class MainAppActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener
{

    private FragmentsManager m_FragmenstManager = null;
    private ListView m_DrawerList = null;
    private DrawerLayout m_DrawerLayout = null;
    private ArrayAdapter<String> m_Adapter = null;
    private ActionBarDrawerToggle m_DrawerToggle = null;
    private Toolbar m_Toolbar = null;
    private NavigationView m_NavigationView = null;

    public MainAppActivity()
    {
        int iIndex = 0;

        Utility.logDebug(m_szTag,"MainAppActivity");

        m_szTag = new String("MainActivity");

        m_iLayoutID = R.layout.activity_main_app;

        m_FragmenstManager = new FragmentsManager(this,R.id.FrameLayoutMainAPPID);

        Utility.Assert(m_FragmenstManager!=null);

    }


    public void create()
    {
        Utility.logDebug(m_szTag,"create");

        m_Toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(m_Toolbar);

        m_DrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        Utility.Assert(m_DrawerLayout!=null);

        m_DrawerToggle = new ActionBarDrawerToggle(this, m_DrawerLayout,m_Toolbar,R.string.app_name,R.string.app_name);

        Utility.Assert(m_DrawerToggle!=null);

        m_DrawerLayout.setDrawerListener( m_DrawerToggle );

        m_DrawerToggle.syncState();

        m_NavigationView = (NavigationView) findViewById(R.id.nav_view);

        Utility.Assert(m_DrawerToggle!=null);

        m_NavigationView.setNavigationItemSelectedListener(this);

        BaseFragment baseFragment = new ContactMeFragment();

        m_FragmenstManager.addFragment(baseFragment,false);

        this.setTitle("CloudThoughLeader");

    }

    @Override
    public int backKeyPressed()
    {
        int iResult = 1;

        if (m_DrawerLayout.isDrawerOpen(GravityCompat.START))
        {
            m_DrawerLayout.closeDrawer(GravityCompat.START);

            iResult = 0;
        }

        return iResult;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.contactmeid)
        {
            BaseFragment baseFragment = new ContactMeFragment();

            m_FragmenstManager.replaceFragment(baseFragment,false);

        }

        if (id == R.id.experienceid)
        {
            WebBroswerFragment webBroswerFragment = new WebBroswerFragment();

            webBroswerFragment.setURL("http://www.mushibhuiyan.com/bio/experience/");

            m_FragmenstManager.replaceFragment(webBroswerFragment,false);

        }

        if (id == R.id.achievement)
        {
            WebBroswerFragment webBroswerFragment = new WebBroswerFragment();

            webBroswerFragment.setURL("http://www.mushibhuiyan.com/achievements/");

            m_FragmenstManager.replaceFragment(webBroswerFragment,false);

        }

        if (id == R.id.industriesid)
        {
            WebBroswerFragment webBroswerFragment = new WebBroswerFragment();

            webBroswerFragment.setURL("http://www.mushibhuiyan.com/bio/industries/");

            m_FragmenstManager.replaceFragment(webBroswerFragment,false);

        }

        if (id == R.id.myvaluesid)
        {
            WebBroswerFragment webBroswerFragment = new WebBroswerFragment();

            webBroswerFragment.setURL("http://www.mushibhuiyan.com/bio/my-values/");

            m_FragmenstManager.replaceFragment(webBroswerFragment,false);

        }

        if (id == R.id.whymeid)
        {
            WebBroswerFragment webBroswerFragment = new WebBroswerFragment();

            webBroswerFragment.setURL("http://www.mushibhuiyan.com/why-me/");

            m_FragmenstManager.replaceFragment(webBroswerFragment,false);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        m_DrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }


}
