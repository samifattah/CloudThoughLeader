package com.samifattah.cloudthoughleader.util;

import android.os.Environment;
import android.util.Log;

public class Utility
{
	private static final String TAG = "Utility";
	
	public static boolean s_bIsDebugEnabloed = true;
	

	public static void Assert()
	{
        if(s_bIsDebugEnabloed)
        {
            throw new RuntimeException( "Assert" );
        }
	}

    public static void Assert( boolean bTest )
    {
        if(s_bIsDebugEnabloed)
        {

            if (bTest == false) {
                throw new RuntimeException("IQAssert");
            }
        }
    }
	
	public static void Assert( String szMessage )
	{
        if(s_bIsDebugEnabloed)
        {
            throw new RuntimeException(szMessage);
        }
	}

	public static String getPathToSDCard()
	{
		return Environment.getExternalStorageDirectory().toString();
	}
	
	public static void logDebug( String szTage , String szMessage )
	{
		if( s_bIsDebugEnabloed )
		{
			Log.d(szTage, szMessage);
		}
	}
}
