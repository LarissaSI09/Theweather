package appexample.l.s.g.theweather;

import com.example.theweather.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import appexample.l.s.g.theweather.utils.Utils;

public class LocationActivity extends Activity {

	TextView cityView;
	TextView countryView;
	String city;
	String country;
	boolean connectionNet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);

		initViews();

		connectionNet = Utils.verifyNetwork(this);

		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLUE));

	}

	public void initViews() {
		cityView = (TextView) findViewById(R.id.city);
		countryView = (TextView) findViewById(R.id.country);
	}

	public void cleanFields(View view) {
		cityView.setText("");
		countryView.setText("");
	}

	public void sendData(View view) {

		city = cityView.getText().toString();
		country = countryView.getText().toString();

		if (!city.matches("") && !country.matches("")) {
			if (connectionNet == true) {

				Intent intent = new Intent(LocationActivity.this,
						OverViewActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("city", city);
				bundle.putString("country", country);
				intent.putExtras(bundle);
				startActivity(intent);
			} else {
				AlertDialog dialog = new AlertDialog.Builder(this)
						.setTitle("Internet Não Funciona!")
						.setMessage(
								"Este aplicativo necessita de internet. "
										+ "Por favor verifique sua conexão de rede.")
						.setNeutralButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.cancel();
									}
								}).show();
			}
		} else {
			Toast.makeText(this, "Preencha os campos por favor",
					Toast.LENGTH_LONG).show();
		}

	}

}
