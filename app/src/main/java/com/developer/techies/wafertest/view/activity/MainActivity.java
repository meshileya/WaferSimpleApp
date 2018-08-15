package com.developer.techies.wafertest.view.activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.developer.techies.wafertest.R;
import com.developer.techies.wafertest.adapter.CountryListAdapter;
import com.developer.techies.wafertest.adapter.swiper.SwipeHelper;
import com.developer.techies.wafertest.model.CountryModel;
import com.developer.techies.wafertest.utils.listener.ItemListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemListener<CountryModel> {

    CountryListAdapter adapter;
    RecyclerView recyclerView;
    List<CountryModel> countryModels;

    //Todo Create view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    //Todo Initialize the view
    public void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        initData();
    }

    //Todo Initialize data to be displayed on the view
    private void initData() {
        adapter = new CountryListAdapter(null);

        adapter.attachListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        loadData();
        initSwiper();
    }

    //Todo : Add the swipe event
    public void initSwiper() {
        SwipeHelper swipeHelper = new SwipeHelper(this, recyclerView) {
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                adapter.removeItem(viewHolder.getAdapterPosition());
                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        Color.parseColor("#9024a2"),
                        null
                ));
            }
        };

        swipeHelper.attachSwipe();
    }

    //Todo Fetch data using the JsonArrayRequest from the URL provided
    public void loadData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        countryModels = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest setUpRequest = new JsonArrayRequest("https://restcountries.eu/rest/v2/all",
                response -> {
                    if (response != null) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);

                                    CountryModel model = new CountryModel();
                                    model.setCountryName(jsonObject.getString("name"));
                                    model.setCountryCurrency(jsonObject.getJSONArray("currencies").getJSONObject(0).getString("name"));
                                    model.setCountryLanguage(jsonObject.getJSONArray("languages").getJSONObject(0).getString("name"));

                                    countryModels.add(model);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            adapter.update(countryModels);
                            progressDialog.dismiss();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, error -> error.printStackTrace()

        );

        int socketTimeout = 60000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        setUpRequest.setRetryPolicy(policy);

        queue.add(setUpRequest);
    }

    @Override
    public void onItemClick(CountryModel v) {
        //ToDo Display more details of the country other screen.

    }
}
