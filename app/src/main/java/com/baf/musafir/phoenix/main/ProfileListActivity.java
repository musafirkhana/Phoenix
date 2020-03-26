package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.TextView;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.holder.AllprofileListVector;
import com.baf.musafir.phoenix.model.ProfileModel;
import com.baf.musafir.phoenix.util.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*****************************
 * Search All Mobile No
 *****************************/
public class ProfileListActivity extends Activity {
    private Context mContext;
    private ProfileAdapter profileAdapter;
    public EditText mobile_no_search;
    private ListView profile_list;
    private ToastUtil toastUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_profile_searchlist);
        mContext = this;
        toastUtil=new ToastUtil(this);
        initUI();
    }
    public void HOME(View v) {
        this.finish();


    }



private void initUI(){
    mobile_no_search = (EditText) findViewById(R.id.mobile_no_search);
    profile_list = (ListView) findViewById(R.id.profile_list);
    profileAdapter = new ProfileAdapter(this);
    profile_list.setAdapter(profileAdapter);
    profile_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            ProfileModel query= profileAdapter.planetList.get(position);
            Intent intent = new Intent(mContext, ProfileActivity.class);
            intent.putExtra("name",""+query.getName());
            intent.putExtra("appoinment",""+query.getAppoinment());
            intent.putExtra("rank",""+query.getRank());
            intent.putExtra("branch",""+query.getBranch());
            intent.putExtra("dob",""+query.getDob());
            intent.putExtra("blood",""+query.getBlood_group());
            intent.putExtra("mobile",""+query.getMobile());
            intent.putExtra("email",""+query.getEmail());
            intent.putExtra("profile",""+query.getMobile());
//            toastUtil.appSuccessMsg(mContext,query.getName()+""+position);
            startActivity(intent);

        }
    });
    // TextFilter
    profile_list.setTextFilterEnabled(true);

    mobile_no_search.addTextChangedListener(new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {

            if (count < before) {
                // We're deleting char so we need to reset the adapter data
                profileAdapter.resetData();
            }

            profileAdapter.getFilter().filter(s.toString());

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    });
}













    class ProfileAdapter extends ArrayAdapter<ProfileModel> {
        Context context;
        private File sdCard = Environment.getExternalStorageDirectory();
        public String response;
        public boolean asyncCheck = false;
        public String ContentCode;
        public String mobileNo;
        File pathName = null;

        private Filter planetFilter;
        private List<ProfileModel> origPlanetList;
        private List<ProfileModel> planetList;

        public ProfileAdapter(Context context) {
            super(context, R.layout.row_profile, AllprofileListVector.getAllProfilelist());
            this.context = context;
            this.context = context;
            this.planetList = AllprofileListVector.getAllProfilelist();
            this.origPlanetList = AllprofileListVector.getAllProfilelist();

        }

        public int getCount() {
            return planetList.size();
        }

        public ProfileModel getItem(int position) {
            return planetList.get(position);
        }

        public long getItemId(int position) {
            return planetList.get(position).hashCode();
        }

         class ViewHolder {

            private TextView rank_tv;
            private TextView name_tv;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            View v = convertView;

            if (v == null) {
                final LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.row_profile, null);
                holder = new ViewHolder();

                holder.rank_tv = (TextView) v.findViewById(R.id.rank_tv);
                holder.name_tv = (TextView) v.findViewById(R.id.name_tv);

                v.setTag(holder);
            } else {
                holder = (ViewHolder) v.getTag();
            }
            if (position < AllprofileListVector.getAllProfilelist().size()) {
                ProfileModel query = planetList.get(position);
                holder.rank_tv.setText(query.getRank());
                holder.name_tv.setText(query.getName());


            }

            return v;
        }

        public void resetData() {
            planetList = origPlanetList;
        }
        /*
         * We create our filter
         */

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
                    List<ProfileModel> nPlanetList = new ArrayList<ProfileModel>();
                    for (ProfileModel p : planetList) {

                        if (p.getAppoinment().toUpperCase().startsWith(constraint.toString().toUpperCase())||
                                p.getName().toUpperCase().contains(constraint.toString().toUpperCase())) {
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
                    planetList = (List<ProfileModel>) results.values;
                    notifyDataSetChanged();
                }

            }

        }
    }

}
