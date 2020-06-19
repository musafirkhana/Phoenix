package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.adapter.CoordinateAdapter;
import com.baf.musafir.phoenix.databse.DataBaseUtility;
import com.baf.musafir.phoenix.holder.AllprofileListVector;
import com.baf.musafir.phoenix.holder.CoordinateVector;
import com.baf.musafir.phoenix.model.CoordinateModel;
import com.baf.musafir.phoenix.model.ProfileModel;
import com.baf.musafir.phoenix.util.AppConstant;
import com.baf.musafir.phoenix.util.CoordinateConvert;
import com.baf.musafir.phoenix.util.StringUtility;
import com.baf.musafir.phoenix.util.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

public class MaplistviewActivity extends Activity {


    private Context mContext;
    private ToastUtil toastUtil;
    private String[] ListElements = new String[]{};

    private String latitude = "";
    private String longitude = "";
    private String places = "";
    List<String> ListElementsArrayList;
    private DataBaseUtility dataBaseUtility;
    private MapCoordinateAdapter mapCoordinateAdapter;
    private locationAdapter adapter;
    private AutoCompleteTextView actv;
    Typeface tf;

    private ListView listView;
    private Button addButton;
    private Button map_generation;
    private TextView top_syllabus;


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

    private void initUI() {
        listView = (ListView) findViewById(R.id.listView);
        addButton = (Button) findViewById(R.id.button1);
        top_syllabus = (TextView) findViewById(R.id.top_syllabus);
        map_generation = (Button) findViewById(R.id.map_generation);
        ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));

        adapter = new locationAdapter(mContext, R.layout.row_location, ListElementsArrayList);
        listView.setAdapter(adapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (latitude.equalsIgnoreCase("") || longitude.equalsIgnoreCase(""))
                {
                    toastUtil.appSuccessMsg(mContext, AppConstant.EMPTY_FIELD);
                } else {
                    //take final place name from textview and add to new list
                    places=actv.getText().toString();

                    ListElementsArrayList.add(latitude + "," + longitude+","+places);
                    listView.setAdapter(adapter);
                    actv.setText("");
                    adapter.notifyDataSetChanged();
                }

            }
        });

        map_generation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ListElementsArrayList.size() == 0) {
                    toastUtil.appSuccessMsg(mContext, AppConstant.NO_LOC);
                } else {
                    locationEnabled();
                }

            }
        });
    }

    /*******************
     * Autocomplete places
     * Behind mapping lat long
     */

    private void autocompletePlaces() {
        mapCoordinateAdapter = new MapCoordinateAdapter(this);
        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        actv.setAdapter(mapCoordinateAdapter);
        actv.setThreshold(1);//will start working from first character

        actv.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count < before) {
                    listView.setVisibility(View.VISIBLE);
                    // We're deleting char so we need to reset the adapter data
                    mapCoordinateAdapter.resetData();
                }
                mapCoordinateAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CoordinateModel query= mapCoordinateAdapter.planetList.get(position);
                actv.setText(query.getPlaces());

                //converting DMS to latlong
                latitude = CoordinateConvert.dmdtoLatlong(query.getLatitude());
                longitude = CoordinateConvert.dmdtoLatlong(query.getLongitude());



                Timber.i("Autocomplete latitude    " + latitude);
                Timber.i("Autocomplete latitude    " + longitude);
                Timber.i("Autocomplete Place    " + query.getPlaces());
                Timber.i("Autocomplete Original    " + query.getLatitude());

            }
        });

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

                // Convert lat long to N E
                location_textview.setText("" + CoordinateConvert.convertDmsToLatLong(
                                 ""+StringUtility.getlatitude(subjectData),
                                ""+StringUtility.getlongitude(subjectData)));

                longitude_textview.setText("" + StringUtility.getPlaces(subjectData));

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

        map_generation.setTypeface(tf);
        addButton.setTypeface(tf);
        top_syllabus.setTypeface(tf);


    }


    /************************
     * This block of code is for autocomplete Search adapter
     */
    public static class MapCoordinateAdapter extends ArrayAdapter<CoordinateModel> {
        Context context;

        private Filter planetFilter;
        private List<CoordinateModel> origPlanetList;
        private List<CoordinateModel> planetList;

        public MapCoordinateAdapter(Context context) {
            super(context, R.layout.row_mapcoordinate, CoordinateVector.getAllCoordinatelist());
            this.context = context;
            this.planetList = CoordinateVector.getAllCoordinatelist();
            this.origPlanetList = CoordinateVector.getAllCoordinatelist();

        }

        public int getCount() {
            return planetList.size();
        }

        public CoordinateModel getItem(int position) {
            return planetList.get(position);
        }

        public long getItemId(int position) {
            return planetList.get(position).hashCode();
        }

        class ViewHolder {

            private TextView tv_coordinate;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            View v = convertView;
            if (v == null) {
                final LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.row_mapcoordinate, null);
                holder = new ViewHolder();

                holder.tv_coordinate = (TextView) v.findViewById(R.id.tv_coordinate);

                v.setTag(holder);
            } else {
                holder = (ViewHolder) v.getTag();
            }
            if (position < CoordinateVector.getAllCoordinatelist().size()) {
                CoordinateModel query = planetList.get(position);
                holder.tv_coordinate.setText(query.getPlaces());
                Timber.i("" + query.getPlaces());


            }


            return v;
        }


        public void resetData() {
            planetList = origPlanetList;
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
                    for (CoordinateModel p : planetList) {
                        if (p.getPlaces().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            nPlanetList.add(p);
                        }
                        Timber.i("Filter Places    " + p.getPlaces().toLowerCase());
                        Timber.d("Filter constraint  " + constraint.toString().toLowerCase());
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
                    planetList = (List<CoordinateModel>) results.values;
                    notifyDataSetChanged();
                }

            }

        }

    }

    private void locationEnabled() {
        LocationManager lm = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!gps_enabled && !network_enabled) {
            new AlertDialog.Builder(MaplistviewActivity.this)
                    .setMessage("GPS Enable")
                    .setPositiveButton("Settings", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                }
                            })
                    .setNegativeButton("Cancel", null)
                    .show();
        } else {
            Intent intent = new Intent(getApplicationContext(), MapActivity.class);
            intent.putExtra("mylist", (Serializable) ListElementsArrayList);
            startActivity(intent);
        }
    }

    public void BACK(View v) {
        this.finish();

    }


}