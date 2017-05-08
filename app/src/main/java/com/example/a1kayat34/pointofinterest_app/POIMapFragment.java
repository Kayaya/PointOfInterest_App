package com.example.a1kayat34.pointofinterest_app;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

/**
 * Created by 1kayat34 on 02/05/2017.
 */
public class POIMapFragment extends Fragment implements View.OnClickListener {

    MapView map;
    ItemizedIconOverlay<OverlayItem> items;
    ItemizedIconOverlay.OnItemGestureListener<OverlayItem> markerGestureListener;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Activity activity = getActivity();
    }
    public View onCreateView (LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.map_frag_layout, parent);
    }
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        final Activity activity = getActivity();

        Configuration.getInstance().load(activity, PreferenceManager.getDefaultSharedPreferences(activity));

        map = (MapView) getView().findViewById(R.id.map_frag_id);
        map.setBuiltInZoomControls(true);
        map.getController().setZoom(13);
        map.getController().setCenter(new GeoPoint(51.05, -0.72));

        // Now you have an activity, you can create the map as before...
        EditText name = (EditText) getView().findViewById(R.id.name_input);
        EditText type = (EditText) getView().findViewById(R.id.type_input);
        EditText description = (EditText) getView().findViewById(R.id.description_input);

        markerGestureListener = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>()
        {
            public boolean onItemLongPress(int i, OverlayItem item){
                Toast.makeText(activity, item.getSnippet(), Toast.LENGTH_LONG).show();
                return true;
            }

            public boolean onItemSingleTapUp(int i, OverlayItem item){
                Toast.makeText(activity, item.getSnippet(), Toast.LENGTH_SHORT).show();
                return true;
            }


        };

        String poi_name = name.getText().toString();
        String poi_type = type.getText().toString();
        String poi_description = description.getText().toString();

        items = new ItemizedIconOverlay<OverlayItem>(activity, new ArrayList<OverlayItem>(),markerGestureListener);
        OverlayItem fernhurst = new OverlayItem("Fernhurst", "Vilage", new GeoPoint(51.05, -0.72));
    //    fernhurst.setMarker(getResources().getDrawable(R.drawable.marker));
        map.getOverlays().add(items);

    }

    public void getNewLocation(double latitude, double longitude){
        GeoPoint ll = new GeoPoint(latitude, longitude);
        map.getController().setCenter(ll);
    }

    @Override
    public void onClick(View view) {

    }
}
