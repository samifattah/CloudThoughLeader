package com.samifattah.cloudthoughleader.util;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

public abstract class BaseActivity extends AppCompatActivity implements BaseFragment.BaseFragmentInterface , BaseDialogFragment.OnBaseDialogFragmentInterface
{
    private final String  m_LocalTag                   = "BaseActivity";
    protected String      m_szTag                      = null;
    protected int 		  m_iLayoutID				   = 0;

    public BaseActivity()
    {
        Utility.logDebug(m_szTag,"BaseActivity");
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        Utility.logDebug(m_LocalTag,"onCreate");

        super.onCreate(savedInstanceState);

        setContentView(m_iLayoutID);

        create();
    }

    @Override
    public void onPause()
    {
        Utility.logDebug(m_LocalTag,"onPause");

        super.onPause();
    }

    @Override
    public void onResume()
    {
        Utility.logDebug(m_LocalTag,"onResume");

        super.onResume();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        Utility.logDebug(m_LocalTag,"onRestoreInstanceState");

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onRestart()
    {
        Utility.logDebug(m_LocalTag,"onRestart");

        super.onRestart();
    }

    @Override
    public void onBackPressed()
    {
        int iReturn = backKeyPressed();

        if(iReturn == 0)
        {
            return;
        }
        else
        {
            super.onBackPressed();
        }

    }

    @Override
    public void onStart()
    {
        Utility.logDebug(m_LocalTag,"onStart");

        super.onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        Utility.logDebug(m_LocalTag,"onSaveInstanceState");

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onStop()
    {
        Utility.logDebug(m_LocalTag,"onStop");

        super.onStop();
    }

    @Override
    public void onDestroy()
    {
        Utility.logDebug(m_LocalTag,"onDestroy");

        super.onDestroy();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        Utility.logDebug(m_LocalTag,"onTouchEvent");

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        Utility.logDebug(m_LocalTag,"onCreateOptionsMenu");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Utility.logDebug(m_LocalTag,"onOptionsItemSelected");

        return super.onOptionsItemSelected(item);
    }

    public abstract  int backKeyPressed();


    public abstract  void create();


}
