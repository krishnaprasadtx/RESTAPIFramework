package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Addplace;
import pojo.Location;

public class TestDataBuild {
	
	public Addplace addPlacePayLoad(String name, String language, String address) {
	
	Addplace p = new Addplace();
	p.setAccuracy(50);
	p.setAddress(address);
	p.setLanguage(language);
	p.setWebsite("https://google.com");
	p.setName(name);
	p.setPhone_number("(+91) 983 893 3937");
	List<String> myList = new ArrayList<String>();
	myList.add("shoe park");
	myList.add("shop");
	p.setTypes(myList);
	Location l = new Location();
	l.setLat(-38.383494);
	l.setLng(33.427362);
	p.setLocation(l);
	
	return p;
	
	}

	public String deletePlacePayLoad(String place_Id)
	{
		return "{\r\n"
				+ "\r\n"
				+ "    \"place_id\":\""+place_Id+"\"\r\n"
				+ "}\r\n"
				+ "";
	}
	

}
