package com.techelevator.npgeek.dao;

import java.util.List;

import com.techelevator.npgeek.model.*;

public interface ParksDAO {
	public List<Park> getAllParksAlphabetically();
	public Park getParkDetailsByParkCode(String parkCode);

}
