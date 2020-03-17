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
import com.baf.musafir.phoenix.holder.AllPhaseVector;
import com.baf.musafir.phoenix.model.CourseModel;
import com.baf.musafir.phoenix.model.PhaseModel;

import java.util.List;

public class PhaseAdapter extends ArrayAdapter<PhaseModel> implements SpinnerAdapter {
    Context context;

    private List<PhaseModel> phaseModels;

    public PhaseAdapter(Context context, int textViewResourceId) {
        super(context, R.layout.row_phase, AllPhaseVector.getAllPhaselist());
        this.context = context;
        this.phaseModels = AllPhaseVector.getAllPhaselist();

    }



    static class ViewHolder {

        private TextView txt_section_list;




    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        View v = convertView;

        if (v == null) {
            final LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.row_phase, parent,false);
            holder = new ViewHolder();

            holder.txt_section_list = (TextView) v.findViewById(R.id.txt_section_list);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        if (position < AllCourseVector.getAllCourse().size()) {
            PhaseModel query = phaseModels.get(position);
            holder.txt_section_list.setText(query.getPhase());





        }

        return v;
    }
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getView(position, convertView, parent);
    }





}