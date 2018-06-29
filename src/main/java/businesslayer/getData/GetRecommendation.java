package businesslayer.getData;

import java.util.ArrayList;
import java.util.List;

import com.recombee.api_client.RecombeeClient;
import com.recombee.api_client.api_requests.AddDetailView;
import com.recombee.api_client.api_requests.AddRating;
import com.recombee.api_client.api_requests.RecommendItemsToUser;
import com.recombee.api_client.bindings.RecommendationResponse;
import com.recombee.api_client.exceptions.ApiException;

import businesslayer.entities.Agritur;
import businesslayer.entities.input.AgriturEntity;

public class GetRecommendation {
	private static final String token = System.getenv("RECOMBEE_KEY");
	
	public static void addView(String userId, String agritur) {
		RecombeeClient client = new RecombeeClient("trenteat", token);
        try {
        	client.send(new AddDetailView(userId, agritur.replaceAll("[^A-Za-z0-9]", ""))
        			  .setCascadeCreate(true)//create user or item if not present
        			);
        } catch (ApiException e) {
            e.printStackTrace();
        }
	}
	
	public static void addMark(String userId, String agritur, double rating) {
		RecombeeClient client = new RecombeeClient("trenteat", token);
        try {
        	client.send(new AddRating(userId, agritur.replaceAll("[^A-Za-z0-9]", ""), rating)
        			  .setCascadeCreate(true)//create user or item if not present
        			);
        } catch (ApiException e) {
            e.printStackTrace();
        }
	}
	
	public static List<Agritur> getRecommendation(String userId) {
		RecombeeClient client = new RecombeeClient("trenteat", token);
		List<Agritur> output = new ArrayList<Agritur>();
		RecommendationResponse result = null;
        try {
        	result = client.send(new RecommendItemsToUser(userId, 5));
        	List<AgriturEntity> all = GetAgritur.getAll();
            for(AgriturEntity ae : all){
            	for(String id : result.getIds()) {
            		if(id.equals(ae.getName().replaceAll("[^A-Za-z0-9]", ""))) {
            			output.add(new Agritur(ae, GetWeather.getWeather(ae.getLat(), ae.getLon())));
            		}
            	}
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }       
        return output;
	}
	
	public static void main(String args[]) {
		//Adding random view in order to getting recommendation
		RecombeeClient client = new RecombeeClient("trenteat", token);
		List<AgriturEntity> all = GetAgritur.getAll();
		RecommendationResponse result = null;
        try {
        	for(int i = 0; i<100; i++) {
        		client.send(new AddDetailView(
        				"fake_user_"+i%7, 
        				all.get(
        						(int)(Math.random()*100)
        						).getName().replaceAll("[^A-Za-z0-9]", "")
        				).setCascadeCreate(true));
        	}
        	


      		result = client.send(new RecommendItemsToUser("fake_user", 5));
        	System.out.println(result.getIds().length);
        } catch (ApiException e) {
            e.printStackTrace();
        }
	}
}
