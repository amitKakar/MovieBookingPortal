package com.example.springexample.theatre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springexample.global.Globals;
import com.example.springexample.operator.Operator;
import com.example.springexample.operator.OperatorDAO;
import com.example.springexample.screen.Screen;
import com.example.springexample.screen.ScreenDAO;



@Controller
public class MainTheatreController {
	
	@Autowired
	TheatreDAO theatreDAO;
	
	@Autowired
	ScreenDAO screenDAO;
	
	@Autowired
	OperatorDAO operatorDAO;
	
	
	
	@GetMapping("/theatre")
	public String theatre(Model model)
	{
		model.addAttribute("theatreForm", new MainTheatre());
		return "Theatre";
	}
	
	@PostMapping("/theatreDisplay")
	public String displayTheatreJson(MainTheatre mainTheatre, Model model, HttpServletRequest request ) throws JSONException
	{
		HttpSession httpSession = request.getSession();
		String operatorName = (String)httpSession.getAttribute("operatorname");
		
		Operator operator = operatorDAO.getOperatorByOperatorName(operatorName);
		 
		mainTheatre.setOperatorId(operator.getOperatorId());
		
		
		Theatre theatre = setTheatreTable(mainTheatre);
		setScreenTable(mainTheatre);
		System.out.println("Test");
		
		model.addAttribute("theatreId", theatre.getTheatreId());
		model.addAttribute("theatre", mainTheatre);
		
		return "theatreSuccess";
	}
	
	
	public JSONObject createScreenJson(String jsonData) throws JSONException
	{
		
		ArrayList<String> screenTokens = new ArrayList<>();
		
		
		StringTokenizer token = new StringTokenizer(jsonData,",");
		while(token.hasMoreTokens())
		{
			screenTokens.add(token.nextToken());
		}

		JSONObject screenNameJson = new JSONObject();
		JSONObject numberOfSeatsJson = new JSONObject();
		
		for(int i=0; i<screenTokens.size(); i++)
		{
			String screen = screenTokens.get(i);
			StringTokenizer temp = new StringTokenizer(screen,":");
			
			while(temp.hasMoreTokens())
			{
				screenNameJson.put("screen"+(i+1), temp.nextToken());
				numberOfSeatsJson.put("screen"+(i+1), temp.nextToken());
			}
		}
		
		JSONObject screenJson = new JSONObject();
		screenJson.put("screenName", screenNameJson);
		screenJson.put("numberOfSeats", numberOfSeatsJson);
		
		return screenJson;
	}
	
	public Theatre setTheatreTable(MainTheatre mainTheatre)
	{
		Theatre theatre = new Theatre();
		theatre.setCity(mainTheatre.getCity());
		theatre.setNumberOfScreens(mainTheatre.getNumberOfScreens());
		theatre.setOperatorId(mainTheatre.getOperatorId());
		
		String upperCaseTheatreName = mainTheatre.getName().toUpperCase();
		String upperCaseAddress = mainTheatre.getAddress().toUpperCase();
		
		theatre.setName(upperCaseTheatreName);
		theatre.setAddress(upperCaseAddress);

		theatreDAO.addTheatre(theatre);
		
		return theatre; 
		
	}
	
	public void setScreenTable(MainTheatre mainTheatre) throws JSONException
	{
		
		HashMap<String, String> map=new HashMap<>();
		map.put(mainTheatre.getName().toUpperCase(), mainTheatre.getAddress().toUpperCase());
		//missing theatreMap put
		int theatreId = Globals.theatreMap.get(map);
		
		
		JSONObject screenJson = createScreenJson(mainTheatre.getJsonData());
		screenJson.put("theatreId", theatreId);
		
		String screenJsonString = screenJson.toString();
		
		Screen screen = new Screen();
		screen.setJsonData(screenJsonString);
		
		screenDAO.addScreen(screen);
		
	}
}
