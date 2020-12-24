package com.example.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class TextSpeech extends Activity implements TextToSpeech.OnInitListener{

    EditText et;
    Button b1,b2;
    TextView tv;
    TextToSpeech tts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_speech);

        et=findViewById(R.id.et);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        tv=findViewById(R.id.tv);
        tts=new TextToSpeech(getApplicationContext(),this);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg=et.getText().toString();
                if(msg.equals(""))
                {
                    tts.speak("Enter Your Message First",TextToSpeech.QUEUE_ADD,null,null);
                }
                else
                {
                    tts.speak(msg,TextToSpeech.QUEUE_ADD,null,null);
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                ii.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                startActivityForResult(ii,121);
            }
        });

    }

    @Override
    protected void onActivityResult(int req,int res,Intent data) {
        super.onActivityResult(req, res, data);
        if (req == 121 && res == RESULT_OK) {
            ArrayList<String> list = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String speech = "";
            for (int i = 0; i < list.size(); i++) {
                speech = speech + list.get(i) + "\n";
            }
            tv.setText(speech);
            if (speech.indexOf("open YouTube") != -1) {
                Intent ii = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com"));
                startActivity(ii);
            }

            else if(speech.indexOf("call Mom")!=-1)
            {
                Intent ii=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+"8328678918"));
                startActivity(ii);
            }

            else if(speech.indexOf("call Dad")!=-1)
            {
                Intent ii=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+"3467154095"));
                startActivity(ii);
            }

            else if(speech.indexOf("call Yash")!=-1)
            {
                Intent ii=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+"3468138750"));
                startActivity(ii);
            }

        }
    }

    @Override
    public void onInit(int i) {

    }
}
