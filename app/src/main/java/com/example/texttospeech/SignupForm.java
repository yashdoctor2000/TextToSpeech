package com.example.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupForm extends Activity {

    EditText etRegNa,etRegUs,etRegPa,etRegAge,etRegPh;
    Button btReg,btCan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        etRegNa=findViewById(R.id.etRegNa);
        etRegAge=findViewById(R.id.etRegAge);
        etRegUs=findViewById(R.id.etRegus);
        etRegPa=findViewById(R.id.etRegPa);
        etRegPh=findViewById(R.id.etRegPh);
        btReg=findViewById(R.id.btReg);
        btCan=findViewById(R.id.btCan);
        Intent ii=getIntent();
        String us=ii.getStringExtra("username");
        etRegUs.setText(us);


        btReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=etRegUs.getText().toString();
                String name=etRegNa.getText().toString();
                String phone=etRegPh.getText().toString();
                String age=etRegAge.getText().toString();
                String password=etRegPa.getText().toString();
                etRegAge.setText("");
                etRegNa.setText("");
                etRegPa.setText("");
                etRegUs.setText("");
                etRegPh.setText("");

                int flag=0;


                String expAge="^[0-9]{2}$";
                Pattern patage=Pattern.compile(expAge);
                Matcher matchage= patage.matcher(age);

                if(matchage.matches()==false)

                {
                    Toast.makeText(getApplicationContext(),"Invalid User",Toast.LENGTH_LONG).show();
                    etRegPh.requestFocus();
                    flag=1;

                }
                String expName="^[A-Z a-z]{3,15}$";
                Pattern patname=Pattern.compile(expName);
                Matcher matchName= patname.matcher(name);

                if(matchName.matches()==false)

                {
                    Toast.makeText(getApplicationContext(),"Invalid User",Toast.LENGTH_LONG).show();
                    etRegNa.requestFocus();
                    flag=1;

                }
                String expPh="^[3-9]{1}[0-9]{9}$";
                Pattern patPh=Pattern.compile(expPh);
                Matcher matchPh= patPh.matcher(phone);

                if(matchPh.matches()==false)
                {
                    Toast.makeText(getApplicationContext(),"INVALID PHONE NUMBER",Toast.LENGTH_LONG).show();
                    etRegPh.requestFocus();
                    flag=1;
                }

                if(flag==0)
                {
                    SharedPreferences shad =getSharedPreferences("doctorfile", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit=shad.edit();
                    edit.putString("myuser",username);
                    edit.putString("mypass",password);

                    edit.commit();

                    Toast.makeText(getApplicationContext(),"User Registered....",Toast.LENGTH_LONG).show();
                }

            }
        });
        btCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etRegAge.setText("");
                etRegNa.setText("");
                etRegPa.setText("");
                etRegUs.setText("");
                etRegPh.setText("");
            }
        });


    }
}