package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.databse.DataBaseUtility;
import com.baf.musafir.phoenix.holder.CoordinateVector;
import com.baf.musafir.phoenix.model.CoordinateModel;
import com.baf.musafir.phoenix.util.StringUtility;
import com.baf.musafir.phoenix.util.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

public class MaplistviewActivity extends Activity {

    private ListView listView;
    private Button addButton;
    private Button map_generation;
    private TextView map_tv;
    AutoCompleteTextView actv;
    private Context mContext;
    private locationAdapter adapter;
    private ToastUtil toastUtil;
    private String[] ListElements = new String[]{
//            "23.8103,90.4125",
//            "24.7471,90.4203","24.8949,91.8687","24.3065,91.7296"
    };

    private String latitude = "";
    private String longitude = "";
    List<String> ListElementsArrayList;
    private DataBaseUtility dataBaseUtility;
    private MapCoordinateAdapter mapCoordinateAdapter;
    Typeface tf ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_maplist);
        mContext = this;
        toastUtil = new ToastUtil(this);
        dataBaseUtility = new DataBaseUtility();
        tf = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/megatron.ttf");
        dataBaseUtility.getCoordinateData(this);
        initUI();
        autocompletePlaces();
        changeFont();


    }
private void initUI(){
    listView = (ListView) findViewById(R.id.listView);
    addButton = (Button) findViewById(R.id.button1);
    map_tv=(TextView)findViewById(R.id.map_tv);
    map_generation = (Button) findViewById(R.id.map_generation);
    ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));

    adapter = new locationAdapter(mContext, R.layout.row_location, ListElementsArrayList);
    listView.setAdapter(adapter);
    addButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (latitude.equalsIgnoreCase("") ||
                    longitude.equalsIgnoreCase("")) {
                toastUtil.appSuccessMsg(mContext, "Please insert proper data");
            } else {
                ListElementsArrayList.add(latitude + "," + longitude);
                listView.setAdapter(adapter);
                actv.setText("");
                // adapter.notify();
                adapter.notifyDataSetChanged();
            }

        }
    });

    map_generation.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (ListElementsArrayList.size() == 0) {
                toastUtil.appSuccessMsg(mContext, "No Data Available");
            } else {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                intent.putExtra("mylist", (Serializable) ListElementsArrayList);
                startActivity(intent);
            }

        }
    });
}
    /*******************
     * Autocomplete places
     * Behind mapping lat long
     */

    private void autocompletePlaces() {
        //Creating the instance of ArrayAdapter containing list of fruit names
        mapCoordinateAdapter = new MapCoordinateAdapter(this);
        //Getting the instance of AutoCompleteTextView
        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        actv.setAdapter(mapCoordinateAdapter);
        actv.setThreshold(1);//will start working from first character
//        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
//                //this is the way to find selected object/item
//               // CoordinateModel query = adapterView.getItemAtPosition(pos);
//                if (pos < CoordinateVector.getAllCoordinatelist().size()) {
//                    CoordinateModel query = coordinateModels.get(pos);
//                    Timber.i(""+query.getPlaces());
//
//                    actv.setText(query.getPlaces());
//                    Timber.i(""+pos);
//                    Timber.i(""+query.getPlaces());
//                    latitude = query.getLatitude();
//                    longitude = query.getLongitude();
//
//                }
//
//            }
//        });
        actv.setTextColor(Color.BLACK);
        actv.setHintTextColor(Color.BLACK);
    }

    /*************************************
     * Adapter for filtering Data
     *
     ****************************/

    class locationAdapter extends ArrayAdapter<String> {
        List<String> arrayList;
        Context context;

        public locationAdapter(Context context, int textViewResourceId, List<String> arrayList) {
            super(context, textViewResourceId, arrayList);
            this.arrayList = arrayList;
            this.context = context;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEnabled(int position) {
            return true;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {
        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        //        @Override
//        public String getItem(int position) {
//            return position;
//        }
        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            String subjectData = arrayList.get(position);
            if (convertView == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                convertView = layoutInflater.inflate(R.layout.row_location, null);
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                TextView location_textview = convertView.findViewById(R.id.location_textview);
                TextView longitude_textview = convertView.findViewById(R.id.longitude_textview);
                ImageView delete_item = convertView.findViewById(R.id.delete_item);

                location_textview.setText("Latitude :" + StringUtility.getlatitude(subjectData));
                longitude_textview.setText("Longitude :" + StringUtility.getlongitude(subjectData));

                delete_item.setTag(position);
                delete_item.setOnClickListener(myButtonClickListener);


            }
            return convertView;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }


    }

    private View.OnClickListener myButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (listView.getCount() == 0) {
                adapter.notifyDataSetChanged();
            } else {
                int position = (Integer) v.getTag();
                ListElementsArrayList.remove(position);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }


        }
    };

    private void changeFont() {

        actv.setTypeface(tf);
        map_generation.setTypeface(tf);
        addButton.setTypeface(tf);
        map_tv.setTypeface(tf);


    }


    public class MapCoordinateAdapter extends ArrayAdapter<CoordinateModel>  {
        Context context;

        private Filter planetFilter;
        private List<CoordinateModel> origPlanetList;
        private List<CoordinateModel> coordinateModels;

        public MapCoordinateAdapter(Context context) {
            super(context, R.layout.row_mapcoordinate, CoordinateVector.getAllCoordinatelist());
            this.context = context;
            this.coordinateModels = CoordinateVector.getAllCoordinatelist();
            this.origPlanetList = CoordinateVector.getAllCoordinatelist();

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

//         class ViewHolder {
//
//            private TextView tv_coordinate;
//
//
//
//
//
//        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            //final ViewHolder holder;
            View v = convertView;

            if (v == null) {
                final LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.row_mapcoordinate, parent,false);
                // convertView = _inflater.inflate(R.layout.list_row, null);
               // holder = new com.baf.musafir.phoenix.adapter.MapCoordinateAdapter.ViewHolder();

                TextView tv_coordinate = (TextView) v.findViewById(R.id.tv_coordinate);

                if (position < CoordinateVector.getAllCoordinatelist().size()) {
                    CoordinateModel query = coordinateModels.get(position);

                    tv_coordinate.setText(query.getPlaces());
                    Timber.i(""+query.getPlaces());



                }
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CoordinateModel query = coordinateModels.get(position);
                        Timber.i(""+query.getPlaces());

                        actv.setText(query.getPlaces());
                        Timber.i(""+position);
                        Timber.i(""+query.getPlaces());
                        latitude = query.getLatitude();
                        longitude = query.getLongitude();
                    }
                });
                //v.setTag(holder);
            }


            return v;
        }



        public void resetData() {
            coordinateModels = origPlanetList;
        }


        @Override
        public Filter getFilter() {
            if (planetFilter == null)
                planetFilter = new PlanetFilter();

            return planetFilter;
        }

        private class PlanetFilter extends Filter {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                // We implement here the filter logic
                if (constraint == null || constraint.length() == 0) {
                    // No filter implemented we return all the list
                    results.values = origPlanetList;
                    results.count = origPlanetList.size();
                } else {
                    // We perform filtering operation
                    List<CoordinateModel> nPlanetList = new ArrayList<CoordinateModel>();
                    for (CoordinateModel p : coordinateModels) {

                        if (p.getPlaces().toUpperCase().startsWith(constraint.toString().toUpperCase())) {
                            nPlanetList.add(p);
                        }

                    }

                    results.values = nPlanetList;
                    results.count = nPlanetList.size();

                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                // Now we have to inform the adapter about the new list filtered
                if (results.count == 0)
                    notifyDataSetInvalidated();
                else {
                    coordinateModels = (List<CoordinateModel>) results.values;
                    notifyDataSetChanged();
                }

            }

        }

    }
}