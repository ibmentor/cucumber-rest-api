package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
public AddPlace addPlacePayLoad(String name , String address ,String languages ) {
/************************
 JSon Payload	
	Body:
	{
	    "accuracy": 50,
	    "name": "Akshay Gaikwad",
	    "phone_number": "(+91) 1231231234",
	    "address": "PUNE",
	    "website": "https://akshayGaikwad.com",
	    "language": "Marathi",
	    "location": {
	        "lat": 94.0,
	        "lng": 322.0,
	        "png": 76.0
	    },
	    "types": [
	        "webdriverIo",
	        "selenium",
	    ]
	}
	**********/
	AddPlace add = new AddPlace(); 
	add.setAccuracy(50);
	add.setAddress(address);
	add.setLanguage(languages);
	add.setPhone_number("(+91) 1231231234");
	add.setWebsite("https://akshayGaikwad.com");
	add.setName(name);

	List<String> list = new ArrayList<String>();
	list.add("webdriverIo");
	list.add("selenium");
	list.add("playwright");
	add.setTypes(list);

	Location loc = new Location();
	loc.setLat(94);
	loc.setLng(322);
	loc.setPng(76);

	add.setLocation(loc);
	return add;
}
public String deletePlacePayload(String placeId)
{
	return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
}
}
