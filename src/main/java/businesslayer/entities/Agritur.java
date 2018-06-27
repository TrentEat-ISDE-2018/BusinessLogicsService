package businesslayer.entities;

import java.io.Serializable;

public class Agritur implements Serializable {
	//Agritur data
	private String phone;
	private String email;
	private String website;
	private Integer altitude;
	private String address;
	private Double lat;
	private Double lon;
	private String name; //ID	
	private Integer num_for_eat;
	private Integer num_for_sleep;
	//Weather data
	private String main;
	private String description;
	private Double temp;
	private Double temp_min;
	private Double temp_max;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Integer getAltitude() {
		return altitude;
	}
	public void setAltitude(Integer altitude) {
		this.altitude = altitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNum_for_eat() {
		return num_for_eat;
	}
	public void setNum_for_eat(Integer num_for_eat) {
		this.num_for_eat = num_for_eat;
	}
	public Integer getNum_for_sleep() {
		return num_for_sleep;
	}
	public void setNum_for_sleep(Integer num_for_sleep) {
		this.num_for_sleep = num_for_sleep;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	//Weather
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getTemp() {
		return temp;
	}
	public void setTemp(Double temp) {
		this.temp = temp;
	}
	public Double getTemp_min() {
		return temp_min;
	}
	public void setTemp_min(Double temp_min) {
		this.temp_min = temp_min;
	}
	public Double getTemp_max() {
		return temp_max;
	}
	public void setTemp_max(Double temp_max) {
		this.temp_max = temp_max;
	}
}

