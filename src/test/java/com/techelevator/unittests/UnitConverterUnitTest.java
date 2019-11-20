package com.techelevator.unittests;

import org.junit.Test;

import com.techelevator.npgeek.tools.UnitConverter;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class UnitConverterUnitTest {
	private double floatingPointMargin = 0.01;
	
	@Test
	public void test_temp_conversion_32_F_returns_0_C() {
		Assert.assertEquals(UnitConverter.convertFahrenheitToCelsius(32.0), 0.0, floatingPointMargin);
	}

	@Test
	public void test_temp_conversion_212_F_returns_100_C() {
		Assert.assertEquals(UnitConverter.convertFahrenheitToCelsius(212.0), 100.0, floatingPointMargin);
	}

	@Test
	public void test_temp_conversion_minus_40_F_returns_minus_40_C() {
		Assert.assertEquals(UnitConverter.convertFahrenheitToCelsius(-40.0), -40.0, floatingPointMargin);
	}

	@Test
	public void test_area_conversion_1000_acres_returns_4_sq_kms() {
		Assert.assertEquals((int) UnitConverter.convertAcresToSqKilometers(1000), 4, floatingPointMargin);
	}

	@Test
	public void test_area_conversion_1000000_acres_returns_4046_sq_kms() {
		Assert.assertEquals((int) UnitConverter.convertAcresToSqKilometers(1000000), 4046, floatingPointMargin);
	}

	@Test
	public void test_area_conversion_25000_acres_returns_101_sq_kms() {
		Assert.assertEquals((int) UnitConverter.convertAcresToSqKilometers(25000), 101, floatingPointMargin);
	}

	@Test
	public void test_distance_conversion_20_ft_to_6_meters() {
		Assert.assertEquals((int) UnitConverter.convertFeetToMeters(20), 6, floatingPointMargin);
	}

	@Test
	public void test_distance_conversion_1000_ft_to_304_meters() {
		Assert.assertEquals((int) UnitConverter.convertFeetToMeters(1000), 304, floatingPointMargin);
	}

	@Test
	public void test_distance_conversion_5280_ft_to_1609_meters() {
		Assert.assertEquals((int) UnitConverter.convertFeetToMeters(5280), 1609, floatingPointMargin);
	}

	@Test
	public void test_distance_conversion_1_mile_to_1_60934_kilometers() {
		Assert.assertEquals(UnitConverter.convertMilesToKilometers(1), 1.60934, floatingPointMargin);
	}

	@Test
	public void test_distance_conversion_10_25_miles_to_16_495776_kilometers() {
		Assert.assertEquals(UnitConverter.convertMilesToKilometers(10.25), 16.495776, floatingPointMargin);
	}

	@Test
	public void test_distance_conversion_62_miles_to_99_7793_kilometers() {
		Assert.assertEquals(UnitConverter.convertMilesToKilometers(62), 99.7793, floatingPointMargin);
	}

}
