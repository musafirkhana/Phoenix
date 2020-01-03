package com.baf.musafir.phoenix.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Environment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.databse.AssetDatabaseOpenHelper;
import com.baf.musafir.phoenix.databse.DataBaseUtility;
import com.baf.musafir.phoenix.holder.CoordinateVector;
import com.baf.musafir.phoenix.holder.FlgHourVector;
import com.baf.musafir.phoenix.model.CoordinateModel;
import com.baf.musafir.phoenix.model.FlgHourModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class CoordinateAdapter extends ArrayAdapter<CoordinateModel>  {
    Context context;

    private Filter planetFilter;
    private  List<CoordinateModel> origPlanetList;
    private  List<CoordinateModel> coordinateModels;
    DataBaseUtility dataBaseUtility;
    Typeface tf;

    public CoordinateAdapter(Context context) {
        super(context, R.layout.row_coordinate, CoordinateVector.getAllCoordinatelist());
        this.context = context;
        this.coordinateModels = CoordinateVector.getAllCoordinatelist();
        this.origPlanetList = CoordinateVector.getAllCoordinatelist();
        dataBaseUtility=new DataBaseUtility();
        tf = Typeface.createFromAsset(context.getAssets(),
                "fonts/megatron.ttf");

    }

    public int getCount() {
        return coordinateModels.size();
    }

    public CoordinateModel getItem(int position) {
        return coordinateModels.get(position);
    }

    public long getItemId(int position) {
        return coordinateModels.get(position).hashCode();
    }

    public static class ViewHolder {

        private TextView tv_lat;
        private TextView tv_long;
        private TextView tv_place;
        private ImageView delete_item;





    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        View v = convertView;

        if (v == null) {
            final LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.row_coordinate, parent,false);
            // convertView = _inflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();

            holder.tv_lat = (TextView) v.findViewById(R.id.tv_lat);
            holder.tv_long = (TextView) v.findViewById(R.id.tv_long);
            holder.tv_place = (TextView) v.findViewById(R.id.tv_place);
            holder.delete_item=(ImageView)v.findViewById(R.id.delete_item);
            holder.tv_lat.setTypeface(tf);
            holder.tv_long.setTypeface(tf);
            holder.tv_place.setTypeface(tf);


            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        if (position < CoordinateVector.getAllCoordinatelist().size()) {
            CoordinateModel query = coordinateModels.get(position);
            holder.tv_lat.setText("Lat " +query.getLatitude());
            holder.tv_long.setText("Long "+query.getLatitude());
            holder.tv_place.setText(""+query.getPlaces());
            Timber.i(""+query.getPlaces());
            holder.delete_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CoordinateModel query1 = coordinateModels.get(position);
                    deleteData(query1.getPlaces());
                    Timber.i(query1.getPlaces());
                    dataBaseUtility.getCoordinateData(context);
                    notifyDataSetChanged();
                }
            });



        }

        return v;
    }
    private void deleteData(String place) {
        AssetDatabaseOpenHelper databaseOpenHelper = new AssetDatabaseOpenHelper(context);
        SQLiteDatabase db = databaseOpenHelper.openDatabase();
        db.beginTransaction();

        String strSQL = "DELETE FROM cordinate WHERE place = '" +
                place +
                "'; ";
        db.execSQL(strSQL);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        Timber.i("Data Insert Duccessfully");
        //toastUtil.appSuccessMsg(mContext, "Data Insert Duccessfully");

    }


    public void resetData() {
        coordinateModels = origPlanetList;
    }
    /*
	 * We create our filter
	 */
    /*
     * We create our filter
     */

//    @Override
//    public Filter getFilter() {
//        if (planetFilter == null)
//            planetFilter = new PlanetFilter();
//
//        return planetFilter;
//    }
//
//    public class PlanetFilter extends Filter {
//
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            FilterResults results = new FilterResults();
//            // We implement here the filter logic
//            if (constraint == null || constraint.length() == 0) {
//                // No filter implemented we return all the list
//                results.values = origPlanetList;
//                results.count = origPlanetList.size();
//            } else {
//                // We perform filtering operation
//                List<CoordinateModel> nPlanetList = new ArrayList<CoordinateModel>();
//                for (CoordinateModel p : coordinateModels) {
//
//                    if (p.getPlaces().toUpperCase().contains(constraint.toString().toUpperCase())) {
//                        nPlanetList.add(p);
//                    }
//
//                }
//
//                results.values = nPlanetList;
//                results.count = nPlanetList.size();
//
//            }
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//
//            // Now we have to inform the adapter about the new list filtered
//            if (results.count == 0)
//                notifyDataSetInvalidated();
//            else {
//                coordinateModels = (List<CoordinateModel>) results.values;
//                notifyDataSetChanged();
//            }
//
//        }
//
//    }

}