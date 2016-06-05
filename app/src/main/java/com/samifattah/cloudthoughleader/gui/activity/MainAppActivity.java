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
import com.samifattah.cloudthoughleader.gui.fragment.TrackUSMailFragment;
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

        BaseFragment baseFragment = new TrackUSMailFragment();

        m_FragmenstManager.addFragment(baseFragment,false);

        this.setTitle("USMailPackageTracker");

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
    public boolean onNavigationItemSelected(MenuItem item)
    {

        int id = item.getItemId();


        if (id == R.id.contactus)
        {
            try
            {


                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, "oasf2010@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "question");
                intent.putExtra(Intent.EXTRA_TEXT, "");

                intent.setType("message/rfc822");

                intent.setPackage("com.google.android.gm");

                startActivity(intent);
            }
            catch(android.content.ActivityNotFoundException ex)
            {

            }
            finally
            {


            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        m_DrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }


}
