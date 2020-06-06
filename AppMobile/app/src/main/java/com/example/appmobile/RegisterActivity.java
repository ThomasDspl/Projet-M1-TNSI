package com.example.appmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (true){ //connexion ok
                    SaveSharedPreference.setUserName(getBaseContext(), "testuser");
                }
                Intent intent=new Intent(getBaseContext(),MainActivity.class);
                intent.putExtra("tabToSelect", 1);
                startActivity(intent);
            }
        });
    }
}
