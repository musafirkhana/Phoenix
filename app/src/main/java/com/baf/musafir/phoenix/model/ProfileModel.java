package com.baf.musafir.phoenix.model;

public class ProfileModel {

	private String rank = "";
	private String name = "";
	private String bd_no = "";
	private String branch = "";
	private String appoinment = "";
	private String dob = "";
	private String email = "";
	private String posting_date = "";
	private String blood_group = "";
	private String mobile = "";
	private String profile = "";

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBd_no() {
		return bd_no;
	}

	public void setBd_no(String bd_no) {
		this.bd_no = bd_no;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAppoinment() {
		return appoinment;
	}

	public void setAppoinment(String appoinment) {
		this.appoinment = appoinment;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosting_date() {
		return posting_date;
	}

	public void setPosting_date(String posting_date) {
		this.posting_date = posting_date;
	}

	public String getBlood_group() {
		return blood_group;
	}

	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "ProfileModel{" +
				"rank='" + rank + '\'' +
				", name='" + name + '\'' +
				", bd_no='" + bd_no + '\'' +
				", branch='" + branch + '\'' +
				", appoinment='" + appoinment + '\'' +
				", dob='" + dob + '\'' +
				", email='" + email + '\'' +
				", posting_date='" + posting_date + '\'' +
				", blood_group='" + blood_group + '\'' +
				", mobile='" + mobile + '\'' +
				", profile='" + profile + '\'' +
				'}';
	}
}
