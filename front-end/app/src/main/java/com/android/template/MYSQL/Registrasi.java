package com.android.template.MYSQL;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Registrasi extends AppCompatActivity {
    EditText editTextUsernameRegis, editTextEmail, editTextPasswordRegis, editTextPasswordRetypeRegis;
    Button buttonRegisterRegis;
    String usernameKirim,passwordKirim,emailKirim,cekPassword1,cekPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        editTextUsernameRegis = (EditText) findViewById(R.id.editTextUsrnameRegis);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPasswordRegis = (EditText) findViewById(R.id.editTextPasswordRegis);
        editTextPasswordRetypeRegis = (EditText) findViewById(R.id.editTextPasswordRegisReType);
        buttonRegisterRegis = (Button) findViewById(R.id.buttonRegisterRegis);

        buttonRegisterRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 cekPassword1 = editTextPasswordRegis.getText().toString().trim();
                 cekPassword2 = editTextPasswordRetypeRegis.getText().toString().trim();
                 if(cekPassword1.equals(cekPassword2)){
                     if(cekPassword1.length()>=6) {
                         Registrasi();
                     }else{
                         Toast.makeText(Registrasi.this,"Password Minimal Harus Terdiri Dari 6 Karakter",Toast.LENGTH_LONG).show();
                     }
                 }else{
                     Toast.makeText(Registrasi.this,"Password yang anda masukkan tidak sama",Toast.LENGTH_LONG).show();
                 }



            }
        });
    }

    private void Registrasi(){

         usernameKirim = editTextUsernameRegis.getText().toString().trim();
         emailKirim = editTextEmail.getText().toString().trim();
         passwordKirim = editTextPasswordRegis.getText().toString().trim();


        class AddAkun extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Registrasi.this,"Simpan Data...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Registrasi.this,s,Toast.LENGTH_LONG).show();
                if(s.equals("Registrasi Berhasil")){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    Toast.makeText(Registrasi.this,"Silahkan Login :)",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_EMP_USERNAME,usernameKirim);
                params.put(Konfigurasi.KEY_EMP_PASSWORD,passwordKirim);
                params.put(Konfigurasi.KEY_EMP_EMAIL,emailKirim);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Konfigurasi.URL_TAMBAH, params);
                return res;
            }
        }

        AddAkun ak = new AddAkun();
        ak.execute();
    }

}
