package com.baf.musafir.phoenix.model;

public class FlgHourModel {

	private String year = "";
	private String month = "";
	private String date = "";
	private String ac_type = "";
	private String ac_serno = "";
	private String first_pilot = "";
	private String second_pilot = "";
	private String day_hour = "";
	private String night_hour = "";
	private String instr_actual = "";
	private String inst_simulator = "";
	private String remarks = "";

	public String getD_n_flag() {
		return d_n_flag;
	}

	public void setD_n_flag(String d_n_flag) {
		this.d_n_flag = d_n_flag;
	}

	private String d_n_flag = "";

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAc_type() {
		return ac_type;
	}

	public void setAc_type(String ac_type) {
		this.ac_type = ac_type;
	}

	public String getAc_serno() {
		return ac_serno;
	}

	public void setAc_serno(String ac_serno) {
		this.ac_serno = ac_serno;
	}

	public String getFirst_pilot() {
		return first_pilot;
	}

	public void setFirst_pilot(String first_pilot) {
		this.first_pilot = first_pilot;
	}

	public String getSecond_pilot() {
		return second_pilot;
	}

	public void setSecond_pilot(String second_pilot) {
		this.second_pilot = second_pilot;
	}

	public String getDay_hour() {
		return day_hour;
	}

	public void setDay_hour(String day_hour) {
		this.day_hour = day_hour;
	}

	public String getNight_hour() {
		return night_hour;
	}

	public void setNight_hour(String night_hour) {
		this.night_hour = night_hour;
	}

	public String getInstr_actual() {
		return instr_actual;
	}

	public void setInstr_actual(String instr_actual) {
		this.instr_actual = instr_actual;
	}

	public String getInst_simulator() {
		return inst_simulator;
	}

	public void setInst_simulator(String inst_simulator) {
		this.inst_simulator = inst_simulator;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "FlgHourModel{" +
				"year='" + year + '\'' +
				", month='" + month + '\'' +
				", date='" + date + '\'' +
				", ac_type='" + ac_type + '\'' +
				", ac_serno='" + ac_serno + '\'' +
				", first_pilot='" + first_pilot + '\'' +
				", second_pilot='" + second_pilot + '\'' +
				", day_hour='" + day_hour + '\'' +
				", night_hour='" + night_hour + '\'' +
				", instr_actual='" + instr_actual + '\'' +
				", inst_simulator='" + inst_simulator + '\'' +
				", remarks='" + remarks + '\'' +
				", d_n_flag='" + d_n_flag + '\'' +
				'}';
	}
}
