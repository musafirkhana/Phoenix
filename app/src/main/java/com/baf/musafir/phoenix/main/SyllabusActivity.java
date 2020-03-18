package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.adapter.CourseAdapter;
import com.baf.musafir.phoenix.adapter.MissionProfileAdapter;
import com.baf.musafir.phoenix.adapter.PhaseAdapter;
import com.baf.musafir.phoenix.databse.AssetDatabaseOpenHelper;
import com.baf.musafir.phoenix.databse.DataBaseUtility;
import com.baf.musafir.phoenix.holder.AllCourseVector;
import com.baf.musafir.phoenix.holder.AllMsnProfileVector;
import com.baf.musafir.phoenix.holder.AllPhaseVector;
import com.baf.musafir.phoenix.model.CourseModel;
import com.baf.musafir.phoenix.model.MsnProfileModel;
import com.baf.musafir.phoenix.model.PhaseModel;
import com.baf.musafir.phoenix.quiz.QuizlistActivity;
import com.baf.musafir.phoenix.util.StringUtility;
import com.baf.musafir.phoenix.util.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import timber.log.Timber;

/***************************
 * copyright@ Musafir Ali
 * Bangladesh Airforce (103 ATTU)
 **********************/
public class SyllabusActivity extends Activity  {
    private String TAG = "SyllabusActivity";
    private Context mContext;
    private ToastUtil toastUtil;
    private DataBaseUtility dataBaseUtility;
    private CourseAdapter courseAdapter;
    private PhaseAdapter phaseAdapter;
    private MissionProfileAdapter missionProfileAdapter;

    Typeface tf ;


    /********************
     * UI Decleration
     *******************/

    private TextView top_syllabus;
    private Spinner course_spinner;
    private Spinner phase_spinner;
    private ListView exercise_list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_syllabus);
        mContext = this;
        dataBaseUtility=new DataBaseUtility();
        dataBaseUtility.getCourseName(mContext);
        toastUtil = new ToastUtil(this);
        tf = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/megatron.ttf");
        initUI();
        changeFont();
    }



    private void initUI() {
        courseAdapter=new CourseAdapter(mContext,R.layout.row_course);
        phaseAdapter=new PhaseAdapter(mContext,R.layout.row_phase);
        course_spinner = (Spinner) findViewById(R.id.course_spinner);
        phase_spinner = (Spinner) findViewById(R.id.phase_spinner);

        course_spinner.setAdapter(courseAdapter);
        course_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                CourseModel query = AllCourseVector.getAllCourse().elementAt(position);
                Log.w(TAG, "Course ID: " + query.getCourse_id());

                callAgain("" + query.getCourse_id());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }



    public void callAgain(String courseID) {
        dataBaseUtility.getPhaseName(mContext, courseID);
        phase_spinner.setAdapter(phaseAdapter);
        phase_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                PhaseModel query = AllPhaseVector.getAllPhaselist().elementAt(position);
                Log.w(TAG, "PHASE ID: " + query.getPhase());
                detailList(query.getCourse_id(), query.getPhase());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void detailList(String courseID, String phaseID) {
        dataBaseUtility.getMissionProfile(mContext, courseID, phaseID);
        exercise_list = (ListView) findViewById(R.id.exercise_list);
        missionProfileAdapter = new MissionProfileAdapter(mContext,R.layout.row_msnprofile);;
        exercise_list.setAdapter(missionProfileAdapter);
        exercise_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MsnProfileModel query = AllMsnProfileVector.getAllMsnProfilelist().elementAt(position);
                Log.w(TAG, "Mission Profile: " + query.getMsn_profile());
                Intent intent = new Intent(mContext, WebviewActivity.class);
                intent.putExtra("DETAIL",query.getMsn_profile());
                intent.putExtra("HEADER",query.getExercise_no());
                startActivity(intent);

            }
        });

    }
    private void changeFont() {

        top_syllabus = (TextView) findViewById(R.id.top_syllabus);
        top_syllabus.setTypeface(tf);


    }

    public void BACK(View v){
        this.finish();

    }
}
