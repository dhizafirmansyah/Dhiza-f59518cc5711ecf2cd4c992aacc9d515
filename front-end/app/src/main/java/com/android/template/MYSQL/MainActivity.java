package com.android.template.MYSQL;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    TextView textViewregistrasi;
    EditText editTextUsername, editTextPassword;
    Button buttonLogin;
    String username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewregistrasi = (TextView) findViewById(R.id.textViewRegistrasi);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = editTextUsername.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();
                Konfigurasi.CEKLOGIN = username;


                getData();

                //startActivity(new Intent(getApplicationContext(),Registrasi.class));

            }
        });

        textViewregistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Registrasi.class));
            }
        });


    }

    private void getData(){
        class GetData extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Please...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showData(s);
                if(s.equals("Benar")){
                    Toast.makeText(MainActivity.this,"Selamat Login Berhasil",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,"Username atau Password Anda Salah,",Toast.LENGTH_LONG).show();
                }
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
                showData(s);

            }



            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_EMP_USERNAME,username);
                params.put(Konfigurasi.KEY_EMP_PASSWORD,password);
                params.put("login_time",getTime().toString());
                params.put("login_state","1");
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Konfigurasi.URL_AUTH, params);
                return res;
            }
        }
        GetData gd = new GetData();
        gd.execute();
        Konfigurasi.SESSION = username;
    }



    private void showData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String tes = c.getString("password");
            Log.d("coba", tes);





        } catch (JSONException e) {
            e.printStackTrace();
        }
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
}
