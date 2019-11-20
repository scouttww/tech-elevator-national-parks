package com.techelevator.npgeek.model;

import com.techelevator.npgeek.tools.UnitConverter;

public class Park {
	private String parkCode;
	private String parkName;
	private String state;
	private int acreage;
	private int sqKilometers;
	private int elevationInFeet;
	private int elevationInMeters;
	private double milesOfTrail;
	private double kilometersOfTrail;
	private int numberOfCampsites;
	private String climate;
	private int yearFounded;
	private int annualVisitCount;
	private String inspirationalQuote;
	private String inspirationalQuoteSource;
	private String parkDescription;
	private int entryFee;
	private int numberOfAnimalSpecies;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + acreage;
		result = prime * result + annualVisitCount;
		result = prime * result + ((climate == null) ? 0 : climate.hashCode());
		result = prime * result + elevationInFeet;
		result = prime * result + elevationInMeters;
		result = prime * result + entryFee;
		result = prime * result + ((inspirationalQuote == null) ? 0 : inspirationalQuote.hashCode());
		result = prime * result + ((inspirationalQuoteSource == null) ? 0 : inspirationalQuoteSource.hashCode());
		long temp;
		temp = Double.doubleToLongBits(kilometersOfTrail);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(milesOfTrail);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + numberOfAnimalSpecies;
		result = prime * result + numberOfCampsites;
		result = prime * result + ((parkCode == null) ? 0 : parkCode.hashCode());
		result = prime * result + ((parkDescription == null) ? 0 : parkDescription.hashCode());
		result = prime * result + ((parkName == null) ? 0 : parkName.hashCode());
		result = prime * result + sqKilometers;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + yearFounded;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Park other = (Park) obj;
		if (acreage != other.acreage)
			return false;
		if (annualVisitCount != other.annualVisitCount)
			return false;
		if (climate == null) {
			if (other.climate != null)
				return false;
		} else if (!climate.equals(other.climate))
			return false;
		if (elevationInFeet != other.elevationInFeet)
			return false;
		if (elevationInMeters != other.elevationInMeters)
			return false;
		if (entryFee != other.entryFee)
			return false;
		if (inspirationalQuote == null) {
			if (other.inspirationalQuote != null)
				return false;
		} else if (!inspirationalQuote.equals(other.inspirationalQuote))
			return false;
		if (inspirationalQuoteSource == null) {
			if (other.inspirationalQuoteSource != null)
				return false;
		} else if (!inspirationalQuoteSource.equals(other.inspirationalQuoteSource))
			return false;
		if (Double.doubleToLongBits(kilometersOfTrail) != Double.doubleToLongBits(other.kilometersOfTrail))
			return false;
		if (Double.doubleToLongBits(milesOfTrail) != Double.doubleToLongBits(other.milesOfTrail))
			return false;
		if (numberOfAnimalSpecies != other.numberOfAnimalSpecies)
			return false;
		if (numberOfCampsites != other.numberOfCampsites)
			return false;
		if (parkCode == null) {
			if (other.parkCode != null)
				return false;
		} else if (!parkCode.equals(other.parkCode))
			return false;
		if (parkDescription == null) {
			if (other.parkDescription != null)
				return false;
		} else if (!parkDescription.equals(other.parkDescription))
			return false;
		if (parkName == null) {
			if (other.parkName != null)
				return false;
		} else if (!parkName.equals(other.parkName))
			return false;
		if (sqKilometers != other.sqKilometers)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (yearFounded != other.yearFounded)
			return false;
		return true;
	}
	public String getParkCode() {
		return parkCode;
	}
	public String getParkName() {
		return parkName;
	}
	public String getState() {
		return state;
	}
	public int getAcreage() {
		return acreage;
	}
	public int getSqKilometers() {
		return sqKilometers;
	}
	public int getElevationInFeet() {
		return elevationInFeet;
	}
	public int getElevationInMeters() {
		return elevationInMeters;
	}
	public double getMilesOfTrail() {
		return milesOfTrail;
	}
	public double getKilometersOfTrail() {
		return kilometersOfTrail;
	}
	public int getNumberOfCampsites() {
		return numberOfCampsites;
	}
	public String getClimate() {
		return climate;
	}
	public int getYearFounded() {
		return yearFounded;
	}
	public int getAnnualVisitCount() {
		return annualVisitCount;
	}
	public String getInspirationalQuote() {
		return inspirationalQuote;
	}
	public String getInspirationalQuoteSource() {
		return inspirationalQuoteSource;
	}
	public String getParkDescription() {
		return parkDescription;
	}
	public int getEntryFee() {
		return entryFee;
	}
	public int getNumberOfAnimalSpecies() {
		return numberOfAnimalSpecies;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	// *********************
	// When setting the acreage, elevationInFeet, and milesOfTrail, we must also calculate and set the
	// corresponding values for sqKilometers, elevationInMeters, and kilometersOfTrail
	// These values are dependent on the other values
	public void setAcreage(int acreage) {
		this.acreage = acreage;
		setSqKilometers();
	}
	public void setSqKilometers() {
		this.sqKilometers = (int) UnitConverter.convertAcresToSqKilometers(acreage);
	}
	public void setElevationInFeet(int elevationInFeet) {
		this.elevationInFeet = elevationInFeet;
		setElevationInMeters();
	}
	public void setElevationInMeters() {
		this.elevationInMeters = (int) UnitConverter.convertFeetToMeters(elevationInFeet);
	}
	public void setMilesOfTrail(double milesOfTrail) {
		this.milesOfTrail = milesOfTrail;
		setKilometersOfTrail();
	}
	public void setKilometersOfTrail() {
		this.kilometersOfTrail = UnitConverter.convertMilesToKilometers(milesOfTrail);
	}
	// *********************

	public void setNumberOfCampsites(int numberOfCampsites) {
		this.numberOfCampsites = numberOfCampsites;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}
	public void setYearFounded(int yearFounded) {
		this.yearFounded = yearFounded;
	}
	public void setAnnualVisitCount(int annualVisitCount) {
		this.annualVisitCount = annualVisitCount;
	}
	public void setInspirationalQuote(String inspirationalQuote) {
		this.inspirationalQuote = inspirationalQuote;
	}
	public void setInspirationalQuoteSource(String inspirationalQuoteSource) {
		this.inspirationalQuoteSource = inspirationalQuoteSource;
	}
	public void setParkDescription(String parkDescription) {
		this.parkDescription = parkDescription;
	}
	public void setEntryFee(int entryFee) {
		this.entryFee = entryFee;
	}
	public void setNumberOfAnimalSpecies(int numberOfAnimalSpecies) {
		this.numberOfAnimalSpecies = numberOfAnimalSpecies;
	}
}
