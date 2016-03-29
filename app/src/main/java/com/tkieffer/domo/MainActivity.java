package com.tkieffer.domo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private String server = "http://fyx.ddns.net";
    private String port = ":8000";

    List<com.tkieffer.domo.Switch> list;

    ViewManager vm;
    ListView lv;

    RequestQueue q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);

        q = Volley.newRequestQueue(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        list = new ArrayList<>(); // clear existing data
        //list.add(new com.tkieffer.domo.Switch("TV",12,true));
        //list.add(new com.tkieffer.domo.Switch("Lampe",15,false));

        // launch init request -> response handled below
        initRequest();

        //update view


    }

    Response.Listener<JSONObject> rListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Response received : ");
                System.out.println(response);
                if(response.has("0")){
                    handleInitResponse(response);
                    SwitchAdapter swa = new SwitchAdapter(MainActivity.this,list);
                    lv.setAdapter(swa);
                }
            }
        };
        Response.ErrorListener eListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error !");
                System.out.println(error.toString());
                Toast.makeText(MainActivity.this,"Server unreachable !",Toast.LENGTH_SHORT).show();
            }
        };

        public void handleInitResponse(JSONObject res){
            System.out.println("Received response to init request");
            res.remove("0");
            Iterator<String> i = res.keys();
            while(i.hasNext()){
                String key = i.next();
                Integer id = null;
                String name = null;
                boolean status = false;
                JSONObject inter = null;
                try {
                    inter = (JSONObject) res.get(key);

                    System.out.println("Handling : ");
                    System.out.println(inter);

                    id = inter.getInt("id");
                    name = inter.getString("name");
                    status = inter.getBoolean("status");

                } catch (JSONException e){
                    e.printStackTrace();
                }
                list.add(new com.tkieffer.domo.Switch(name, id, status));
            }
            Toast.makeText(MainActivity.this,"Ready !",Toast.LENGTH_SHORT).show();
        }

        public void sendRequest(final String url){
            System.out.println("Requesting : "+url);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url,null,rListener,eListener);
            q.add(request);
        }

        public void switchInter(int num){
            System.out.println("switching inter " + num);
            String url = server+port+"/"+num+"/switch";
            sendRequest(url);
        }

        public void initRequest() {
            String url = server+port+"/init";
            sendRequest(url);
        }

    /*View.OnClickListener btListener = new View.OnClickListener() { // TODO : à refaire !
        @Override
        public void onClick(View v) {
            int internum = 0;
            // Si l'inter existe on récupère son ID
            if(btList.containsKey(v)) switchInter(btList.get(v));
            System.out.println("Click on button "+internum);
        }
    };*/

   /* public LinearLayout renderButton(int id, String name, boolean status){
        LinearLayout ly = new LinearLayout(MainActivity.this);
        ly.setOrientation(LinearLayout.HORIZONTAL);
        ly.setGravity(Gravity.CENTER);

        TextView txt = new TextView(MainActivity.this);
        txt.setText(name);
        ly.addView(txt);

        ToggleButton bt = new ToggleButton(MainActivity.this);
        bt.setEnabled(status);
        ly.addView(bt);

        bt.setOnClickListener(btListener);
        btList.put(bt,id);

        return ly;
    };*/
}
