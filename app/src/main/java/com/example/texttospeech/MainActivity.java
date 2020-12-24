package com.example.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity{

    EditText username,password;
    Button login,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        signup=findViewById(R.id.signup);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String log=username.getText().toString();
                String pass=password.getText().toString();

                SharedPreferences shad=getSharedPreferences("doctorfile", Context.MODE_PRIVATE);

                String us=shad.getString("myuser","nouser");
                String pa=shad.getString("mypass","nopass");

                if(us.equals("nouser") && pa.equals("nopass"))
                {
                    Toast.makeText(getApplicationContext(),"You are no registered user",Toast.LENGTH_LONG).show();
                }
                else if(!us.equals(log) || !pa.equals(pass))
                {
                    Toast.makeText(getApplicationContext(),"Unauthorised user",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Authorised User",Toast.LENGTH_LONG).show();
                    Intent i2=new Intent(getApplicationContext(),TextSpeech.class);
                    startActivity(i2);


                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String log=username.getText().toString();
                Intent i1=new Intent(getApplicationContext(),SignupForm.class);
                i1.putExtra("username",log);
                startActivity(i1);
            }
        });

    }


}