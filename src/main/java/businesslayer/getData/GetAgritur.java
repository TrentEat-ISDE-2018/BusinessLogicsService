package businesslayer.getData;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import businesslayer.entities.input.AgriturEntity;
import businesslayer.entities.input.MapsResponse;

public class GetAgritur {
	public static AgriturEntity getAgritur(String title) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget 
		  = client.target("http://datalayer.herokuapp.com/agritur/");
		
		WebTarget weatherWebTarget = webTarget.path(title);
		
		Invocation.Builder invocationBuilder 
		  = weatherWebTarget.request(MediaType.APPLICATION_JSON);
		
		return invocationBuilder.get(AgriturEntity.class);
	}
	
	public static List<AgriturEntity> getAll() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget 
		  = client.target("http://datalayer.herokuapp.com/agritur/");
		
		WebTarget weatherWebTarget = webTarget.path("all");
		
		Invocation.Builder invocationBuilder 
		  = weatherWebTarget.request(MediaType.APPLICATION_JSON);
		
		//https://stackoverflow.com/questions/35313767/how-to-get-liststring-as-response-from-jersey2-client
		return invocationBuilder.get().readEntity(new GenericType<List<AgriturEntity>>() {});
	}
	
	public static void main(String args[]) {
		AgriturEntity ae = GetAgritur.getAgritur("SOCIETA' AGRICOLA MASO DELLO SPECK");
		System.out.println(ae.getAddress());
		
		List<AgriturEntity> list = GetAgritur.getAll();
		System.out.println(list.size());
	}
}
