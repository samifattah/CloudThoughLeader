package com.samifattah.cloudthoughleader.util;

import android.app.Activity;
import android.content.Intent;

import java.io.Serializable;

/**
 * Created by osama on 5/15/2016.
 */
public class IntentHelper
{
    private   final String  m_CustomDataTag  = "CustomDataTag";
    protected String        m_szTag          = "IntentHelper";
    private   Intent        m_Intent         = null;
    private   Activity      m_Activity       = null;

    public IntentHelper()
    {
        Utility.logDebug(m_szTag,"IntentHelper");

        m_Intent = new Intent();
    }

    public IntentHelper(Activity activity)
    {
        Utility.logDebug(m_szTag,"IntentHelper");

        m_Activity = activity;

        m_Intent = new Intent();
    }

    public IntentHelper(Intent intent)
    {
        Utility.logDebug(m_szTag,"IntentHelper");

        m_Intent = intent;
    }

    public void navigateToOneWay(String s)
    {

    }

    public void setResultToCancle()
    {
        Utility.logDebug(m_szTag,"setResultToCancle");

        Utility.Assert(m_Activity!=null);

        if(m_Activity!=null)
        {
            m_Activity.setResult(Activity.RESULT_CANCELED, m_Intent);
        }
    }

    public void setResultToOk()
    {
        Utility.logDebug(m_szTag,"setResultToOk");

        Utility.Assert(m_Activity!=null);

        if(m_Activity!=null)
        {
            m_Activity.setResult(Activity.RESULT_OK,m_Intent);
        }

    }

    public abstract class IntentCustomData implements Serializable
    {
        public IntentCustomData()
        {
            Utility.logDebug(m_szTag,"IntentCustomData");
        }
    }

    public void setCustomData(IntentCustomData intentCustomData)
    {
        Utility.logDebug(m_szTag,"setCustomData");

        Utility.Assert(m_Intent!=null);

        if(m_Intent!=null)
        {
            m_Intent.putExtra(m_CustomDataTag,intentCustomData);
        }
    }

    public IntentCustomData getCustomData()
    {
        IntentCustomData intentCustomData = null;

        Utility.logDebug(m_szTag,"getCustomData");

        Utility.Assert(m_Intent!=null);

        if(m_Intent!=null)
        {
            intentCustomData = (IntentCustomData) m_Intent.getSerializableExtra(m_CustomDataTag);
        }

        Utility.Assert(intentCustomData!=null);

        return intentCustomData;
    }

}
