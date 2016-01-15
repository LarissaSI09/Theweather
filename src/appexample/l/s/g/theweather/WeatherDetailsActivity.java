package appexample.l.s.g.theweather;

import com.example.theweather.R;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class WeatherDetailsActivity extends Activity {

	TextView temperature;
	TextView temperature_max;
	TextView temperature_min;
	TextView condition;
	TextView pressure;
	TextView humidity;
	TextView wind;
	TextView clouds;

	String temp;
	String temp_max;
	String temp_min;
	String conditionDetails;
	String humidityDetails;
	String pressureDetails;
	String windDetails;
	String cloudsDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		initView();

		getActionBar().setTitle("Detalhes do Clima");
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLUE));

		Bundle bundle = getIntent().getExtras();
		temp = bundle.getString("temp");
		temp_max = bundle.getString("tempmax");
		temp_min = bundle.getString("tempmin");
		conditionDetails = bundle.getString("condition");
		pressureDetails = bundle.getString("pressure");
		humidityDetails = bundle.getString("humidity");
		cloudsDetails = bundle.getString("clouds");
		windDetails = bundle.getString("wind");

		temperature.setText(temp + "°C");
		temperature_max.setText(temp_max + "°");
		temperature_min.setText(temp_min + "°");
		condition.setText(conditionDetails.toUpperCase());
		pressure.setText("" + pressureDetails + " hPa");
		humidity.setText("" + humidityDetails + " %");
		clouds.setText(cloudsDetails + " %");
		wind.setText(windDetails + " mps");

	}

	public void initView() {
		temperature = (TextView) findViewById(R.id.temp);
		temperature_max = (TextView) findViewById(R.id.temp_max);
		temperature_min = (TextView) findViewById(R.id.temp_min);
		condition = (TextView) findViewById(R.id.condition);
		pressure = (TextView) findViewById(R.id.pressure);
		humidity = (TextView) findViewById(R.id.humidity);
		clouds = (TextView) findViewById(R.id.clouds);
		wind = (TextView) findViewById(R.id.wind);

	}

}
