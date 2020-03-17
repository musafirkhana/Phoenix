package com.baf.musafir.phoenix.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.holder.AllCourseVector;
import com.baf.musafir.phoenix.model.CourseModel;

import java.util.List;

public class CourseAdapter extends ArrayAdapter<CourseModel> implements SpinnerAdapter {
    Context context;

    private List<CourseModel> courseModels;

    public CourseAdapter(Context context, int textViewResourceId) {
        super(context, R.layout.row_course, AllCourseVector.getAllCourse());
        this.context = context;
        this.courseModels = AllCourseVector.getAllCourse();

    }



    static class ViewHolder {

        private TextView txt_section;
        private TextView txt_id;




    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        View v = convertView;

        if (v == null) {
            final LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.row_course, parent,false);
            holder = new ViewHolder();

            holder.txt_section = (TextView) v.findViewById(R.id.txt_section);
            holder.txt_id = (TextView) v.findViewById(R.id.txt_id);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        if (position < AllCourseVector.getAllCourse().size()) {
            CourseModel query = courseModels.get(position);
            holder.txt_section.setText(query.getCourse_name());
            holder.txt_id.setText(query.getCourse_id());





        }

        return v;
    }
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getView(position, convertView, parent);
    }





}