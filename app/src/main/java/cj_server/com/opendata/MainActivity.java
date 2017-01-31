package cj_server.com.opendata;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cj_server.com.opendata.Adapters.DistrictAdapter;
import cj_server.com.opendata.General__Functions.Constants;
import cj_server.com.opendata.General__Functions.DividerItemDecoration;
import cj_server.com.opendata.General__Functions.VolleySingleton;
import cj_server.com.opendata.Pojo.Data;
import cj_server.com.opendata.Pojo.Distict;

public class MainActivity extends AppCompatActivity {
    private RecyclerView m_recylerView;
    private Toolbar toolbar;
    private DistrictAdapter  adapter;
    private ArrayList<Data> m_datalist = new ArrayList<>();
    private String url = "http://opendata.arcgis.com/datasets/9345239789134fcbbdf829b4aa6cd895_0/FeatureServer/0/" +
            "query?where=1%3D1&outFields=District,rural/urban,Male,Female,Total,Households,Area_in_Sq_Km,_Household_Density_,Population_Density," +
            "Gender_Index,County,Location,Location_1,Province&outSR=4326&f=json";
    private String TAG = "Main";
    private String tag_json_obj = "json_obj_req";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar =  (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Population Per Districts");

        }

        adapter =  new DistrictAdapter(getApplicationContext(),m_datalist);
        m_recylerView = (RecyclerView) findViewById(R.id.districtsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        m_recylerView.setLayoutManager(mLayoutManager);
        m_recylerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        m_recylerView.addOnItemTouchListener(new RecyclerTouchListener(this, m_recylerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Data  data  = m_datalist.get(position);
                Toast.makeText(MainActivity.this, "Selected:"+data.getCounty(),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,Analytics.class);
                intent.putExtra(Constants.DISTRICT,data.getDistrict());
                intent.putExtra(Constants.CATEGORY,data.getCategory());
                intent.putExtra(Constants.COUNTY, data.getCounty());
                intent.putExtra(Constants.NO_OF_MALE,data.getNo_of_male());
                intent.putExtra(Constants.NO_OF_FEMALE, data.getNo_of_female());
                intent.putExtra(Constants.TOTAL_POPULATION,data.getTotal_population());
                intent.putExtra(Constants.LOCATION,data.getLocation());
                startActivity(intent);


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        m_recylerView.setAdapter(adapter);

        FetchData();




    }
    private void FetchData(){
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        JsonObjectRequest myrequest = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
//                        Log.d(TAG, response.toString());
                        pDialog.hide();
                        if(response.length()>0){
                            try {
                                JSONArray array = response.getJSONArray("features");
                                for(int i =0; i<array.length();i++){
                                    String child = array.getJSONObject(i).getString("attributes");
                                    JSONObject a = new JSONObject(child);
                                    String district = a.getString(Constants.DISTRICT);
                                    String category = a.getString(Constants.CATEGORY);
                                    int male  = a.getInt(Constants.NO_OF_MALE);
                                    int female = a.getInt(Constants.NO_OF_FEMALE);
                                    int total_population = a.getInt(Constants.TOTAL_POPULATION);
                                    String location = a.getString(Constants.LOCATION);
                                    String county = a.getString(Constants.COUNTY);

                                    Data data = new Data(district,category,male,female,total_population,county,location);
                                    m_datalist.add(data);
                                    adapter.notifyDataSetChanged();


                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }else{
                            Toast.makeText(getApplicationContext(),"No data Found",Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                pDialog.hide();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        VolleySingleton.getInstance().addToRequestQueue(myrequest, tag_json_obj);

    }
}
