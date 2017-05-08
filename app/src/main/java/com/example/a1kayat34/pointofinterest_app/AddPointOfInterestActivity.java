package com.example.a1kayat34.pointofinterest_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPointOfInterestActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_point_of_interest);

        Button add = (Button) findViewById(R.id.add_button);
        add.setOnClickListener(this);

        Button cancel = (Button) findViewById(R.id.cancel_button);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add_button){

            EditText name_input = (EditText) findViewById(R.id.name_input);
            EditText type_input = (EditText) findViewById(R.id.type_input);
            EditText description_input = (EditText) findViewById(R.id.description_input);

            String name = name_input.getText().toString();
            String type = type_input.getText().toString();
            String description = description_input.getText().toString();

            Intent intent = new Intent();
            Bundle bundle = new Bundle();

            bundle.putString("com.example.a1kayat34.name_input", name);
            bundle.putString("com.example.a1kayat34.type_input", type);
            bundle.putString("com.example.a1kayat34.description_input", description);

            intent.putExtras(bundle);

            setResult(RESULT_OK, intent);
            finish();
        }
        if (view.getId() == R.id.cancel_button){

        }
    }
}
