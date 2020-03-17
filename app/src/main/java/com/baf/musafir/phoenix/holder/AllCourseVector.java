package com.baf.musafir.phoenix.holder;

import com.baf.musafir.phoenix.model.CourseModel;

import java.util.Vector;


public class AllCourseVector {
	public static Vector<CourseModel> courseModels = new Vector<CourseModel>();

	public static Vector<CourseModel> getAllCourse() {
		return courseModels;
	}

	public static void setAllCourse(Vector<CourseModel> courseModels) {
		AllCourseVector.courseModels = courseModels;
	}

	public static CourseModel getAllCourse(int pos) {
		return courseModels.elementAt(pos);
	}

	public static void setAllCourse(CourseModel courseModels) {
		AllCourseVector.courseModels.addElement(courseModels);
	}

	public static void removeCourse() {
		AllCourseVector.courseModels.removeAllElements();
	}
}
