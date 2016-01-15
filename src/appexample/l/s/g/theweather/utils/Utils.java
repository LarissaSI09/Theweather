package appexample.l.s.g.theweather.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {
	
	 private static Context context;

	    public Utils(Context context) {
	        this.context = context;
	    }
	
	public static String cleanString(String input){
		String textClean;
		textClean = input.replaceAll("\\s","");
		
		return textClean;
	}
	
	public static boolean verifyNetwork(Context context) {
		ConnectivityManager connectionNet = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = connectionNet.getActiveNetworkInfo();
		return netInfo != null && netInfo.isConnected();//netInfo.isConnectedOrConnecting();
	}

}
