package com.etanixyz.etani;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
    EditText namaEt, perEt, alamatEt, posEt, teleponEt, emailEt, passEt,repassEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        namaEt = (EditText)findViewById(R.id.nama);
        perEt = (EditText)findViewById(R.id.per);
        alamatEt = (EditText)findViewById(R.id.alamat);
        posEt = (EditText)findViewById(R.id.pos);
        teleponEt = (EditText)findViewById(R.id.telepon);
        emailEt = (EditText)findViewById(R.id.email);
        passEt = (EditText)findViewById(R.id.password);
        repassEt = (EditText)findViewById(R.id.repassword);

        TextView loginScreen = (TextView) findViewById(R.id.btntoLogin);

        // Mengambil link ke Login form
        loginScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Menutup tampilan screen register
                // Beralih ke Login Screen/menutup screen register
                finish();
            }
        });
    }

    public void register(View v){
        String type = "register";
        String nama = namaEt.getText().toString();
        String per = perEt.getText().toString();
        String alamat = alamatEt.getText().toString();
        String pos = posEt.getText().toString();
        String telepon = teleponEt.getText().toString();
        String email = emailEt.getText().toString();
        String password = passEt.getText().toString();
        String repass = repassEt.getText().toString();

        if (password.equals(repass)){
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, nama, per, alamat, pos, telepon, email, password);
        }
        else {
            Toast.makeText(RegisterActivity.this, "Password Tidak Sama", Toast.LENGTH_LONG).show();
        }


    }
}
