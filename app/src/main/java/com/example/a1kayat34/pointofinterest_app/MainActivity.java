package com.example.a1kayat34.pointofinterest_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        POIMapFragment mapFragment = (POIMapFragment) getFragmentManager().findFragmentById(R.id.mapFragmentID);

        if(item.getItemId() == R.id.add_poi_id){
            //when item add_poi item selected launch second activity
            Intent intent = new Intent (this, AddPointOfInterestActivity.class);
            startActivity(intent);

            return true;
        }
        if(item.getItemId() == R.id.save_poi_id){

            return true;
        }
        return false;
    }

}
