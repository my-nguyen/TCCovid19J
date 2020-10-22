package com.nguyen.tccovid19j;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyen.tccovid19j.databinding.ItemCountryBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemCountryBinding binding;

        public ViewHolder(ItemCountryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(this);
        }

        public void bind(Country country) {
            Picasso.get().load(country.flag).into(binding.countryFlag);
            binding.countryName.setText(country.country);
            String text = context.getResources().getString(R.string.label_last_update, lastUpdate);
            binding.countryLastUpdate.setText(text);
            binding.countryCount.setText(Utils.toString(country.totalCases));
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Country country = countries.get(position);
                BottomSheetFragment.show(context, country);
            }
        }
    }

    public int currentPage;
    public int totalPages;
    private String lastUpdate;
    public List<Country> countries;
    private Context context;

    private static final String TAG = "CountriesAdapter";

    private CountriesAdapter(Context context) {
        this.context = context;
        countries = new ArrayList();
    }

    public void update(Data data) {
        if (data.pagination.currentPage == 1) {
            totalPages = data.pagination.totalPages;
            lastUpdate = data.lastUpdate;
        }
        Log.d(TAG, "update, current page: " + data.pagination.currentPage);
        currentPage = data.pagination.currentPage;
        int size = getItemCount();
        this.countries.addAll(data.countries);
        notifyItemRangeInserted(size, data.countries.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemCountryBinding binding = ItemCountryBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Country country = countries.get(position);
        holder.bind(country);
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public static CountriesAdapter getInstance(Context context) {
        if (instance == null) {
            instance = new CountriesAdapter(context);
        }
        return instance;
    }

    private static CountriesAdapter instance;
}
