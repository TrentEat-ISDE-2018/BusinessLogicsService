package businesslayer.getData;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import businesslayer.entities.input.MapsResponse;

public class GetLocation {
	public static MapsResponse getLocation(String place) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget 
		  = client.target("http://datalayer.herokuapp.com/geocoding/");
		
		WebTarget weatherWebTarget = null;
		try {
			weatherWebTarget = webTarget.path(System.getenv("MAPS_KEY")+"/"+URLEncoder.encode(place, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Invocation.Builder invocationBuilder 
		  = weatherWebTarget.request(MediaType.APPLICATION_JSON);
		
		return invocationBuilder.get(MapsResponse.class);
	}
	
	public static void main(String args[]) {
		MapsResponse mr = GetLocation.getLocation("Via Sommarive 5, Povo");
		System.out.println(mr.getLat());
		System.out.println(mr.getLon());
	}
}
