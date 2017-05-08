package com.example.a1kayat34.pointofinterest_app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {

    MapView map;
    ItemizedIconOverlay<OverlayItem> items;
    ItemizedIconOverlay.OnItemGestureListener<OverlayItem> markerGestureListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        map = (MapView) findViewById(R.id.map_id);

        map.setBuiltInZoomControls(true);
        map.getController().setZoom(13);
        map.getController().setCenter(new GeoPoint(50.9027, -1.4048));

        markerGestureListener = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>()
        {
            public boolean onItemLongPress(int i, OverlayItem item){
                Toast.makeText(MainActivity.this, item.getSnippet(), Toast.LENGTH_LONG).show();
                return true;
            }

            public boolean onItemSingleTapUp(int i, OverlayItem item){
                Toast.makeText(MainActivity.this, item.getSnippet(), Toast.LENGTH_SHORT).show();
                return true;
            }


        };

        items = new ItemizedIconOverlay<OverlayItem>(this, new ArrayList<OverlayItem>(),markerGestureListener);
        OverlayItem southampton = new OverlayItem("Southampton", "City of southampton", new GeoPoint(50.9027, -1.4048));
        //fernhurst.setMarker(getResources().getDrawable(R.drawable.marker));
        items.addItem(southampton);
        map.getOverlays().add(items);

    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onClick(View view) {


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //POIMapFragment mapFragment = (POIMapFragment) getFragmentManager().findFragmentById(R.id.mapFragmentID);

        if(item.getItemId() == R.id.add_poi_id){
            //when item add_poi item selected launch second activity
            Intent intent = new Intent (this, AddPointOfInterestActivity.class);
            startActivityForResult(intent, 1);
            return true;
        }
        if(item.getItemId() == R.id.save_poi_id){



            return true;
        }
        return false;
    }

    protected void onActivityResult(int requestetCode, int resultCode, Intent intent){
        if (requestetCode == 1){
            if (resultCode == RESULT_OK){
                Bundle extras = intent.getExtras();

                String name = intent.getExtras().getString("com.example.a1kayat34.name_input");
                String type = intent.getExtras().getString("com.example.a1kayat34.type_input");
                String description = intent.getExtras().getString("com.example.a1kayat34.description_input");

                double lat = map.getMapCenter().getLatitude();
                double lon = map.getMapCenter().getLongitude();

                items = new ItemizedIconOverlay<OverlayItem>(this, new ArrayList<OverlayItem>(),markerGestureListener);

                OverlayItem newPlace = new OverlayItem(name, description, new GeoPoint(lat,lon));

                items.addItem(newPlace);
                map.getOverlays().add(items);

                //Saving markers to a file

                try {
                    PrintWriter pw =
                            new PrintWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() + "/data.csv", true));
                    //Load the data from ADD POI activity

                    for(int i=0; i<items.size(); i++){
                        OverlayItem item = items.getItem(i);
                        // item.getTitle() //-the name of the item
                        // item.getSnippet() //- the description
                        // item.getPoint().getLatitude() //- latitude

                        pw.println(item.getTitle()+","+item.getSnippet()+","+item.getPoint().getLatitude()+","+item.getPoint().getLongitude());
                        pw.close();
                    }

                }
                catch (IOException e){
                    new AlertDialog.Builder(this).setMessage("ERROR: " + e).
                            setPositiveButton("OK", null).show();

                }
            }
        }

    }




}
