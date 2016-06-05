package com.samifattah.cloudthoughleader.util;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by osamaabdelfattah on 5/17/16.
 */
public class FragmentsManager
{
    private final String s_szTag = "FragmentsManager";

    ArrayList<BaseFragment> m_FragmentsList = null;
    Activity m_Activity = null;
    FragmentManager m_FragmentsManager = null;
    int m_ContainerViewId =  0;
    BaseFragment m_ActiveBaseFragment = null;

    public FragmentsManager(Activity activity,int iContainerViewId)
    {
        Utility.logDebug(s_szTag,"FragmentManager");

        m_FragmentsList = new ArrayList<BaseFragment>();

        m_Activity = activity;

        m_ContainerViewId = iContainerViewId;

        m_FragmentsManager = m_Activity.getFragmentManager();

        Utility.Assert(m_FragmentsList!=null);

        Utility.Assert(m_FragmentsManager!=null);

        Utility.Assert(m_Activity!=null);
    }

    void addFragment( BaseFragment  baseFragment )
    {
        Utility.logDebug(s_szTag,"addFragment");

        Utility.Assert(m_FragmentsList!=null);

        if(m_FragmentsList!=null)
        {
            m_FragmentsList.add(baseFragment);
        }
    }

    void removeFragment( BaseFragment  baseFragment )
    {

        Utility.logDebug(s_szTag,"removeFragment");

        Utility.Assert(m_FragmentsList!=null);

        if(m_FragmentsList!=null)
        {
            m_FragmentsList.remove(baseFragment);
        }
    }

    BaseFragment getFragment(int iIndex)
    {
        Utility.logDebug(s_szTag,"getFragment");

        Utility.Assert(m_FragmentsList!=null);

        BaseFragment baseFragment = null;

        if(m_FragmentsList!=null)
        {

            baseFragment = m_FragmentsList.get(iIndex);

            Utility.Assert(baseFragment!=null);

        }

        return baseFragment;
    }

    BaseFragment getFragment( String szFragmentName )
    {
        Utility.logDebug(s_szTag,"getFragment");

        Utility.Assert(m_FragmentsList!=null);

        BaseFragment baseFragment = null;

        for( int iIndex = 0 ; iIndex < m_FragmentsList.size() ; iIndex++)
        {
            BaseFragment tempBaseFragment = m_FragmentsList.get(iIndex);

            if((tempBaseFragment!=null) && tempBaseFragment.equals(tempBaseFragment));
            {
                baseFragment = tempBaseFragment;

                break;
            }
        }

        return baseFragment;
    }


    public void addFragment(BaseFragment baseFragment , boolean bAddToBackStatck)
    {
        Utility.logDebug(s_szTag,"addFragment");

        Utility.Assert(baseFragment!=null);

        Utility.Assert(baseFragment.getFragmentStatus() != BaseFragment.FragmentStatus.FragmentStatus_onDestroyed);

        FragmentTransaction fragmentTransaction = m_FragmentsManager.beginTransaction();

        Utility.Assert(fragmentTransaction!=null);

        if(fragmentTransaction!=null)
        {

            fragmentTransaction.add(m_ContainerViewId, baseFragment, baseFragment.getFragmentName());

            m_ActiveBaseFragment = baseFragment;

            if (bAddToBackStatck)
            {
                fragmentTransaction.addToBackStack(baseFragment.getFragmentName());
            }

            fragmentTransaction.commit();
        }

    }

    public void removeFragment(BaseFragment baseFragment , boolean bAddToBackStatck)
    {
        Utility.logDebug(s_szTag,"removeFragment");

        Utility.Assert(baseFragment!=null);

        Utility.Assert(baseFragment.getFragmentStatus() != BaseFragment.FragmentStatus.FragmentStatus_onDestroyed);

        FragmentTransaction fragmentTransaction = m_FragmentsManager.beginTransaction();

        Utility.Assert(fragmentTransaction!=null);

        if(fragmentTransaction!=null)
        {

            Fragment oldFragment = m_FragmentsManager.findFragmentByTag(baseFragment.getFragmentName());

            Utility.Assert(oldFragment != null);

            if (oldFragment != null)
            {
                fragmentTransaction.remove(oldFragment);
            }

            if (bAddToBackStatck)
            {
                fragmentTransaction.addToBackStack(baseFragment.getFragmentName());
            }

            fragmentTransaction.commit();
        }
    }

    public void replaceFragment(BaseFragment baseFragment , boolean bAddToBackStatck)
    {
        Utility.logDebug(s_szTag,"replaceFragment");

        Utility.Assert(baseFragment.getFragmentStatus() != BaseFragment.FragmentStatus.FragmentStatus_onDestroyed);

        FragmentTransaction fragmentTransaction = m_FragmentsManager.beginTransaction();

        Utility.Assert(fragmentTransaction!=null);

        if(fragmentTransaction!=null)
        {

            fragmentTransaction.replace(m_ContainerViewId, baseFragment, baseFragment.getFragmentName());

            m_ActiveBaseFragment = baseFragment;

            if(baseFragment!=null)
            {
                fragmentTransaction.replace(m_ContainerViewId, baseFragment, baseFragment.getFragmentName());
            }

            if (bAddToBackStatck)
            {
                fragmentTransaction.addToBackStack(baseFragment.getFragmentName());
            }

            fragmentTransaction.commit();
        }
    }

    public void attachFragment(BaseFragment baseFragment , boolean bAddToBackStatck)
    {
        Utility.logDebug(s_szTag,"attachFragment");

        Utility.Assert(baseFragment.getFragmentStatus() != BaseFragment.FragmentStatus.FragmentStatus_onDestroyed);

        FragmentTransaction fragmentTransaction = m_FragmentsManager.beginTransaction();

        Utility.Assert(fragmentTransaction!=null);

        if(fragmentTransaction!=null)
        {
            Fragment theFragment = m_FragmentsManager.findFragmentByTag(baseFragment.getFragmentName());

            Utility.Assert(theFragment!=null);

            if (theFragment != null)
            {
                fragmentTransaction.attach(theFragment);

                m_ActiveBaseFragment = baseFragment;
            }

            if (bAddToBackStatck)
            {
                fragmentTransaction.addToBackStack(baseFragment.getFragmentName());
            }

            fragmentTransaction.commit();

        }
    }

    public void detachFragment(BaseFragment baseFragment , boolean bAddToBackStatck)
    {
        Utility.logDebug(s_szTag,"detachFragment");

        Utility.Assert(baseFragment.getFragmentStatus() != BaseFragment.FragmentStatus.FragmentStatus_onDestroyed);

        FragmentTransaction fragmentTransaction = m_FragmentsManager.beginTransaction();

        Utility.Assert(fragmentTransaction!=null);

        if(fragmentTransaction!=null)
        {
            Fragment theFragment = m_FragmentsManager.findFragmentByTag(baseFragment.getFragmentName());

            Utility.Assert(theFragment!=null);

            if (theFragment != null)
            {

                fragmentTransaction.detach(theFragment);

            }

            if (bAddToBackStatck)
            {
                fragmentTransaction.addToBackStack(baseFragment.getFragmentName());
            }

            fragmentTransaction.commit();

        }
    }

    public void previousFragment(BaseFragment baseFragment , boolean bAddToBackStatck)
    {
        Utility.logDebug(s_szTag,"previousFragment");

        if(m_FragmentsManager!=null)
        {
            m_FragmentsManager.popBackStack();
        }
    }

    public void registerListner(FragmentManager.OnBackStackChangedListener listener)
    {
        Utility.logDebug(s_szTag,"registerListner");

        if(m_FragmentsManager!=null)
        {
            m_FragmentsManager.addOnBackStackChangedListener(listener);
        }
    }

    public  void showFragmentDialog(BaseDialogFragment baseDialogFragment , boolean bAddToBackStatck)
    {

        Utility.logDebug(s_szTag,"showFragmentDialog");

        if(m_FragmentsManager!=null)
        {

            Bundle args = new Bundle();

            baseDialogFragment.setArguments(args);

            android.app.FragmentTransaction fragmentTransaction = m_FragmentsManager.beginTransaction();

            android.app.Fragment prev = m_FragmentsManager.findFragmentByTag(baseDialogFragment.getFragmentName());

            if (prev != null)
            {
                fragmentTransaction.remove(prev);
            }

            fragmentTransaction.addToBackStack(null);

            baseDialogFragment.show(fragmentTransaction, baseDialogFragment.getFragmentName());

        }

    }

}
