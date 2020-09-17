package com.android.template.MYSQL;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {
    TextView tvJam;
    String Time;
    Button btHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvJam = (TextView) findViewById(R.id.tvWaktu);
        btHello =(Button) findViewById(R.id.btHello) ;


        Thread threadku = new Thread() {

            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvJam.setText(getTime());
                                getTime();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        threadku.start();

        btHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

    }
    public String getTime(){
        Calendar calendar = Calendar.getInstance();
        int Jam = calendar.get(Calendar.HOUR_OF_DAY);
        int Menit = calendar.get(Calendar.MINUTE);
        int Detik = calendar.get(Calendar.SECOND);
        int Tahun = calendar.get(Calendar.YEAR);
        int Bulan = calendar.get(Calendar.MONTH);
        int Tanggal = calendar.get(Calendar.DATE);

        String Time="";

        if(Menit<10){
            Time = Jam+":0"+Menit;
        }else if(Jam<10){
            Time = "0"+Jam+":"+Menit;

        }else if(Jam<10&&Menit<10){
            Time = "0"+Jam+":0"+Menit;
        }else {
            Time = Jam+":"+Menit;
        }
        Log.d("Coba", "Response = Jam :"+ Time+":"+Detik );
        return Time+":"+Detik;


    }
    private void getData(){
        class GetData extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity2.this,"Please...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showData(s);
                if(s.equals("1")){
                    Toast.makeText(MainActivity2.this,s.toString(),Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(MainActivity2.this,s,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                Toast.makeText(MainActivity2.this,s,Toast.LENGTH_LONG).show();
                showData(s);

            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_EMP_USERNAME,Konfigurasi.SESSION);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Konfigurasi.URL_CEK_LOGIN, params);
                return res;
            }
        }
        GetData gd = new GetData();
        gd.execute();
    }



    private void showData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String tes = c.getString("username");
            Log.d("coba", tes);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}