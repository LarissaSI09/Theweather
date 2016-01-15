package appexample.l.s.g.theweather.model;

public class DayForecast {

	public Weather weather = new Weather();
	public ForecastTemp forecastTemp = new ForecastTemp();

	public class ForecastTemp {
		public float day;
		public float min;
		public float max;
		public float night;
		public float evening;
		public float morning;
	}

}
