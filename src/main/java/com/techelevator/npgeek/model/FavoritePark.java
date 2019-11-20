package com.techelevator.npgeek.model;

public class FavoritePark {
	private String parkName;
	private String parkCode;
	private int numberOfSurveys;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numberOfSurveys;
		result = prime * result + ((parkCode == null) ? 0 : parkCode.hashCode());
		result = prime * result + ((parkName == null) ? 0 : parkName.hashCode());
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
		FavoritePark other = (FavoritePark) obj;
		if (numberOfSurveys != other.numberOfSurveys)
			return false;
		if (parkCode == null) {
			if (other.parkCode != null)
				return false;
		} else if (!parkCode.equals(other.parkCode))
			return false;
		if (parkName == null) {
			if (other.parkName != null)
				return false;
		} else if (!parkName.equals(other.parkName))
			return false;
		return true;
	}
	public String getParkName() {
		return parkName;
	}
	public String getParkCode() {
		return parkCode;
	}
	public int getNumberOfSurveys() {
		return numberOfSurveys;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public void setNumberOfSurveys(int numberOfSurveys) {
		this.numberOfSurveys = numberOfSurveys;
	}
	
	

}
