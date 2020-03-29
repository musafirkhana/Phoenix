package com.baf.musafir.phoenix.databse;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.baf.musafir.phoenix.holder.AllCourseVector;
import com.baf.musafir.phoenix.holder.AllMsnProfileVector;
import com.baf.musafir.phoenix.holder.AllPhaseVector;
import com.baf.musafir.phoenix.holder.AllprofileListVector;
import com.baf.musafir.phoenix.holder.CoordinateVector;
import com.baf.musafir.phoenix.holder.FlgHourVector;
import com.baf.musafir.phoenix.model.CoordinateModel;
import com.baf.musafir.phoenix.model.CourseModel;
import com.baf.musafir.phoenix.model.FlgHourModel;
import com.baf.musafir.phoenix.model.MsnProfileModel;
import com.baf.musafir.phoenix.model.PhaseModel;
import com.baf.musafir.phoenix.model.ProfileModel;
import com.baf.musafir.phoenix.quiz.Question;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;


public class DataBaseUtility {
    private static String TAG = "DataBaseUtility";

    /******************************
     * Getting  Contact from DB
     * Getting All Mobile no where baseID=1 that means Air HQ
     ******************************/
    public void getCoordinateData(Context context) {
        AssetDatabaseOpenHelper databaseOpenHelper = new AssetDatabaseOpenHelper(context);
        SQLiteDatabase db = databaseOpenHelper.openDatabase();
        Log.w(TAG, "getAirHqLodgerData: " + db.getVersion());

        Cursor cursor = db.rawQuery(
                "SELECT * from cordinate;",
                null);
       // Log.i(TAG, "Database Query Are :" + cursor);
        CoordinateVector coordinateVector = new CoordinateVector();
        coordinateVector.removeAllCoordinatelist();
        if (cursor.moveToFirst()) {
            do {

                CoordinateModel coordinateModel = new CoordinateModel();
                coordinateModel.setLatitude(cursor.getString(1));
                coordinateModel.setLongitude(cursor.getString(2));
                coordinateModel.setPlaces(cursor.getString(0));
                coordinateVector.setAllCoordinatelist(coordinateModel);
                coordinateModel = null;
                Log.w(TAG, "Contact Data : " + cursor.getString(0));
                Timber.tag("asdsadsad").e(cursor.getString(2));

            } while (cursor.moveToNext());
        }
        db.close();
    }

    /***********************************
     * Getting List of course from DB
     * Only use for spinner
     ***********************************/
    public void getCourseName(Context context) {
        AssetDatabaseOpenHelper databaseOpenHelper = new AssetDatabaseOpenHelper(context);
        SQLiteDatabase db = databaseOpenHelper.openDatabase();
        Cursor cursor = db.rawQuery(
                "select * from mission_profile group by course_name;",
                null);
        AllCourseVector allCourseVector = new AllCourseVector();
        allCourseVector.removeCourse();
        if (cursor.moveToFirst()) {
            do {
                CourseModel courseModel = new CourseModel();
                courseModel.setCourse_id(cursor.getString(0));
                courseModel.setCourse_name(cursor.getString(1));
                courseModel.setPhase(cursor.getString(2));
                courseModel.setExercise_no(cursor.getString(3));

                allCourseVector.setAllCourse(courseModel);
                allCourseVector = null;
                Log.w(TAG, "getCourseName: " + cursor.getString(0));
            } while (cursor.moveToNext());
        }
        db.close();
    }
    /***********************************
     * Getting List of course from DB
     * Only use for spinner
     ***********************************/
    public void getPhaseName(Context context,String courseID) {
        AssetDatabaseOpenHelper databaseOpenHelper = new AssetDatabaseOpenHelper(context);
        SQLiteDatabase db = databaseOpenHelper.openDatabase();
        Cursor cursor = db.rawQuery(
                "select * from mission_profile where course_id='" +
                        courseID +
                        "' group by phase;",
                null);
        AllPhaseVector allPhaseVector = new AllPhaseVector();
        allPhaseVector.removePhaselist();
        if (cursor.moveToFirst()) {
            do {
                PhaseModel phaseModel = new PhaseModel();
                phaseModel.setCourse_id(cursor.getString(0));
                phaseModel.setCourse_name(cursor.getString(1));
                phaseModel.setPhase(cursor.getString(2));
                phaseModel.setExercise_no(cursor.getString(3));

                allPhaseVector.setAllPhaselist(phaseModel);
                allPhaseVector = null;
                Log.w(TAG, "getPhaseName: " + cursor.getString(0));
            } while (cursor.moveToNext());
        }
        db.close();
    }

    /***********************************
     * Getting List of Mission Profile from DB
     * Only use for spinner
     ***********************************/
    public void getMissionProfile(Context context,String courseID,String phaseID) {
        AssetDatabaseOpenHelper databaseOpenHelper = new AssetDatabaseOpenHelper(context);
        SQLiteDatabase db = databaseOpenHelper.openDatabase();
        Cursor cursor = db.rawQuery(
                "select * from mission_profile where course_id='" +
                        courseID +
                        "' and phase='" +
                        phaseID +
                        "';",
                null);
        AllMsnProfileVector allMsnProfileVector = new AllMsnProfileVector();
        allMsnProfileVector.removeMsnProfilelist();
        if (cursor.moveToFirst()) {
            do {
                MsnProfileModel msnProfileModel = new MsnProfileModel();
                msnProfileModel.setCourse_id(cursor.getString(0));
                msnProfileModel.setCourse_name(cursor.getString(1));
                msnProfileModel.setPhase(cursor.getString(2));
                msnProfileModel.setExercise_no(cursor.getString(3));
                msnProfileModel.setMsn_profile(cursor.getString(4));
                msnProfileModel.setDuration_dual(cursor.getString(5));
                msnProfileModel.setDuration_solo(cursor.getString(6));
                msnProfileModel.setDuration_progressive(cursor.getString(7));
                msnProfileModel.setMsn_status(cursor.getString(8));
                msnProfileModel.setId(cursor.getString(9));
                allMsnProfileVector.setAllMsnProfilelist(msnProfileModel);
                allMsnProfileVector = null;
                Log.w(TAG, "getMissionProfile: " + cursor.getString(0));
            } while (cursor.moveToNext());
        }
        db.close();
    }
    /******************************
     * Getting  Flg hhour from DB
     *
     ******************************/
    public void getFlghourData(Context context) {
        AssetDatabaseOpenHelper databaseOpenHelper = new AssetDatabaseOpenHelper(context);
        SQLiteDatabase db = databaseOpenHelper.openDatabase();
        Log.w(TAG, "getAirHqLodgerData: " + db.getVersion());

        Cursor cursor = db.rawQuery(
                "SELECT * from flg_hour;",
                null);
        Timber.tag("\"Database Query").e(""+cursor);
        FlgHourVector flgHourVector = new FlgHourVector();
        flgHourVector.removeFlhhourlist();
        if (cursor.moveToFirst()) {
            do {

                FlgHourModel flgHourModel = new FlgHourModel();
                flgHourModel.setYear(cursor.getString(0));
                flgHourModel.setMonth(cursor.getString(1));
                flgHourModel.setDate(cursor.getString(2));
                flgHourModel.setAc_type(cursor.getString(3));
                flgHourModel.setAc_serno(cursor.getString(4));
                flgHourModel.setFirst_pilot(cursor.getString(5));
                flgHourModel.setSecond_pilot(cursor.getString(6));
                flgHourModel.setDay_hour(cursor.getString(7));
                flgHourModel.setNight_hour(cursor.getString(8));
                flgHourModel.setInstr_actual(cursor.getString(9));
                flgHourModel.setInst_simulator(cursor.getString(10));
                flgHourModel.setD_n_flag(cursor.getString(12));

                flgHourVector.setFlhhourlist(flgHourModel);
                flgHourModel = null;
                Log.w(TAG, "Contact Data : " + cursor.getString(0));
                Timber.tag("Year").e(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        db.close();
    }
    /******************************
     * Getting  Flg hhour from DB
     *
     ******************************/
    public void getFilterFlghourData(Context context,String year,String month) {
        AssetDatabaseOpenHelper databaseOpenHelper = new AssetDatabaseOpenHelper(context);
        SQLiteDatabase db = databaseOpenHelper.openDatabase();
        Log.w(TAG, "getAirHqLodgerData: " + db.getVersion());

        Cursor cursor = db.rawQuery(
                "SELECT * from flg_hour where  year='" +
                        year +
                        "' AND month='" +
                        month +
                        "';",
                null);
        Timber.tag("\"Database Query").e(""+cursor);
        FlgHourVector flgHourVector = new FlgHourVector();
        flgHourVector.removeFlhhourlist();
        if (cursor.moveToFirst()) {
            do {

                FlgHourModel flgHourModel = new FlgHourModel();
                flgHourModel.setYear(cursor.getString(0));
                flgHourModel.setMonth(cursor.getString(1));
                flgHourModel.setDate(cursor.getString(2));
                flgHourModel.setAc_type(cursor.getString(3));
                flgHourModel.setAc_serno(cursor.getString(4));
                flgHourModel.setFirst_pilot(cursor.getString(5));
                flgHourModel.setSecond_pilot(cursor.getString(6));
                flgHourModel.setDay_hour(cursor.getString(7));
                flgHourModel.setNight_hour(cursor.getString(8));
                flgHourModel.setInstr_actual(cursor.getString(9));
                flgHourModel.setInst_simulator(cursor.getString(10));
                flgHourModel.setD_n_flag(cursor.getString(12));

                flgHourVector.setFlhhourlist(flgHourModel);
                flgHourModel = null;
                Log.w(TAG, "Contact Data : " + cursor.getString(0));
                Timber.tag("Year").e(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        db.close();
    }

    public List<Question> getQuizData(Context context ,String quizType) {
        List<Question> questionList = new ArrayList<Question>();
        AssetDatabaseOpenHelper databaseOpenHelper = new AssetDatabaseOpenHelper(context);
        SQLiteDatabase db = databaseOpenHelper.openDatabase();
        Log.w(TAG, "getAirHqLodgerData: " + db.getVersion());

        Cursor cursor = db.rawQuery(
                "SELECT * from quiz_table WHERE question_type='" +
                        quizType +
                        "';",
                null);
        // Log.i(TAG, "Database Query Are :" + cursor);
        if (cursor.moveToFirst()) {
            do {

                Question q = new Question();
                q.setId(cursor.getInt(0));
                q.setQuestion(cursor.getString(1));
                q.setAnswer(cursor.getString(2));
                q.setOptA(cursor.getString(3));
                q.setOptB(cursor.getString(4));
                q.setOptC(cursor.getString(5));
                q.setExplanation(cursor.getString(6));
                q.setOptD(cursor.getString(7));

                //add question in list
                questionList.add(q);

            } while (cursor.moveToNext());
        }
        return questionList;
       // db.close();
    }


    /******************************
     * Getting  Flg hhour from DB
     *
     ******************************/
    public void getProfileList(Context context) {
        AssetDatabaseOpenHelper databaseOpenHelper = new AssetDatabaseOpenHelper(context);
        SQLiteDatabase db = databaseOpenHelper.openDatabase();
        Cursor cursor = db.rawQuery(
                "select * from officer_data;",
                null);
        AllprofileListVector allprofileListVector = new AllprofileListVector();
        allprofileListVector.removeProfilelist();
        if (cursor.moveToFirst()) {
            do {

                ProfileModel profileModel = new ProfileModel();
                profileModel.setRank(cursor.getString(0));
                profileModel.setName(cursor.getString(1));
                profileModel.setBd_no(cursor.getString(2));
                profileModel.setBranch(cursor.getString(3));
                profileModel.setAppoinment(cursor.getString(4));
                profileModel.setDob(cursor.getString(5));
                profileModel.setEmail(cursor.getString(6));
                profileModel.setPosting_date(cursor.getString(7));
                profileModel.setBlood_group(cursor.getString(8));
                profileModel.setMobile(cursor.getString(9));
                profileModel.setProfile(cursor.getString(10));

                allprofileListVector.setAllProfilelist(profileModel);
                profileModel = null;
                Log.w(TAG, "Contact Data : " + cursor.getString(0));
                Timber.tag("Year").e(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        db.close();
    }

}
