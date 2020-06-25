package com.benny.submissionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OutputActivity extends Activity {

    TextView outputText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        outputText = (TextView) findViewById(R.id.txt_Output);
        Intent intent = getIntent();
        String out = (String) intent.getSerializableExtra("Output");
        outputText.setText(out);
    }
}
