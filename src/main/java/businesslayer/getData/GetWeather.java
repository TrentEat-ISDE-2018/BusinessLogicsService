package businesslayer.getData;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import businesslayer.entities.input.WeatherResponse;

public class GetWeather {
	public static WeatherResponse getWeather(double lat, double lon) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget 
		  = client.target("http://datalayer.herokuapp.com/weather/");
		
		WebTarget weatherWebTarget 
		  = webTarget.path(System.getenv("OPENWEATHERMAP_KEY")+"/"+Double.toString(lat)+"/"+Double.toString(lon));
		
		Invocation.Builder invocationBuilder 
		  = weatherWebTarget.request(MediaType.APPLICATION_JSON);
		
		return invocationBuilder.get(WeatherResponse.class);
	}
	
	public static void main(String args[]) {
		WeatherResponse wr = GetWeather.getWeather(46.0747793, 11.1217486);
		System.out.println(wr.getId());
	}
}
