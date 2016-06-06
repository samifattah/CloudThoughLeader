package com.samifattah.cloudthoughleader.gui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.samifattah.cloudthoughleader.R;
import com.samifattah.cloudthoughleader.util.BaseFragment;
import com.samifattah.cloudthoughleader.util.FragmentsManager;
import com.samifattah.cloudthoughleader.util.Utility;

public class WebBroswerFragment extends BaseFragment
{
    private WebView          m_WebView          = null;
    private FragmentsManager m_FragmenstManager = null;
    private java.lang.String m_szURL            = "";

    public interface TrackUSMailFragmentFragmentInterface extends BaseFragmentInterface
    {
    }

    public WebBroswerFragment()
    {
        Utility.logDebug(m_szTag,"WebBroswerFragment");

        m_szTag = new String("WebBroswerFragment");

        m_iLayoutID = R.layout.fragment_web_browser;

        m_szFragmentName = new String(m_szTag);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Utility.logDebug(m_szTag,"onCreateView");

        super.onCreateView(inflater, container, savedInstanceState);

        m_WebView  = (WebView) m_View.findViewById(R.id.webViewid);

        Utility.Assert(m_WebView!=null);

        if(m_WebView!=null)
        {
            m_WebView.loadUrl(m_szURL);

            m_WebView.setWebViewClient(new WebViewClient()
            {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url)
                {
                    view.loadUrl(url);
                    return true;
                }
            });
        }

        m_FragmenstManager = new FragmentsManager(this.getActivity(),0);

        Utility.Assert(m_FragmenstManager!=null);

        return m_View;
    }

    @Override
    public void handleClick(View v)
    {

        Utility.logDebug(m_szTag,"handleClick");
    }

    public void setURL(String szURL)
    {

        Utility.logDebug(m_szTag,"navigate");

        m_szURL = new String(szURL);
    }

}
