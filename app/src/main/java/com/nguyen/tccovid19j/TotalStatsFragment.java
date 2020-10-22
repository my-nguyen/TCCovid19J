package com.nguyen.tccovid19j;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nguyen.tccovid19j.databinding.FragmentTotalStatsBinding;

import static com.nguyen.tccovid19j.Utils.WRITE_EXTERNAL_STORAGE_PERMISSIONS_REQUEST;

public abstract class TotalStatsFragment extends Fragment {

    protected FragmentTotalStatsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTotalStatsBinding.inflate(inflater, container, false);

        fetchWorld();
        return binding.getRoot();
    }

    protected void bind(World world) {
        binding.confirmedLastUpdate.setText(world.lastUpdate);
        binding.confirmedCount.setText(Utils.toString(world.totalCases));
        binding.infectedLastUpdate.setText(world.lastUpdate);
        binding.infectedCount.setText(Utils.toString(world.currentlyInfected));
        binding.recoveredLastUpdate.setText(world.lastUpdate);
        binding.recoveredCount.setText(Utils.toString(world.recoveryCases));
        binding.deadLastUpdate.setText(world.lastUpdate);
        binding.deadCount.setText(Utils.toString(world.deathCases));
    }

    abstract void fetchWorld();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_share:
                Utils.shareScreenShot(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == WRITE_EXTERNAL_STORAGE_PERMISSIONS_REQUEST) {
            Utils.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}