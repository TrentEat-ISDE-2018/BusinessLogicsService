package businesslayer.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import businesslayer.entities.Agritur;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface AgriturService {

	@WebMethod
	public Agritur getDetailedAgritur(String name);
	
	@WebMethod
	public List<Agritur> getNearAgritur(double distance, double lat, double lon);
}
