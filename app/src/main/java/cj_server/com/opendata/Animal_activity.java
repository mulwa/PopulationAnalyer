package cj_server.com.opendata;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cj_server.com.opendata.Adapters.AnimalAdapter;
import cj_server.com.opendata.General__Functions.Constants;
import cj_server.com.opendata.General__Functions.DividerItemDecoration;
import cj_server.com.opendata.General__Functions.VolleySingleton;
import cj_server.com.opendata.Pojo.Animal;

public class Animal_activity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView m_recy_list;
    private Button m_btn_error;
    private TextView m_tv_error;
    private String url= "http://opendata.arcgis.com/datasets/7acafcec6b2d41e990c200dbb045768b_0/FeatureServer/0/query?where=1%3D1&outFields=*&outSR=4326&f=json";
    private String tag_json_obj = "json_obj_req";
    private ArrayList<Animal>my_list = new ArrayList<>();
    private AnimalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_activity);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Animal Population");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
        m_tv_error = (TextView)findViewById(R.id.connection_error);
        m_btn_error = (Button)findViewById(R.id.btn_tryAgain);
        m_recy_list = (RecyclerView)findViewById(R.id.animal_list);


        adapter = new AnimalAdapter(my_list,getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        m_recy_list.setLayoutManager(mLayoutManager);
        m_recy_list.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        fetchData();
        m_recy_list.setAdapter(adapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    private void fetchData(){
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        JsonObjectRequest my_request =  new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pDialog.dismiss();
                try {
                    JSONArray rootArray =  response.getJSONArray("features");
                    for(int i=0;i<rootArray.length();i++){
                        String child = rootArray.getJSONObject(i).getString("attributes");
                        JSONObject my_object = new JSONObject(child);
                        String district = my_object.getString(Constants.ANIMAL_DISTRICT);
                        String province = my_object.getString(Constants.PROVINCE);
                        String county = my_object.getString(Constants.ANIMAL_COUNTY);
                        int cattle = my_object.getInt(Constants.CATTLE);
                        int sheep = my_object.getInt(Constants.SHEEP);
                        int pigs = my_object.getInt(Constants.PIGS);
                        int chicken = my_object.getInt(Constants.CHICKEN);
                        int commercial_chicken = my_object.getInt(Constants.COMMERCIAL_CHICKEN);
                        int camel = my_object.getInt(Constants.CAMELS);
                        int bee = my_object.getInt(Constants.BEE);
                        int donkey = my_object.getInt(Constants.DISTRICT);
                        int goat = my_object.getInt(Constants.GOATS);

                        Animal animal= new Animal(camel,cattle,county,bee,goat,district,sheep,chicken,commercial_chicken,province,pigs,donkey);
                        my_list.add(animal);
                        adapter.notifyDataSetChanged();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        VolleySingleton.getInstance().addToRequestQueue(my_request, tag_json_obj);
    }

    private void AddData(){
        Animal animal = new Animal(10,20,"meru",20,20,"home",60,45,69,"Macha",50,569);
        my_list.add(animal);
        Animal ab = new Animal(10,20,"meru",20,20,"home",60,45,69,"Macha",50,569);
        my_list.add(ab);
        adapter.notifyDataSetChanged();
    }
}
