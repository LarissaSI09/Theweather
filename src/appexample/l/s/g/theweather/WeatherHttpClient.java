
package appexample.l.s.g.theweather;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherHttpClient {

	private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
	private static String IMG_URL = "http://openweathermap.org/img/w/";
	private static String API_KEY = "&APPID=bf9960cdd34e2a5bfd4db59e3fe6880f";
	private static String LANGUAGE = "&lang=pt";
	private static String BASE_FORECAST_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&q=";

	
	public String getWeatherData(String location) {
		HttpURLConnection con = null ;
		InputStream is = null;

		try {
			con = (HttpURLConnection) ( new URL(BASE_URL+location+LANGUAGE+API_KEY)).openConnection();
			con.setRequestMethod("GET");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.connect();

			StringBuffer buffer = new StringBuffer();
			is = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ( (line = br.readLine()) != null )
				buffer.append(line + "\r\n");
			
			is.close();
			con.disconnect();
			return buffer.toString();
	    }
		catch(Throwable t) {
			t.printStackTrace();
		}
		finally {
			try { is.close(); } catch(Throwable t) {}
			try { con.disconnect(); } catch(Throwable t) {}
		}

		return null;
				
	}
	
	public String getForecastWeatherData(String location, String numdays) {
		HttpURLConnection con = null ;
		InputStream is = null;
		int daysWeek = Integer.parseInt(numdays);
				
		try {

			String url = BASE_FORECAST_URL+location+LANGUAGE+API_KEY;

			url = url+"&cnt="+daysWeek;
			con = (HttpURLConnection) (new URL(url)).openConnection();
			con.setRequestMethod("GET");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.connect();
			
			StringBuffer buffer1 = new StringBuffer();
			is = con.getInputStream();
			BufferedReader br1 = new BufferedReader(new InputStreamReader(is));
			String line1 = null;
			while ((line1 = br1.readLine()) != null )
				buffer1.append(line1 + "\r\n");
			
			is.close();
			con.disconnect();
		
			return buffer1.toString();
	    }
		catch(Throwable t) {
			t.printStackTrace();
		}
		finally {
			try { is.close(); } catch(Throwable t) {}
			try { con.disconnect(); } catch(Throwable t) {}
		}

		return null;
				
	}
	
	public byte[] getImage(String code) {
		HttpURLConnection con = null ;
		InputStream is = null;
		try {
			con = (HttpURLConnection) ( new URL(IMG_URL+code+".png")).openConnection();
			con.setRequestMethod("GET");
			//con.setDoInput(true);
			//con.setDoOutput(true);
			con.connect();

			is = con.getInputStream();
			byte[] buffer = new byte[1024];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			while (is.read(buffer) != -1)
				baos.write(buffer);
			
			return baos.toByteArray();
	    }
		catch(Throwable t) {
			t.printStackTrace();
		}
		
		finally {
			try { is.close(); } catch(Throwable t) {}
			try { con.disconnect(); } catch(Throwable t) {}
		}
		
		return null;
		
	}
}
