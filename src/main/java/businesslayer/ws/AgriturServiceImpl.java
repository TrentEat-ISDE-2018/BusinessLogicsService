package businesslayer.ws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import businesslayer.entities.Agritur;
import businesslayer.entities.input.AgriturEntity;
import businesslayer.entities.input.WeatherResponse;
import businesslayer.getData.GetAgritur;
import businesslayer.getData.GetRecommendation;
import businesslayer.getData.GetWeather;

@WebService(endpointInterface = "businesslayer.ws.AgriturService")
public class AgriturServiceImpl implements AgriturService{

	public Agritur getDetailedAgritur(String name) {
		AgriturEntity ae = GetAgritur.getAgritur(name);
		WeatherResponse wr = GetWeather.getWeather(ae.getLat(), ae.getLon());
		
		Agritur detailed = new Agritur(ae, wr);
		return detailed;
	}
	
	public List<Agritur> getNearAgritur(double distance, double lat, double lon) {
		List<AgriturEntity> all = GetAgritur.getAll();		
		List<Agritur> near = new ArrayList<Agritur>();		
		for(AgriturEntity ae : all) {
			if(distance > geoDistance(lat, lon, ae.getLat(), ae.getLon())) {
				near.add(
						new Agritur(
								ae, 
								new WeatherResponse()
								)
						);
			}
		}
		return near;
	}
	
	private static Double geoDistance(Double lat1, Double lon1, Double lat2, Double lon2) {	
		//Haversine
		final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c; // result in kilometers
	    
	    return distance;
	}

	public List<Agritur> getAgriturByQuery(String query) {
		List<AgriturEntity> all = GetAgritur.getAll();
		List<Agritur> matched = new ArrayList<Agritur>();		
		for(AgriturEntity ae : all) {
			if(ae.getName().contains(query.toUpperCase())) {
				matched.add(
						new Agritur(
								ae, 
								new WeatherResponse()
								)
						);
			}
		}
		return matched;
	}

	public void userMarkAgritur(String userId, String agritur, double mark) {
		GetRecommendation.addMark(userId, agritur, mark);
		
	}

	public void userViewAgritur(String userId, String agritur) {
		GetRecommendation.addView(userId, agritur);
	}

	public List<Agritur> recommendAgritur(String userId) {
		return GetRecommendation.getRecommendation(userId);
	}

}
