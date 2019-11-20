package com.techelevator.npgeek.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.gargoylesoftware.htmlunit.javascript.host.Map;
import com.techelevator.npgeek.dao.*;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.Weather;
import com.techelevator.npgeek.model.WeatherAdvisory;

@Controller
@SessionAttributes("isScientificNotation")
public class ParksController {

	@Autowired
	private ParksDAO parksDao;
	@Autowired
	private WeatherDAO weatherDao;
	

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String redirectToHomePage() {
				
		return "redirect:/home"; // redirect
	}

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public String showHomePage(ModelMap map) {
		List<Park> allParksList = parksDao.getAllParksAlphabetically();
		map.addAttribute("parksList", allParksList);

		return "homePage"; // jsp
	}

	@RequestMapping(path = "/park", method = RequestMethod.GET)
	public String showParkDetailsPage(ModelMap map, 
			@RequestParam(name="parkName", required=false) String parkName, 
			@RequestParam(name="parkCode") String parkCode) {
		
		setInitialUnitNotationIfApplicable(map);
		
		Park park = parksDao.getParkDetailsByParkCode(parkCode);
		boolean validPark = true;
		if(park == null || park.getParkName() == null) {
			validPark = false;
		}
		
		List<Weather> forecastList = weatherDao.get5DayWeatherForecastByParkCode(parkCode);
		List<String> advisoryList = new WeatherAdvisory(forecastList.get(0)).getAdvisories();
		WeatherAdvisory advisory = new WeatherAdvisory(forecastList.get(0));
		map.addAttribute("park", park);
		map.addAttribute("weatherForecasts", forecastList);
		map.addAttribute("validPark", validPark);
		map.addAttribute("advisoryList", advisoryList);
		
		return "parkDetails"; // jsp
	}
	
	@RequestMapping(path = "/park", method = RequestMethod.POST)
	public String changeTempUnitScale(ModelMap map, 
			@RequestParam(name="isScientificNotation") boolean scientificNotation, 
			@RequestParam(name="parkName") String parkName, 
			@RequestParam(name="parkCode") String parkCode) {
		
		map.addAttribute("isScientificNotation", scientificNotation);
		map.addAttribute("parkCode", parkCode);
		map.addAttribute("parkName", parkName);
		
		return "redirect:/park"; // jsp
	}
	
	private void setInitialUnitNotationIfApplicable(ModelMap map) {
		if(!map.containsAttribute("isScientificNotation")) {
			map.addAttribute("isScientificNotation", false);
		}
	}

	
	@RequestMapping(path = "/parkChangeUnits", method = RequestMethod.GET)
	public String changeTempUnitScaleGet(ModelMap map, 
			@RequestParam(name="isScientificNotation") boolean scientificNotation, 
			@RequestParam(name="parkName") String parkName, 
			@RequestParam(name="parkCode") String parkCode) {
		
		map.addAttribute("isScientificNotation", scientificNotation);
		map.addAttribute("parkCode", parkCode);
		map.addAttribute("parkName", parkName);
		
		return "redirect:/park"; // jsp
	}

}
