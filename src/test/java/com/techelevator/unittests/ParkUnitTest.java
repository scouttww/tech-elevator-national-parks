package com.techelevator.unittests;

import org.junit.*;

import com.techelevator.npgeek.model.*;

public class ParkUnitTest {
	private Park park;

	@Before
	public void setup() {
		park = new Park();
	}
	
	@Test
	public void set_miles_of_trail_sets_kms_of_trail() {
		park.setMilesOfTrail(1);
		Assert.assertNotNull(park.getKilometersOfTrail()); // the actual conversion is tested in the unit converter test
	}

	@Test
	public void set_acreage_sets_sq_kms() {
		park.setAcreage(1);
		Assert.assertNotNull(park.getSqKilometers()); // the actual conversion is tested in the unit converter test
	}

	@Test
	public void set_elevation_in_feet_sets_elevation_in_meters() {
		park.setElevationInFeet(1);
		Assert.assertNotNull(park.getElevationInMeters()); // the actual conversion is tested in the unit converter test
	}
}
