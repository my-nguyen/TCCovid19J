package com.nguyen.tccovid19j;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nguyen.tccovid19j.databinding.FragmentCovidMapBinding;

public abstract class CovidMapFragment extends Fragment implements OnMapReadyCallback {
    private static final String TAG = "CovidMapFragment";

    protected GoogleMap map;
    protected FragmentCovidMapBinding binding;

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        /*// Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        Log.d(TAG, "loading all countries coordinate from CSV");
        Coordinates coordinates = Coordinates.getInstance(getContext(), "countries.csv");

        Log.d(TAG, "adding a marker to each country");
        LatLng firstLatLng = null;
        CountriesAdapter adapter = CountriesAdapter.getInstance(getContext());
        // iterate and add marker to each country
        for (int i = 0; i < adapter.countries.size(); i++) {
            Country country = adapter.countries.get(i);
            // find lat-long based on country name abbreviation
            LatLng latLng = coordinates.toLatLng(country.countryAbbreviation);
            if (latLng != null) {
                // make a marker from lat-long found
                Marker marker = map.addMarker(new MarkerOptions().position(latLng).title(country.country));
                // record country index in marker
                marker.setTag(i);
                // save the first lat-lng if necessary
                if (firstLatLng == null) {
                    firstLatLng = latLng;
                }
            }
        }
        // move the camera to the first country in the list, which is USA
        map.moveCamera(CameraUpdateFactory.newLatLng(firstLatLng));

        // add click listener to markers
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // retrieve from marker index of country selected
                int index = (int)marker.getTag();
                // show bottom sheet of country selected
                BottomSheetFragment.show(getContext(), adapter.countries.get(index));
                return false;
            }
        });

        /*// take a snapshot of the google map and save it into file
        map.snapshot(new GoogleMap.SnapshotReadyCallback() {
            @Override
            public void onSnapshotReady(Bitmap bitmap) {
                snapShotFile = Utils.saveBitmap(getContext(), bitmap);
            }
        });*/

        binding.progressBar.setVisibility(View.GONE);
        // binding.covidMap.setVisibility(View.VISIBLE);
    };

    protected void loadMap() {
        Log.d(TAG, "loading map");
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(CovidMapFragment.this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCovidMapBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // first fetch all 220 countries before loading the map via mapFragment.getMapAsync()
        getAllCountries();
    }

    abstract void getAllCountries();
}