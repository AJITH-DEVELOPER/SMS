package com.example.sms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText mob,mes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.SEND_SMS )) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.SEND_SMS},1);
            }
            else
            {
ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},1);
            }
        }
        else
        {

        }
        mob=(EditText) findViewById(R.id.edittxt1);
        mes=(EditText) findViewById(R.id.edittxt2);
        btn=(Button) findViewById(R.id.button);
      btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String number = mob.getText().toString();
              String sms = mes.getText().toString();
              try {
                  SmsManager sm = SmsManager.getDefault();
                  sm.sendTextMessage(number, null, sms, null, null);
                  Toast.makeText(MainActivity.this, "SENT", Toast.LENGTH_LONG).show();
                  ;
              } catch (Exception e){
                  Toast.makeText(MainActivity.this,"FAILED",Toast.LENGTH_LONG).show();
              }
          }
      });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    switch (requestCode){
            case 1: {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "permission granted", Toast.LENGTH_LONG).show();


                }
            }
            else
            {
                Toast.makeText(this,"NO PERMISSION GRANTED",Toast.LENGTH_LONG).show();
            }
            return ;

            }
        }
    }

    }

