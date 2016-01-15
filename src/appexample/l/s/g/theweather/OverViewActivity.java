package appexample.l.s.g.theweather;

import java.util.Calendar;
import org.json.JSONException;
import com.example.theweather.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import appexample.l.s.g.theweather.model.DayForecast;
import appexample.l.s.g.theweather.model.Weather;
import appexample.l.s.g.theweather.model.WeatherForecast;
import appexample.l.s.g.theweather.utils.Utils;

public class OverViewActivity extends Activity {

	String city;
	String country;
	String location;
	String condition;

	TextView temp_day;
	TextView condition_day;
	ImageView icon_day;

	TextView max_firstday;
	TextView max_secondday;
	TextView max_thirdday;
	TextView max_fourthday;
	TextView max_fifthday;

	TextView min_firstday;
	TextView min_secondday;
	TextView min_thirdday;
	TextView min_fourthday;
	TextView min_fifthday;

	TextView firstday;
	TextView secondday;
	TextView thirdday;
	TextView fourthday;
	TextView fifthday;

	Utils utils;
	ProgressDialog progressDialog;
	LinearLayout linearDetails;

	String temp;
	String temp_max;
	String temp_min;
	String conditionDetails;
	String humidity;
	String pressure;
	String wind;
	String clouds;

	int today;

	private static String daysWeek = "5";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overview);

		initViews();

		linearDetails.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(OverViewActivity.this,
						WeatherDetailsActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("temp", temp);
				bundle.putString("tempmax", temp_max);
				bundle.putString("tempmin", temp_min);
				bundle.putString("condition", condition);
				bundle.putString("humidity", humidity);
				bundle.putString("pressure", pressure);
				bundle.putString("wind", wind);
				bundle.putString("clouds", clouds);

				intent.putExtras(bundle);
				startActivity(intent);
			}
		});

		Bundle bundle = getIntent().getExtras();
		city = bundle.getString("city");
		country = bundle.getString("country");

		getActionBar().setTitle(city + " - " + country);
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLUE));

		city = Utils.cleanString(city);
		country = Utils.cleanString(country);

		location = city + "," + country;

		Calendar calander = Calendar.getInstance();
		today = calander.get(Calendar.DAY_OF_WEEK);

		switch (today) {
		case 1:
			firstday.setText("SEG");
			firstday.setVisibility(View.VISIBLE);
			secondday.setText("TER");
			secondday.setVisibility(View.VISIBLE);
			thirdday.setText("QUA");
			thirdday.setVisibility(View.VISIBLE);
			fourthday.setText("QUI");
			fourthday.setVisibility(View.VISIBLE);
			fifthday.setText("SEX");
			fifthday.setVisibility(View.VISIBLE);
			break;
		case 2:
			firstday.setText("TER");
			firstday.setVisibility(View.VISIBLE);
			secondday.setText("QUA");
			secondday.setVisibility(View.VISIBLE);
			thirdday.setText("QUI");
			thirdday.setVisibility(View.VISIBLE);
			fourthday.setText("SEX");
			fourthday.setVisibility(View.VISIBLE);
			fifthday.setText("SAB");
			fifthday.setVisibility(View.VISIBLE);
			break;
		case 3:
			firstday.setText("QUA");
			firstday.setVisibility(View.VISIBLE);
			secondday.setText("QUI");
			secondday.setVisibility(View.VISIBLE);
			thirdday.setText("SEX");
			thirdday.setVisibility(View.VISIBLE);
			fourthday.setText("SAB");
			fourthday.setVisibility(View.VISIBLE);
			fifthday.setText("DOM");
			fifthday.setVisibility(View.VISIBLE);
			break;
		case 4:
			firstday.setText("QUI");
			firstday.setVisibility(View.VISIBLE);
			secondday.setText("SEX");
			secondday.setVisibility(View.VISIBLE);
			thirdday.setText("SAB");
			thirdday.setVisibility(View.VISIBLE);
			fourthday.setText("DOM");
			fourthday.setVisibility(View.VISIBLE);
			fifthday.setText("SEG");
			fifthday.setVisibility(View.VISIBLE);
			break;
		case 5:
			firstday.setText("SEX");
			firstday.setVisibility(View.VISIBLE);
			secondday.setText("SAB");
			secondday.setVisibility(View.VISIBLE);
			thirdday.setText("DOM");
			thirdday.setVisibility(View.VISIBLE);
			fourthday.setText("SEG");
			fourthday.setVisibility(View.VISIBLE);
			fifthday.setText("TER");
			fifthday.setVisibility(View.VISIBLE);
			break;
		case 6:
			firstday.setText("SAB");
			firstday.setVisibility(View.VISIBLE);
			secondday.setText("DOM");
			secondday.setVisibility(View.VISIBLE);
			thirdday.setText("SEG");
			thirdday.setVisibility(View.VISIBLE);
			fourthday.setText("TER");
			fourthday.setVisibility(View.VISIBLE);
			fifthday.setText("QUA");
			fifthday.setVisibility(View.VISIBLE);
			break;
		case 7:
			firstday.setText("DOM");
			firstday.setVisibility(View.VISIBLE);
			secondday.setText("SEG");
			secondday.setVisibility(View.VISIBLE);
			thirdday.setText("TER");
			thirdday.setVisibility(View.VISIBLE);
			fourthday.setText("QUA");
			fourthday.setVisibility(View.VISIBLE);
			fifthday.setText("QUI");
			fifthday.setVisibility(View.VISIBLE);
			break;

		}

		JSONWeatherTask task = new JSONWeatherTask();
		task.execute(new String[] { location });

		JSONForecastWeatherTask task1 = new JSONForecastWeatherTask();
		task1.execute(new String[] { location, daysWeek });

	}

	public void initViews() {

		max_firstday = (TextView) findViewById(R.id.max_firstday);
		max_secondday = (TextView) findViewById(R.id.max_secondday);
		max_thirdday = (TextView) findViewById(R.id.max_thirdday);
		max_fourthday = (TextView) findViewById(R.id.max_fourthday);
		max_fifthday = (TextView) findViewById(R.id.max_fifthday);

		min_firstday = (TextView) findViewById(R.id.min_firstday);
		min_secondday = (TextView) findViewById(R.id.min_secondday);
		min_thirdday = (TextView) findViewById(R.id.min_thirdday);
		min_fourthday = (TextView) findViewById(R.id.min_fourthday);
		min_fifthday = (TextView) findViewById(R.id.min_fifthday);

		firstday = (TextView) findViewById(R.id.firstday);
		secondday = (TextView) findViewById(R.id.secondday);
		thirdday = (TextView) findViewById(R.id.thirdday);
		fourthday = (TextView) findViewById(R.id.fourthday);
		fifthday = (TextView) findViewById(R.id.fifthday);

		icon_day = (ImageView) findViewById(R.id.icon_dia);
		temp_day = (TextView) findViewById(R.id.temp_day);
		condition_day = (TextView) findViewById(R.id.condition_day);
		linearDetails = (LinearLayout) findViewById(R.id.weatherday);

	}

	private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

		@Override
		protected void onPreExecute() {

			super.onPreExecute();
			progressDialog = new ProgressDialog(OverViewActivity.this);
			progressDialog.setCancelable(false);
			progressDialog.setCanceledOnTouchOutside(false);
			progressDialog.setMessage("Loading...");
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progressDialog.setProgress(0);
			progressDialog.show();
		}

		@Override
		protected Weather doInBackground(String... params) {
			Weather weather = new Weather();

			String data = ((new WeatherHttpClient()).getWeatherData(params[0]));

			try {
				weather = JSONWeatherParser.getWeather(data);

				weather.iconData = ((new WeatherHttpClient())
						.getImage(weather.currentCondition.getIcon()));

			} catch (JSONException e) {
				e.printStackTrace();
			}
			return weather;

		}

		@Override
		protected void onPostExecute(Weather weather) {
			super.onPostExecute(weather);

			if (weather.iconData != null && weather.iconData.length > 0) {
				// byte[] b = Base64.decode(weather.iconData , Base64.DEFAULT);
				// BitmapFactory.Options opt = new BitmapFactory.Options();
				// opt.inDither = true;
				// opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
				
				//**Return null not decode**//
				// Bitmap img = BitmapFactory.decodeByteArray(weather.iconData,
				// 0,
				// weather.iconData.length);

				String icon = weather.currentCondition.getIcon();

				if (icon.equals("01d")) {

					icon_day.setImageDrawable(getResources().getDrawable(
							(R.drawable.sun)));
					icon_day.setVisibility(View.VISIBLE);

				} else if (icon.equals("01n")) {

					icon_day.setImageDrawable(getResources().getDrawable(
							(R.drawable.sky_clean_night)));
					icon_day.setVisibility(View.VISIBLE);

				} else if (icon.equals("02d")) {

					icon_day.setImageDrawable(getResources().getDrawable(
							(R.drawable.cloudy_morning)));
					icon_day.setVisibility(View.VISIBLE);

				} else if (icon.equals("03d") || icon.equals("03n")) {

					icon_day.setImageDrawable(getResources().getDrawable(
							(R.drawable.cloud)));
					icon_day.setVisibility(View.VISIBLE);

				} else if (icon.equals("04d") || icon.equals("04n")) {

					icon_day.setImageDrawable(getResources().getDrawable(
							(R.drawable.clouds)));
					icon_day.setVisibility(View.VISIBLE);

				} else if (icon.equals("09d") || icon.equals("09n")) {

					icon_day.setImageDrawable(getResources().getDrawable(
							(R.drawable.rain)));
					icon_day.setVisibility(View.VISIBLE);

				} else if (icon.equals("10d")) {

					icon_day.setImageDrawable(getResources().getDrawable(
							(R.drawable.rain_morning)));
					icon_day.setVisibility(View.VISIBLE);

				} else if (icon.equals("10n")) {

					icon_day.setImageDrawable(getResources().getDrawable(
							(R.drawable.rain_night)));
					icon_day.setVisibility(View.VISIBLE);

				} else if (icon.equals("11d") || icon.equals("11n")) {

					icon_day.setImageDrawable(getResources().getDrawable(
							(R.drawable.thunder)));
					icon_day.setVisibility(View.VISIBLE);

				} else if (icon.equals("13d") || icon.equals("13n")) {

					icon_day.setImageDrawable(getResources().getDrawable(
							(R.drawable.snow)));
					icon_day.setVisibility(View.VISIBLE);

				} else {

					icon_day.setImageDrawable(getResources().getDrawable(
							(R.drawable.mist)));
					icon_day.setVisibility(View.VISIBLE);

				}

			}

			condition = weather.currentCondition.getDescr();

			condition_day.setText(condition.toUpperCase());
			temp_day.setText(""
					+ Math.round((weather.temperature.getTemp() - 275.15))
					+ "°C");

			condition_day.setVisibility(View.VISIBLE);
			temp_day.setVisibility(View.VISIBLE);

			temp = String
					.valueOf(Math.round(weather.temperature.getTemp() - 275.15));
			temp_max = String.valueOf(Math.round(weather.temperature
					.getMaxTemp() - 275.15));
			temp_min = String.valueOf(Math.round(weather.temperature
					.getMinTemp() - 275.15));
			conditionDetails = weather.currentCondition.getDescr();
			humidity = String.valueOf(Math.round(weather.currentCondition
					.getHumidity()));
			pressure = String.valueOf(Math.round(weather.currentCondition
					.getPressure()));
			wind = String.valueOf(weather.wind.getSpeed());
			clouds = String.valueOf(weather.clouds.getPerc());

		}
	}

	private class JSONForecastWeatherTask extends
			AsyncTask<String, Void, WeatherForecast> {

		@Override
		protected WeatherForecast doInBackground(String... params) {

			String data = ((new WeatherHttpClient()).getForecastWeatherData(
					params[0], params[1]));
			WeatherForecast forecast = new WeatherForecast();

			try {
				forecast = JSONWeatherParser.getForecastWeather(data);

			} catch (JSONException e) {
				e.printStackTrace();
			}
			return forecast;

		}

		@Override
		protected void onPostExecute(WeatherForecast forecastWeather) {
			super.onPostExecute(forecastWeather);

			DayForecast weatherfirstday = (DayForecast) forecastWeather
					.getForecast(0);
			DayForecast weathersecondday = (DayForecast) forecastWeather
					.getForecast(1);
			DayForecast weatherthirdday = (DayForecast) forecastWeather
					.getForecast(2);
			DayForecast weatherfourthday = (DayForecast) forecastWeather
					.getForecast(3);
			DayForecast weatherfifthday = (DayForecast) forecastWeather
					.getForecast(4);

			max_firstday.setText(String.valueOf(Math
					.round(weatherfirstday.forecastTemp.max - 275.15)) + "°");
			min_firstday.setText(String.valueOf(Math
					.round(weatherfirstday.forecastTemp.min - 275.15)) + "°");

			max_secondday.setText(String.valueOf(Math
					.round(weathersecondday.forecastTemp.max - 275.15)) + "°");
			min_secondday.setText(String.valueOf(Math
					.round(weathersecondday.forecastTemp.min - 275.15)) + "°");

			max_thirdday.setText(String.valueOf(Math
					.round(weatherthirdday.forecastTemp.max - 275.15)) + "°");
			min_thirdday.setText(String.valueOf(Math
					.round(weatherthirdday.forecastTemp.min - 275.15)) + "°");

			max_fourthday.setText(String.valueOf(Math
					.round(weatherfourthday.forecastTemp.max - 275.15)) + "°");
			min_fourthday.setText(String.valueOf(Math
					.round(weatherfourthday.forecastTemp.min - 275.15)) + "°");

			max_fifthday.setText(String.valueOf(Math
					.round(weatherfifthday.forecastTemp.max - 275.15)) + "°");
			min_fifthday.setText(String.valueOf(Math
					.round(weatherfifthday.forecastTemp.min - 275.15)) + "°");

			max_firstday.setVisibility(View.VISIBLE);
			max_secondday.setVisibility(View.VISIBLE);
			max_thirdday.setVisibility(View.VISIBLE);
			max_fourthday.setVisibility(View.VISIBLE);
			max_fifthday.setVisibility(View.VISIBLE);

			min_firstday.setVisibility(View.VISIBLE);
			min_secondday.setVisibility(View.VISIBLE);
			min_thirdday.setVisibility(View.VISIBLE);
			min_fourthday.setVisibility(View.VISIBLE);
			min_fifthday.setVisibility(View.VISIBLE);

			progressDialog.dismiss();
		}

	}

}
