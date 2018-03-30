package com.osapps.layoutdisplayer;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.osapps.layoutdisplayer.recursionsearch.SharedPrefsHandler;

public class MainActivity extends AppCompatActivity {

    //activity on create
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.onPackageNameOkBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPackageName = ((EditText) findViewById(R.id.package_name_tv)).getText().toString();
                SharedPrefsHandler.getInstance(MainActivity.this).saveNewPackageName(newPackageName);
                Toast.makeText(getBaseContext(),getString(R.string.ok),Toast.LENGTH_SHORT).show();
            }
        });
    }

    //the accessibility service executioner
    public void onEnableAccClick(View view) {
        startActivityForResult(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS), 1);
    }

    // the onscreen button click
    public void onAccessibilityClicked(View view) {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(intent);
    }
}
