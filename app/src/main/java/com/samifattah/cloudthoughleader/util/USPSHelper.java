package com.samifattah.cloudthoughleader.util;

import android.os.AsyncTask;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;
import java.net.URLEncoder;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by osama on 5/25/2016.
 */
public class USPSHelper
{
    private static final String TAG = "USPSHelper";

    String m_szTrackingNumber = null;

    USPSHelperInterface m_USPSHelperInterface = null;

    private String m_szResult = "";

    private USPSResponse m_USPSResponse = null;


    public interface USPSHelperInterface
    {
        void signalFinish(USPSResponse uSPSResponse);
    }

    public class USPSResponse
    {
        public boolean m_bResult = false;

        public String m_szUSPSResponse = "";
    }

    public USPSHelper()
    {
        m_USPSResponse = new USPSResponse();
    }

    public String getTrackingNumber()
    {
        return m_szTrackingNumber;
    }

    public void setTrackingNumber(String szTrackingNumber)
    {
        m_szTrackingNumber = szTrackingNumber;
    }

    public void excute()
    {
        AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();

        asyncTaskRunner.execute();
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String>
    {

        public AsyncTaskRunner()
        {

        }

        @Override
        protected String doInBackground(String... params)
        {
            m_USPSResponse.m_bResult = true;

            m_USPSResponse.m_szUSPSResponse = new String("Could not connect to server");

            try
            {
                String szURLOrg  = "http://production.shippingapis.com/ShippingAPI.dll?API=TrackV2&XML=";
                String szURL = "";
                szURL = szURL + "<TrackRequest USERID=\"904NA0004858\">";
                szURL = szURL + "<TrackID ID=\"";
                szURL = szURL + m_szTrackingNumber;
                szURL = szURL + "\"></TrackID>";
                szURL = szURL + "</TrackRequest>";

                String encodedur = URLEncoder.encode(szURL,"UTF-8");

                String szNewURL = szURLOrg + encodedur;

                URL url = new URL(szNewURL);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setDoOutput(true);;
                urlConnection.setRequestMethod("GET");
                urlConnection.setInstanceFollowRedirects(false);
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setUseCaches(false);
                urlConnection.setRequestProperty("Content-Type", "application/xml");
                urlConnection.setConnectTimeout(5000);

                urlConnection.connect();

                int statusCode = urlConnection.getResponseCode();

                if (statusCode == 200)
                {
                    InputStream it = new BufferedInputStream(urlConnection.getInputStream());
                    InputStreamReader read = new InputStreamReader(it);
                    BufferedReader buff = new BufferedReader(read);
                    StringBuilder dta = new StringBuilder();
                    String chunks;
                    while ((chunks = buff.readLine()) != null)
                    {
                        dta.append(chunks);
                    }

                    Utility.logDebug(TAG,dta.toString());

                    m_szResult = dta.toString();

                    m_USPSResponse = parseUSPSTrackResponse(m_szResult);

                }
            }
            catch (IOException e)
            {
                e.printStackTrace();


            }

            return "";
        }

        @Override
        protected void onPostExecute(String result)
        {
            m_USPSHelperInterface.signalFinish(m_USPSResponse);
        }

        @Override
        protected void onPreExecute()
        {
        }

        @Override
        protected void onProgressUpdate(String... text)
        {
        }

    }


    public void setInterface(USPSHelperInterface uSPSHelperInterface)
    {
        m_USPSHelperInterface =  uSPSHelperInterface;
    }

    public String getReult()
    {
        return m_szResult;
    }

    private USPSResponse parseUSPSTrackResponse(String szRsponseString)
    {
        org.w3c.dom.Document document = null;

        DocumentBuilderFactory documentBuilderFactory = null;

        DocumentBuilder documentBuilder = null;

        USPSResponse uspsResponse = new USPSResponse();

        if (szRsponseString.isEmpty())
        {
            return uspsResponse;
        }

        {
            documentBuilderFactory = DocumentBuilderFactory.newInstance();

            try
            {
                documentBuilder = documentBuilderFactory.newDocumentBuilder();
            }
            catch (ParserConfigurationException e)
            {
                e.printStackTrace();

                return uspsResponse;
            }
        }

        {
            try
            {
                ByteArrayInputStream stream = new ByteArrayInputStream(szRsponseString.getBytes());

                document = documentBuilder.parse(stream);
            }
            catch (SAXException e)
            {
                e.printStackTrace();

                return uspsResponse;
            }
            catch (IOException e)
            {
                e.printStackTrace();

                return uspsResponse;
            }
        }

        {

            Element root = document.getDocumentElement();

            if(root==null)
            {
                return uspsResponse;
            }

            String Tag = root.getTagName();

            if(Tag.equals("TrackResponse"))
            {

                NodeList nodes = root.getElementsByTagName("TrackSummary");

                if(nodes==null)
                {
                    return uspsResponse;
                }

                int iLength = nodes.getLength();

                if(iLength==1)
                {
                    Element elmenet = (Element) nodes.item(0);

                    if(elmenet!=null)
                    {
                        String szTageName = elmenet.getTagName();

                        if(szTageName.equals("TrackSummary"));
                        {
                            uspsResponse.m_szUSPSResponse  = elmenet.getTextContent();

                            uspsResponse.m_bResult = true;

                            return uspsResponse;
                        }

                    }
                    else
                    {
                        return uspsResponse;
                    }

                }

            }
            else
            {
                return uspsResponse;
            }


        }

        return uspsResponse;

    }

}
