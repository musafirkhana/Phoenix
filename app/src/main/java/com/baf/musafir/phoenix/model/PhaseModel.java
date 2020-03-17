package com.baf.musafir.phoenix.model;

public class PhaseModel {

	private String course_id = "";
	private String course_name = "";
	private String phase = "";
	private String exercise_no = "";

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

	@Override
	public String toString() {
		return "PhaseModel{" +
				"course_id='" + course_id + '\'' +
				", course_name='" + course_name + '\'' +
				", phase='" + phase + '\'' +
				", exercise_no='" + exercise_no + '\'' +
				'}';
	}
}
