package businesslayer.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import businesslayer.entities.Agritur;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface AgriturService {

	@WebMethod
	public Agritur getDetailedAgritur(String name);
	
	@WebMethod
	public List<Agritur> getNearAgritur(double distance, double lat, double lon);
	
	@WebMethod
	public List<Agritur> getAgriturByQuery(String query);
	
	@WebMethod
	public void userMarkAgritur(String userId, String agritur, double mark);
	
	@WebMethod
	public void userViewAgritur(String userId, String agritur);
	
	@WebMethod
	public List<Agritur> recommendAgritur(String userId);
}
