package com.example.googlemapsgoogleplaces;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
public static final String TAG="MainActivtiy";
public static final int ERROR_DIALOG_REQUEST=9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            if(isServiceOK()){
                init();

        }
        }

    private void init() {
            findViewById(R.id.map_button_id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(getApplicationContext(),MapActivity.class);
                    startActivity(i);
                }
            });
    }

    private boolean isServiceOK() {

        int available= GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if(available== ConnectionResult.SUCCESS){
            //google play service is available
            Log.d(TAG,"isServiceOk : Google Play service is working correctly");
            return true;
        }else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG,"isServiceOK : an error occured but we can resolve it");
            Dialog dialog=GoogleApiAvailability.getInstance().getErrorDialog(this,available,ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else {
            Toast.makeText(this, "You cant make map request", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}
