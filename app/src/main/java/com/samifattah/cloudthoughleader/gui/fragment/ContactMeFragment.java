package com.samifattah.cloudthoughleader.gui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.samifattah.cloudthoughleader.R;
import com.samifattah.cloudthoughleader.util.BaseFragment;
import com.samifattah.cloudthoughleader.util.FragmentsManager;
import com.samifattah.cloudthoughleader.util.Utility;

public class ContactMeFragment extends BaseFragment
{
    private Button   m_CallMeButton              = null;
    private Button   m_EmailMeButton              = null;
    private FragmentsManager m_FragmenstManager = null;

    public interface ContactMeFragmentFragmentInterface extends BaseFragmentInterface
    {
    }

    public ContactMeFragment()
    {
        Utility.logDebug(m_szTag,"ContactMeFragment");

        m_szTag = new String("ContactMeFragment");

        m_iLayoutID = R.layout.fragment_contactme;

        m_szFragmentName = new String(m_szTag);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Utility.logDebug(m_szTag,"onCreateView");

        super.onCreateView(inflater, container, savedInstanceState);

        m_CallMeButton                 = (Button) m_View.findViewById(R.id.CallMeButtonID);
        m_EmailMeButton                = (Button) m_View.findViewById(R.id.EmailMeButtonID);

        Utility.Assert(m_CallMeButton!=null);
        Utility.Assert(m_EmailMeButton!=null);

        if(m_CallMeButton!=null)
        {
            m_CallMeButton.setOnClickListener(this);
        }

        if(m_EmailMeButton!=null)
        {
            m_EmailMeButton.setOnClickListener(this);
        }

        m_FragmenstManager = new FragmentsManager(this.getActivity(),0);

        Utility.Assert(m_FragmenstManager!=null);

        return m_View;
    }

    @Override
    public void handleClick(View v)
    {

        Utility.logDebug(m_szTag,"handleClick");

        if(v.getId()==m_CallMeButton.getId())
        {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+19175896702"));
            startActivity(intent);
        }

        if(v.getId()==m_EmailMeButton.getId())
        {
            try
            {


                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.putExtra(Intent.EXTRA_EMAIL, new String("mushib@gmail.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "question");
                intent.putExtra(Intent.EXTRA_TEXT, "");

                intent.setType("message/rfc822");

                intent.setPackage("com.google.android.gm");

                startActivity(intent);
            }
            catch (android.content.ActivityNotFoundException ex)
            {

            }
            finally
            {


            }
        }

    }

}
