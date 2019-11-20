package com.techelevator.npgeek.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.FavoritePark;
import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.Weather;

@Component
public class JdbcSurveyDAO implements SurveyDAO {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcSurveyDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<FavoritePark> getAllFavoritedParks() {
		List<FavoritePark> surveyResultsList = new ArrayList<FavoritePark>();
		
		String sqlQuery = "SELECT park.parkname, park.parkcode, COUNT(*) AS num_surveys " + 
				"FROM survey_result " + 
				"JOIN park ON park.parkcode=survey_result.parkcode " + 
				"GROUP BY park.parkname, park.parkcode " + 
				"ORDER BY num_surveys DESC, park.parkname";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sqlQuery);

		while (rows.next()) {
			FavoritePark favoritePark = mapRowToFavoritePark(rows);
			surveyResultsList.add(favoritePark);
		}

		return surveyResultsList;
	}

	@Override
	public Survey addNewSurveyToDatabase(Survey submittedSurvey) {
		
		String insertSql = "INSERT INTO survey_result (surveyid, parkcode, "
				+ "emailaddress, state, activitylevel) "
				+ "VALUES (DEFAULT, ?, ?, ?, ?) "
				+ "RETURNING surveyid";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(insertSql,
				submittedSurvey.getParkCode(),
				submittedSurvey.getEmailAddress(),
				submittedSurvey.getState(),
				submittedSurvey.getActivityLevel());
		
		rows.next();
		Long surveyId = rows.getLong("surveyid");
		submittedSurvey.setSurveyId(surveyId);
		
		return submittedSurvey;
	}


	private FavoritePark mapRowToFavoritePark(SqlRowSet row) {
		FavoritePark favoritePark = new FavoritePark();
		favoritePark.setNumberOfSurveys(row.getInt("num_surveys"));
		favoritePark.setParkCode(row.getString("parkCode"));
		favoritePark.setParkName(row.getString("parkName"));
		
		return favoritePark;
	}
	
}
