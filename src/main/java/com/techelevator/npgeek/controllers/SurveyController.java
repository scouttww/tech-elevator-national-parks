package com.techelevator.npgeek.controllers;

import javax.validation.Valid;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.dao.*;
import com.techelevator.npgeek.model.*;
import com.techelevator.npgeek.tools.States;

@SessionAttributes("isScientificNotation")
@Controller
public class SurveyController {
	@Autowired
	private ParksDAO parksDao;
	@Autowired
	private SurveyDAO surveyDao;
	
	@RequestMapping(path="/survey", method=RequestMethod.GET)
	public String showSurveyPage(ModelMap map) {
		
		if(! map.containsAttribute("survey")) {
			map.addAttribute("survey", new Survey());
		}
		
		List<Park> allParksList = parksDao.getAllParksAlphabetically();
		map.addAttribute("parksList", allParksList);
		
		String[] allStateNamesArray = States.getAllStateNamesArray();
		map.addAttribute("allStateNames",allStateNamesArray);
		
		return "survey"; //jsp file that has a form (action = "survey" method="POST")
	}


	@RequestMapping(path="/survey", method=RequestMethod.POST)
	public String submitSurveyPage (@Valid @ModelAttribute("survey") Survey survey,
			BindingResult result,
			RedirectAttributes flash) {
		
		// Validate appropriate values
		if(result.hasErrors()) {
			flash.addFlashAttribute("survey", survey);
            flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "survey", result);
            return "redirect:/survey";
        }
		
		surveyDao.addNewSurveyToDatabase(survey);
		
		return "redirect:/surveyResults"; //redirect
	}

	
	
	@RequestMapping(path="/surveyResults", method=RequestMethod.GET)
	public String showSurveyResultsPage(ModelMap map) {
		
		List<FavoritePark> surveyResults = surveyDao.getAllFavoritedParks();
		map.addAttribute("favoriteParksList", surveyResults);
		
		return "surveyResults"; //jsp file
	}
	
	
	
	
}
