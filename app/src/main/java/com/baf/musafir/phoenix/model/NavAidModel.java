package com.baf.musafir.phoenix.model;

public class NavAidModel {

	private String type = "";
	private String ident = "";
	private String freq = "";
	private String op_hrs = "";
	private String latitude = "";
	private String longitude = "";
	private String remarks = "";
	private String airport = "";
	private String type_code = "";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public String getFreq() {
		return freq;
	}

	public void setFreq(String freq) {
		this.freq = freq;
	}

	public String getOp_hrs() {
		return op_hrs;
	}

	public void setOp_hrs(String op_hrs) {
		this.op_hrs = op_hrs;
	}

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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAirport() {
		return airport;
	}

	public void setAirport(String airport) {
		this.airport = airport;
	}

	public String getType_code() {
		return type_code;
	}

	public void setType_code(String type_code) {
		this.type_code = type_code;
	}

	@Override
	public String toString() {
		return "NavAidModel{" +
				"type='" + type + '\'' +
				", ident='" + ident + '\'' +
				", freq='" + freq + '\'' +
				", op_hrs='" + op_hrs + '\'' +
				", latitude='" + latitude + '\'' +
				", longitude='" + longitude + '\'' +
				", remarks='" + remarks + '\'' +
				", airport='" + airport + '\'' +
				", type_code='" + type_code + '\'' +
				'}';
	}
}
