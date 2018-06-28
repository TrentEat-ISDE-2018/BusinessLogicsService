package businesslayer.ws;

import javax.jws.WebService;

import businesslayer.entities.Agritur;
import businesslayer.entities.input.AgriturEntity;
import businesslayer.entities.input.WeatherResponse;
import businesslayer.getData.GetAgritur;
import businesslayer.getData.GetWeather;

@WebService(endpointInterface = "businesslayer.ws.AgriturService")
public class AgriturServiceImpl implements AgriturService{

	public Agritur getDetailedAgritur(String name) {
		AgriturEntity ae = GetAgritur.getAgritur(name);
		WeatherResponse wr = GetWeather.getWeather(ae.getLat(), ae.getLon());
		
		Agritur detailed = new Agritur(ae, wr);
		return detailed;
	}

}
