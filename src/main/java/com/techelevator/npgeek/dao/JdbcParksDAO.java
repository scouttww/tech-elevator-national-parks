package com.techelevator.npgeek.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.Park;

@Component
public class JdbcParksDAO implements ParksDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcParksDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);		
	}
	
	@Override
	public List<Park> getAllParksAlphabetically() {
		
		List<Park> parks = new ArrayList<Park>();
		
		String sqlQuery = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, "
				+ "numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, "
				+ "inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies  "
				+ "FROM park "
				+ "ORDER BY parkname";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sqlQuery);
		
		while (rows.next()) {
			Park park = mapRowToPark(rows);
			parks.add(park);
		}

		return parks;
	}

	@Override
	public Park getParkDetailsByParkCode(String parkCode) {
	
		Park park = null;
		
		String sqlQuery = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, "
				+ "numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, "
				+ "inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies  "
				+ "FROM park "
				+ "WHERE parkcode = ?";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sqlQuery, parkCode);
		
		while (rows.next()) {
			park = mapRowToPark(rows);
		}
		
		return park;
	}
	
	private Park mapRowToPark(SqlRowSet row) {
		Park park = new Park();
		park.setParkName(row.getString("parkName"));
		park.setParkCode(row.getString("parkCode"));
		park.setState(row.getString("state"));
		park.setAcreage(row.getInt("acreage")); // when we set the acreage, we also automatically set the km^2 of trail in the Java Bean Park.java
		park.setElevationInFeet(row.getInt("elevationInFeet")); // when we set the elevation in ft, we also automatically set the elevation in meters in the Java Bean Park.java
		park.setMilesOfTrail(row.getDouble("milesOfTrail")); // when we set the miles of trail, we also automatically set the kms of trail in the Java Bean Park.java
		park.setNumberOfCampsites(row.getInt("numberOfCampsites"));
		park.setClimate(row.getString("climate"));
		park.setYearFounded(row.getInt("yearFounded"));
		park.setAnnualVisitCount(row.getInt("annualVisitorCount"));
		park.setInspirationalQuote(row.getString("inspirationalQuote"));
		park.setInspirationalQuoteSource(row.getString("inspirationalQuoteSource"));
		park.setParkDescription(row.getString("parkDescription"));
		park.setEntryFee(row.getInt("entryFee"));
		park.setNumberOfAnimalSpecies(row.getInt("numberOfAnimalSpecies"));
		
		return park;
	}

}
