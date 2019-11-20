package com.techelevator.npgeek.dao;

import java.util.List;

import com.techelevator.npgeek.model.*;

public interface SurveyDAO {
	public List<FavoritePark> getAllFavoritedParks();
	public Survey addNewSurveyToDatabase(Survey submittedSurvey);

}
