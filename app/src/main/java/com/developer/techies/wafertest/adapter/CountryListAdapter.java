package com.developer.techies.wafertest.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.techies.wafertest.R;
import com.developer.techies.wafertest.adapter.holder.CountryListHolder;
import com.developer.techies.wafertest.model.CountryModel;
import com.developer.techies.wafertest.utils.listener.ItemListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListHolder> {

    private List<CountryModel> items;
    private ItemListener<CountryModel> listener;

    public void attachListener(ItemListener<CountryModel> listener) {
        this.listener = listener;
    }

    public CountryListAdapter(List<CountryModel> items) {
        this.items = items;
    }

    public void update(List<CountryModel> items) {
        if (this.items != null)
            this.items.clear();
        this.items = items;
        this.notifyDataSetChanged();
    }

    public void removeItem(int position) {
        this.items.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, this.items.size());
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_countries, parent, false);
        return new CountryListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryListHolder holder, int position) {

        CountryModel item = items.get(position);
        holder.mCountryNameTv.setText(item.getCountryName());
        holder.mCountryCurrencyTv.setText("Currency : " + item.getCountryCurrency());
        holder.mCountryLanguageTv.setText("Language : " + item.getCountryLanguage());

        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
    }

    @Override
    public int getItemCount() {
        return this.items != null ? items.size() : 0;
    }

}
