package com.baf.musafir.phoenix.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.holder.AllDetailVector;
import com.baf.musafir.phoenix.holder.FlgHourVector;
import com.baf.musafir.phoenix.model.DetailModel;
import com.baf.musafir.phoenix.model.FlgHourModel;

import java.io.File;
import java.util.List;
import java.util.Timer;

import timber.log.Timber;

public class FlhhourAdapter extends ArrayAdapter<FlgHourModel>  {
    Context context;
    private File sdCard = Environment.getExternalStorageDirectory();

    private Filter planetFilter;
    private List<FlgHourModel> flgHourModels;

    public FlhhourAdapter(Context context) {
        super(context, R.layout.row_flg_hour, FlgHourVector.getAllFlhhourlist());
        this.context = context;
        this.flgHourModels = FlgHourVector.getAllFlhhourlist();

    }

    public int getCount() {
        return flgHourModels.size();
    }

    public FlgHourModel getItem(int position) {
        return flgHourModels.get(position);
    }

    public long getItemId(int position) {
        return flgHourModels.get(position).hashCode();
    }

    static class ViewHolder {

        private TextView day_text;
        private TextView actype_text;
        private TextView ac_serno;
        private TextView pilot_one;
        private TextView pilot_two;
        private TextView inst_actual;
        private TextView inst_simulator;
        private LinearLayout row_linear;




    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        View v = convertView;

        if (v == null) {
            final LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.row_flg_hour, parent,false);
            // convertView = _inflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();

            holder.day_text = (TextView) v.findViewById(R.id.day_text);
            holder.actype_text = (TextView) v.findViewById(R.id.actype_text);
            holder.ac_serno = (TextView) v.findViewById(R.id.ac_serno);
            holder.pilot_one = (TextView) v.findViewById(R.id.pilot_one);
            holder.pilot_two = (TextView) v.findViewById(R.id.pilot_two);
            holder.inst_actual = (TextView) v.findViewById(R.id.inst_actual);
            holder.inst_simulator = (TextView) v.findViewById(R.id.inst_simulator);
            holder.row_linear=(LinearLayout) v.findViewById(R.id.row_linear);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        if (position < FlgHourVector.getAllFlhhourlist().size()) {
            FlgHourModel query = flgHourModels.get(position);
            holder.day_text.setText(query.getDate());
            holder.actype_text.setText(query.getAc_type());
            holder.ac_serno.setText(query.getAc_serno());
            holder.pilot_one.setText(query.getFirst_pilot());
            holder.pilot_two.setText(query.getSecond_pilot());
            holder.inst_actual.setText(query.getInstr_actual());
            holder.inst_simulator.setText(query.getInst_simulator());
            Timber.i(query.getD_n_flag());
            if(query.getD_n_flag().equalsIgnoreCase("1")){

                holder.row_linear.setBackgroundColor(Color.GREEN);
            }else {
                holder.row_linear.setBackgroundColor(Color.RED);
            }





        }

        return v;
    }
//    @Override
//    public View getDropDownView(int position, View convertView,
//                                ViewGroup parent) {
//        return getView(position, convertView, parent);
//    }


    public void resetData() {
        flgHourModels = flgHourModels;
    }
    /*
	 * We create our filter
	 */


}