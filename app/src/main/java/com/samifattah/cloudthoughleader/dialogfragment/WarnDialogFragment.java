package com.samifattah.cloudthoughleader.dialogfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.samifattah.cloudthoughleader.R;
import com.samifattah.cloudthoughleader.util.BaseDialogFragment;
import com.samifattah.cloudthoughleader.util.Utility;


public class WarnDialogFragment extends BaseDialogFragment
{
   
	public static final String TAG = "StatsDialogFragment";
	private Button m_CloseButton = null;
	private String m_szMessage="";
	private TextView m_MessageTextView = null;

	public WarnDialogFragment()
	{
		Utility.logDebug(m_szTag,"WarnDialogFragment");

		m_szTag = new String("WarnDialogFragment");

		m_iLayoutID = R.layout.dialog_warn;

		m_szFragmentName = new String(m_szTag);

		m_iStyleAndThemArg = 2;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		Utility.logDebug(m_szTag,"onCreateView");

		super.onCreateView(inflater, container, savedInstanceState);

		m_CloseButton  = (Button) m_View.findViewById(R.id.CloseButtonID);

		m_MessageTextView  = (TextView) m_View.findViewById(R.id.MessageTextViewID);

		Utility.Assert(m_CloseButton!=null);

		Utility.Assert(m_MessageTextView!=null);

		if(m_CloseButton!=null)
		{
			m_CloseButton.setOnClickListener(this);
		}

		if(m_MessageTextView!=null)
		{
			m_MessageTextView.setText(m_szMessage);
		}

		return m_View;
	}


	@Override
	public void onBaseClick(View v)
	{
		if(v.getId()==m_CloseButton.getId())
		{
			this.dismiss();
		}
	}

	@Override
	public boolean onBaseLongClick(View v)
	{
		return false;
	}

	public void setMessage(String szWarnMessage)
	{
		m_szMessage = new String(szWarnMessage);
	}
}