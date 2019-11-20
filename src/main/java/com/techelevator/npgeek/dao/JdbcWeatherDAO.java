package com.techelevator.npgeek.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.Weather;

@Component
public class JdbcWeatherDAO implements WeatherDAO {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcWeatherDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Weather> get5DayWeatherForecastByParkCode(String parkCode) {
		List<Weather> weatherList = new ArrayList<Weather>();

		String sqlQuery = "SELECT parkCode, fivedayforecastvalue, low, high, forecast "
				+ "FROM weather "
				+ "WHERE parkcode = ? "
				+ "ORDER BY fivedayforecastvalue ASC";

		SqlRowSet rows = jdbcTemplate.queryForRowSet(sqlQuery, parkCode);

		while (rows.next()) {
			Weather weather = mapRowToWeather(rows);
			weatherList.add(weather);
		}

		return weatherList;
	}

	private Weather mapRowToWeather(SqlRowSet row) {
		Weather weather = new Weather();
		weather.setParkCode(row.getString("parkCode"));
		weather.setFiveDayForecastValue(row.getInt("fiveDayForecastValue"));
		weather.setLowFahrenheit(row.getInt("low")); // in the Java Bean Weather.java, when we set the temps in *F, we automatically also set the temps in *C
		weather.setHighFahrenheit(row.getInt("high")); // this goes for both the low and high temperatures
		weather.setForecast(row.getString("forecast"));

		return weather;
	}

}
