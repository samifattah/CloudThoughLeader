package com.samifattah.cloudthoughleader.util;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;

public abstract class BaseDialogFragment extends DialogFragment implements OnClickListener, OnLongClickListener
{

	protected String      m_szTag                      = null;
	protected View m_View ;
	protected int m_iStyleAndThemArg;
	protected OnBaseDialogFragmentInterface m_OnIQDialogFragmentInterface = null;
	private final String  m_LocalTag                    = "BaseDialogFragment";
	protected Activity    m_Activity 		  		    = null;
	protected int 		  m_iLayoutID				    = 0;
	protected String m_szFragmentName                   = null;


	public interface OnBaseDialogFragmentInterface
	{

	}		
	
	public BaseDialogFragment(  )
	{
		Utility.logDebug(m_LocalTag,"BaseDialogFragment");
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
		Utility.logDebug(m_LocalTag,"onCreate");

        super.onCreate(savedInstanceState);
            
        int iStyle = DialogFragment.STYLE_NORMAL;
        
        int iTheme = 0;
        
        switch ((m_iStyleAndThemArg-1)%6) 
        {
            case 1: 
            	{
            		iStyle = DialogFragment.STYLE_NO_TITLE;
            		
            		break;
            	}
        
            case 2: 
            	{
            		iStyle = DialogFragment.STYLE_NO_FRAME;
            		
            		break;
            	}
            
            case 3: 
            	{
            		iStyle = DialogFragment.STYLE_NO_INPUT;
            		
            		break;
            	}
            
            case 4: 
            	{
            		iStyle = DialogFragment.STYLE_NORMAL;
            		
            		break;
            	}
            
            case 5: 
            	{
            		iStyle = DialogFragment.STYLE_NORMAL;
            		
            		break;
            	}
            
            case 6: 
            	{
            		iStyle = DialogFragment.STYLE_NO_TITLE;
            	
            		break;
            	}

            
            case 7: 
            	{
            		iStyle = DialogFragment.STYLE_NO_FRAME;
            		
            		break;
            	}
            
            case 8: 
            	{
            		iStyle = DialogFragment.STYLE_NORMAL;
            		
            		break;
            	}
        }
        switch ((m_iStyleAndThemArg-1)%6) 
        {
            case 4: 
            	{
            		iTheme = android.R.style.Theme_Holo;
            		
            		break;
            	}
            
            case 5: 
            	{
            		iTheme = android.R.style.Theme_Holo_Light_Dialog; 
            		
            		break;
            		
            	}
            	            	
            case 6:
            {
            	iTheme = android.R.style.Theme_Holo_Light; 
            	
            	break;
            }
            
            case 7: 
            	{
            		iTheme = android.R.style.Theme_Holo_Light_Panel; 
            		
            		break;
            	}
            
            case 8: 
            	{
            		iTheme = android.R.style.Theme_Holo_Light; 
            		
            		break;
            	}
        }
        
        setStyle(iStyle, iTheme);
 
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState)
    {
		Utility.logDebug(m_LocalTag,"onCreateView");

    	m_View = inflater.inflate( m_iLayoutID , container , false);
    	
        return m_View;
    }

	@Override
	public void onAttach(Activity activity)
	{
		Utility.logDebug(m_LocalTag,"onAttach");

		super.onAttach(activity);
		
		try 
		{
			m_OnIQDialogFragmentInterface = ( OnBaseDialogFragmentInterface )activity;
	    } 
		catch (ClassCastException e)
		{
		  Utility.Assert();
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		Utility.logDebug(m_LocalTag,"onActivityCreated");

		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart()
	{
		Utility.logDebug(m_LocalTag,"onStart");

		super.onStart();
	}

	@Override
	public void onResume()
	{
		Utility.logDebug(m_LocalTag,"onResume");

		super.onResume();
	}

	@Override
	public void onPause()
	{
		Utility.logDebug(m_LocalTag,"onPause");

		super.onPause();
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
	public void onDestroyView()
	{
		Utility.logDebug(m_LocalTag,"onDestroyView");

		super.onDestroyView();
	}

	@Override
	public void onDestroy()
	{
		Utility.logDebug(m_LocalTag,"onDestroy");

		super.onDestroy();
	}

	@Override
	public void onDetach()
	{
		Utility.logDebug(m_LocalTag,"onDetach");

		super.onDetach();
	}

	@Override
	public void onDismiss(DialogInterface dialog)
	{
		Utility.logDebug(m_LocalTag,"onDetach");

		super.onDismiss(dialog);
	}


	@Override
	public void onClick(View v)
	{
		Utility.logDebug(m_LocalTag,"onClick");

		onBaseClick( v );
	}
	
	@Override
	public boolean onLongClick(View v)
	{
		Utility.logDebug(m_LocalTag,"onLongClick");

		return onBaseLongClick( v );
	}

	public String getFragmentName()
	{
		Utility.logDebug(m_LocalTag,"getFragmentName");

		return m_szFragmentName;
	}

	abstract public void onBaseClick(View v);
	abstract public boolean onBaseLongClick(View v);

}