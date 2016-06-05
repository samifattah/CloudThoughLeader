package com.samifattah.cloudthoughleader.util;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by osamaabdelfattah on 5/14/16.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener
{
    private final String  m_LocalTag                           = "BaseFragment";
    protected String      m_szTag                              = null;
    protected Activity    m_Activity 		  		           = null;
    protected View        m_View					           = null;
    protected int 		  m_iLayoutID				           = 0;
    protected int         m_iFragmentIndex                     = 0;
    protected String m_szFragmentName                          = null;
    protected BaseFragmentInterface    m_BaseFragmentInterface = null;
    FragmentStatus m_FragmentStatus   = FragmentStatus.FragmentStatus_Undefined;
    public enum FragmentStatus
    {
        FragmentStatus_Created,
        FragmentStatus_CreateViewed,
        FragmentStatus_Attached,
        FragmentStatus_ActivityCreated,
        FragmentStatus_Started,
        FragmentStatus_Resumed,
        FragmentStatus_Paused,
        FragmentStatus_SaveInstance,
        FragmentStatus_Stoped,
        FragmentStatus_DestroyViewed,
        FragmentStatus_onDestroyed,
        FragmentStatus_onDetached,
        FragmentStatus_Undefined
    }



    public interface BaseFragmentInterface
    {
    }

    public int getFragmentIndex()
    {
        Utility.logDebug(m_LocalTag,"getFragmentIndex");

        return m_iFragmentIndex;
    }

    public void setFragmentIndex(int iFragmentIndex)
    {
        Utility.logDebug(m_LocalTag,"setFragmentIndex");

        m_iFragmentIndex = iFragmentIndex;
    }

    public String getFragmentName()
    {
        Utility.logDebug(m_LocalTag,"getFragmentName");

        return m_szFragmentName;
    }

    public void setFragmentName(String szFragmentName)
    {
        Utility.logDebug(m_LocalTag,"setFragmentName");

        m_szFragmentName = new String(szFragmentName);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        Utility.logDebug(m_LocalTag,"onCreate");

        super.onCreate(savedInstanceState);

        m_FragmentStatus = FragmentStatus.FragmentStatus_Created;
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState)
    {
        Utility.logDebug(m_LocalTag,"onCreateView");

        m_View = inflater.inflate( m_iLayoutID , container , false );

        Utility.Assert( m_View != null );

        m_FragmentStatus = FragmentStatus.FragmentStatus_CreateViewed;

        return m_View;
    }

    @Override
    public void onAttach(Activity activity)
    {
        Utility.logDebug(m_LocalTag,"onAttach");

        super.onAttach(activity);

        processOnAttach((Context) activity);
    }

    @Override
    public void onAttach(Context context)
    {
        Utility.logDebug(m_LocalTag,"onAttach");

        super.onAttach(context);

        processOnAttach(context);
    }

    public void processOnAttach(Context context)
    {
        Utility.logDebug(m_LocalTag,"processOnAttach");

        m_Activity = (Activity) context;

        Utility.Assert( m_Activity != null );

        if(m_Activity!=null)
        {
            try
            {
                m_BaseFragmentInterface = (BaseFragmentInterface) m_Activity;
            }
            catch (ClassCastException e)
            {
                Utility.Assert();
            }
        }

        m_FragmentStatus = FragmentStatus.FragmentStatus_Attached;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        Utility.logDebug(m_LocalTag,"onActivityCreated");

        m_FragmentStatus = FragmentStatus.FragmentStatus_ActivityCreated;

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart()
    {
        Utility.logDebug(m_LocalTag,"onStart");

        m_FragmentStatus = FragmentStatus.FragmentStatus_Started;

        super.onStart();
    }

    @Override
    public void onResume()
    {
        Utility.logDebug(m_LocalTag,"onResume");

        m_FragmentStatus = FragmentStatus.FragmentStatus_Resumed;

        super.onResume();
    }

    @Override
    public void onPause()
    {
        Utility.logDebug(m_LocalTag,"onPause");

        m_FragmentStatus = FragmentStatus.FragmentStatus_Paused;

        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        Utility.logDebug(m_LocalTag,"onSaveInstanceState");

        m_FragmentStatus = FragmentStatus.FragmentStatus_SaveInstance;

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onStop()
    {
        Utility.logDebug(m_LocalTag,"onStop");

        m_FragmentStatus = FragmentStatus.FragmentStatus_Stoped;

        super.onStop();
    }

    @Override
    public void onDestroyView()
    {
        Utility.logDebug(m_LocalTag,"onDestroyView");

        m_FragmentStatus = FragmentStatus.FragmentStatus_DestroyViewed;

        super.onDestroyView();
    }

    @Override
    public void onDestroy()
    {
        Utility.logDebug(m_LocalTag,"onDestroy");

        m_FragmentStatus = FragmentStatus.FragmentStatus_onDestroyed;

        super.onDestroy();
    }

    @Override
    public void onDetach()
    {
        Utility.logDebug(m_LocalTag,"onDetach");

        m_FragmentStatus = FragmentStatus.FragmentStatus_onDetached;

        super.onDetach();
    }

    @Override
    public void onClick(View v)
    {
        Utility.logDebug(m_LocalTag,"onClick");

        handleClick(v);
    }

    public FragmentStatus getFragmentStatus()
    {
        return m_FragmentStatus;
    }

    public abstract void handleClick(View v);
}
