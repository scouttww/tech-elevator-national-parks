package com.techelevator.npgeek.model;

import java.util.*;

public class WeatherAdvisory {
	private final String HEAT_ADVISORY = "Phew... It's going to be hot out! Be sure to bring an extra gallon of water.";
	private final String COLD_ADVISORY = "Brrr... It's going to be chilly. Be careful when exposed to frigid temperatures";
	private final String TEMP_DIFF_ADVISORY = "The temperature will change a lot. Make sure to wear breathable layers";
	
	private final String SNOW_ADVISORY = "It's gonna snow!! You better pack some snowshoes!";
	private final String THUNDER_ADVISORY = "It might storm. You should stay inside or seek shelter and avoid hiking on exposed ridges!";
	private final String RAIN_ADVISORY = "It might rain. You should probably pack rain gear and wear waterproof shoes!";
	private final String SUNNY_ADVISORY = "It's gonna be a sunny day! You better pack some sunblock and a hat!";
	private List<String> advisories;
	
	public WeatherAdvisory() {
		
	}
	
	public WeatherAdvisory(Weather weather) {
		setAdvisories(weather);
	}

	public List<String> getAdvisories() {
		return advisories;
	}

	public void setAdvisories(List<String> advisories) {
		this.advisories = advisories;
	}

	public void setAdvisories(Weather weather) {
		int lowF = weather.getLowFahrenheit();
		int highF = weather.getHighFahrenheit();
		String forecast = weather.getForecast();
		
		List<String> advisories = new ArrayList<String>();
		
		if(forecast.equalsIgnoreCase("snow")) {
			advisories.add(this.SNOW_ADVISORY);
		} else if(forecast.equalsIgnoreCase("thunderstorms")) {
			advisories.add(this.THUNDER_ADVISORY);
		} else if(forecast.equalsIgnoreCase("rain")) {
			advisories.add(this.RAIN_ADVISORY);
		} else if(forecast.equalsIgnoreCase("sunny")) {
			advisories.add(this.SUNNY_ADVISORY);
		}		
		
		if(lowF < 20) {
			advisories.add(this.COLD_ADVISORY);
		}
		
		if(highF > 75) {
			advisories.add(this.HEAT_ADVISORY);
		}
		
		if(highF-lowF > 20) {
			advisories.add(this.TEMP_DIFF_ADVISORY);
		}
		this.advisories = advisories;
	}

}
