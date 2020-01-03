package com.baf.musafir.phoenix.model;

public class CoordinateModel {

	private String latitude = "";
	private String longitude = "";
	private String places = "";

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPlaces() {
		return places;
	}

	public void setPlaces(String places) {
		this.places = places;
	}

	@Override
	public String toString() {
		return "CoordinateModel{" +
				"latitude='" + latitude + '\'' +
				", longitude='" + longitude + '\'' +
				", places='" + places + '\'' +
				'}';
	}
}
