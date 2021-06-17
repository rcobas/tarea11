package com.example.tarea11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import static com.android.volley.Request.*;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private RequestQueue request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        request = Volley.newRequestQueue(this);
        tv1 = (TextView) findViewById(R.id.tv1);

    }

    public void recuperar(View view) {

        tv1.setText("");
        String url = "http://10.0.2.2:80/Tarea11/personas.json";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int f = 0; f < response.length(); f++) {
                    try {

                        JSONObject objeto = new JSONObject(response.get(f).toString());


                        tv1.append(objeto.getString("nombre") + " ");
                        tv1.append(objeto.getString("apellidos") + "\n");
                        tv1.append("Edad: " + objeto.getString("edad"));
                        tv1.append("\n");
                        tv1.append("\n");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

        request.add(jsonArrayRequest);

    }
}