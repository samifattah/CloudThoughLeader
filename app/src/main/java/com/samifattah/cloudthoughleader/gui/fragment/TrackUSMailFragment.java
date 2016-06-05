package com.samifattah.cloudthoughleader.gui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.samifattah.cloudthoughleader.R;
import com.samifattah.cloudthoughleader.dialogfragment.WarnDialogFragment;
import com.samifattah.cloudthoughleader.util.BaseFragment;
import com.samifattah.cloudthoughleader.util.FragmentsManager;
import com.samifattah.cloudthoughleader.util.USPSHelper;
import com.samifattah.cloudthoughleader.util.Utility;

public class TrackUSMailFragment extends BaseFragment  implements USPSHelper.USPSHelperInterface, View.OnFocusChangeListener
{
    private Button   m_TrackButton              = null;
    private Button   m_ClearButton              = null;
    private EditText m_TrackingNumberEditText   = null;
    private TextView m_TrackingResultEditText   = null;
    private FragmentsManager m_FragmenstManager = null;
    private boolean m_bCleared = true;

    public interface TrackUSMailFragmentFragmentInterface extends BaseFragmentInterface
    {
    }

    public TrackUSMailFragment()
    {
        Utility.logDebug(m_szTag,"TrackUSMailFragment");

        m_szTag = new String("TrackUSMailFragment");

        m_iLayoutID = R.layout.fragment_track_us_mail_package;

        m_szFragmentName = new String(m_szTag);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Utility.logDebug(m_szTag,"onCreateView");

        super.onCreateView(inflater, container, savedInstanceState);

        m_TrackButton                 = (Button) m_View.findViewById(R.id.TrackButtonID);
        m_ClearButton                 = (Button) m_View.findViewById(R.id.ClearButtonID);
        m_TrackingNumberEditText      = (EditText) m_View.findViewById(R.id.TrackEditTextID);
        m_TrackingResultEditText      = (TextView) m_View.findViewById(R.id.TrackResultEditTextID);

        Utility.Assert(m_TrackButton!=null);
        Utility.Assert(m_ClearButton!=null);
        Utility.Assert(m_TrackingNumberEditText!=null);
        Utility.Assert(m_TrackingResultEditText!=null);

        if(m_TrackButton!=null)
        {
            m_TrackButton.setOnClickListener(this);
        }

        if(m_ClearButton!=null)
        {
            m_ClearButton.setOnClickListener(this);
        }

        m_TrackingNumberEditText.setOnFocusChangeListener(this);

        m_FragmenstManager = new FragmentsManager(this.getActivity(),0);

        Utility.Assert(m_FragmenstManager!=null);

        return m_View;
    }

    @Override
    public void handleClick(View v)
    {

        Utility.logDebug(m_szTag,"handleClick");

        if(v.getId()==m_TrackButton.getId())
        {
            hideKeyboard();

            String szTrackingNumber = m_TrackingNumberEditText.getText().toString();

            if(!szTrackingNumber.isEmpty())
            {

                if(m_bCleared) {

                    m_TrackButton.setEnabled(false);

                    m_ClearButton.setEnabled(false);

                    USPSHelper uSPSHelper = new USPSHelper();

                    uSPSHelper.setInterface((USPSHelper.USPSHelperInterface) this);

                    uSPSHelper.setTrackingNumber(szTrackingNumber);

                    uSPSHelper.excute();

                    m_bCleared = false;
                }
                else
                {
                    String szWarnMessage="Please Clear First";

                    WarnDialogFragment warnDialogFragment = new WarnDialogFragment();

                    warnDialogFragment.setMessage(szWarnMessage);

                    m_FragmenstManager.showFragmentDialog(warnDialogFragment,false);
                }
            }
            else
            {
                String szWarnMessage="The TrackID \nCan Not Be Empty";

                WarnDialogFragment warnDialogFragment = new WarnDialogFragment();

                warnDialogFragment.setMessage(szWarnMessage);

                m_FragmenstManager.showFragmentDialog(warnDialogFragment,false);
            }

        }

        if(v.getId()==m_ClearButton.getId())
        {
            hideKeyboard();

            m_TrackingNumberEditText.setText("");

            m_TrackingResultEditText.setText("");

            m_bCleared = true;
        }

    }

    @Override
    public void signalFinish(final USPSHelper.USPSResponse uSPSResponse)
    {
        Utility.logDebug(m_szTag,uSPSResponse.m_szUSPSResponse);


        this.getActivity().runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                final String szResultFinal =  uSPSResponse.m_szUSPSResponse;

                m_TrackingResultEditText.setText(szResultFinal);

                m_TrackButton.setEnabled(true);

                m_ClearButton.setEnabled(true);
            }
        });
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus)
    {
        if(!hasFocus)
        {
            hideKeyboard();
        }
    }


    private void hideKeyboard()
    {
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(m_TrackingNumberEditText.getWindowToken(), 0);
    }
}
