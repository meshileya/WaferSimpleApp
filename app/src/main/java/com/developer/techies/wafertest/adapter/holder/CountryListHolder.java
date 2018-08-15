package com.developer.techies.wafertest.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.developer.techies.wafertest.R;


public class CountryListHolder extends RecyclerView.ViewHolder {

    public TextView mCountryNameTv;
    public TextView mCountryCurrencyTv;
    public TextView mCountryLanguageTv;

    public CountryListHolder(View itemView) {
        super(itemView);
        mCountryNameTv = itemView.findViewById(R.id.country_name);
        mCountryCurrencyTv = itemView.findViewById(R.id.country_currency);
        mCountryLanguageTv = itemView.findViewById(R.id.country_language);
    }

}
