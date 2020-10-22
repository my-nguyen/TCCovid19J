package com.nguyen.tccovid19j;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyen.tccovid19j.databinding.FragmentRegionStatsBinding;

public abstract class RegionStatsFragment extends Fragment {

    protected FragmentRegionStatsBinding binding;
    protected CountriesAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegionStatsBinding.inflate(inflater, container, false);
        // adapter = new CountriesAdapter(getContext());
        adapter = CountriesAdapter.getInstance(getContext());
        binding.recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                fetchPage(page+1);
            }
        });
        // if switching to this screen (Region Stats) for the first time, fetch first page and display it
        // otherwise, the fetch has been done, so do nothing and the pages will be displayed
        if (adapter.countries.size() == 0) {
            fetchPage(1);
        }

        return binding.getRoot();
    }

    abstract void fetchPage(int page);
}