package com.baf.musafir.phoenix.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.holder.AllCourseVector;
import com.baf.musafir.phoenix.holder.AllMsnProfileVector;
import com.baf.musafir.phoenix.model.CourseModel;
import com.baf.musafir.phoenix.model.MsnProfileModel;

import java.util.List;

public class MissionProfileAdapter extends ArrayAdapter<MsnProfileModel> implements SpinnerAdapter {
    Context context;

    private List<MsnProfileModel> msnProfileModels;

    public MissionProfileAdapter(Context context, int textViewResourceId) {
        super(context, R.layout.row_msnprofile, AllMsnProfileVector.getAllMsnProfilelist());
        this.context = context;
        this.msnProfileModels = AllMsnProfileVector.getAllMsnProfilelist();

    }



    static class ViewHolder {

        private TextView txt_exno;
        private TextView txt_dual;
        private TextView txt_solo;
        private TextView txt_prog;
        private LinearLayout header_linear;


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        View v = convertView;

        if (v == null) {
            final LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.row_msnprofile, parent,false);
            holder = new ViewHolder();

            holder.txt_exno = (TextView) v.findViewById(R.id.txt_exno);
            holder.txt_dual = (TextView) v.findViewById(R.id.txt_dual);
            holder.txt_solo = (TextView) v.findViewById(R.id.txt_solo);
            holder.txt_prog = (TextView) v.findViewById(R.id.txt_prog);
            holder.header_linear=(LinearLayout)v.findViewById(R.id.header_linear);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        if (position < AllMsnProfileVector.getAllMsnProfilelist().size()) {
            MsnProfileModel query = msnProfileModels.get(position);
            holder.txt_exno.setText(query.getExercise_no());
            holder.txt_dual.setText(query.getDuration_dual());
            holder.txt_solo.setText(query.getDuration_solo());
            holder.txt_prog.setText(query.getDuration_progressive());
            if(query.getMsn_status().equalsIgnoreCase("1")){
                holder.header_linear.setBackgroundColor(context.getResources().getColor(R.color.color_blog));
            }





        }

        return v;
    }
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getView(position, convertView, parent);
    }





}