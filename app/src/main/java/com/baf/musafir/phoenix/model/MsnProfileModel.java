package com.baf.musafir.phoenix.model;

public class MsnProfileModel {

	private String course_id = "";
	private String course_name = "";
	private String phase = "";
	private String exercise_no = "";
	private String msn_profile = "";
	private String duration_dual = "";
	private String duration_solo = "";
	private String duration_progressive = "";
	private String msn_status = "";
	private String id = "";

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getExercise_no() {
		return exercise_no;
	}

	public void setExercise_no(String exercise_no) {
		this.exercise_no = exercise_no;
	}

	public String getMsn_profile() {
		return msn_profile;
	}

	public void setMsn_profile(String msn_profile) {
		this.msn_profile = msn_profile;
	}

	public String getDuration_dual() {
		return duration_dual;
	}

	public void setDuration_dual(String duration_dual) {
		this.duration_dual = duration_dual;
	}

	public String getDuration_solo() {
		return duration_solo;
	}

	public void setDuration_solo(String duration_solo) {
		this.duration_solo = duration_solo;
	}

	public String getDuration_progressive() {
		return duration_progressive;
	}

	public void setDuration_progressive(String duration_progressive) {
		this.duration_progressive = duration_progressive;
	}

	public String getMsn_status() {
		return msn_status;
	}

	public void setMsn_status(String msn_status) {
		this.msn_status = msn_status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "MsnProfileModel{" +
				"course_id='" + course_id + '\'' +
				", course_name='" + course_name + '\'' +
				", phase='" + phase + '\'' +
				", exercise_no='" + exercise_no + '\'' +
				", msn_profile='" + msn_profile + '\'' +
				", duration_dual='" + duration_dual + '\'' +
				", duration_solo='" + duration_solo + '\'' +
				", duration_progressive='" + duration_progressive + '\'' +
				", msn_status='" + msn_status + '\'' +
				", id='" + id + '\'' +
				'}';
	}
}
