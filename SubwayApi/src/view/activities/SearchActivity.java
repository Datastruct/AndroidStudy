package com.example.owner.myapplication.view.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.owner.myapplication.model.stnlistapi.LineList;
import com.example.owner.myapplication.model.stnlistapi.Station;
import com.example.owner.myapplication.presenter.SubwayApiService;
import com.example.owner.myapplication.presenter.searchactivity.SearchImp;
import com.example.owner.myapplication.presenter.ServiceGenerator;
import com.example.owner.myapplication.utils.InternetConnection;
import com.example.owner.myapplication.R;
import com.example.owner.myapplication.view.adapters.StationListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by owner on 2016-12-12.
 * SearchActivity
 */
public class SearchActivity extends Activity{

    private ArrayList<LineList> stationList;     //x(1,2,3...)line station arraylist
    private ArrayList<LineList> tempList;       //x(1,2,3...)line station temp arraylist
    private StationListAdapter adapter;
    private SearchImp mSearchImp;

    @BindView(R.id.list_view) ListView listView;
    @BindView(R.id.inputSearch) EditText exInputSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        init();
        setEvent();

    }

    private void init(){
        this.mSearchImp = new SearchImp(SearchActivity.this);
        //line name for API's input
        String line[] ={
                "1호선","2호선","3호선","4호선","5호선","6호선",
                "7호선","8호선","9호선","공항철도","분당선","경춘선",
                "인천1호선","수인선","경의중앙선","신분당선"
        };
        //make station list
        for (int i=0 ; i<line.length; i++) {
            //have to input API KEY(R.string.LIST_API_KEY)
            setStnListAPI(getString(R.string.LIST_API_KEY), line[i]);
        }
    }
    //Using API for making station list
    private void setStnListAPI(String apikey, final String linenum){
        if (InternetConnection.checkConnection(getApplicationContext())) {
            //Creating an object of our api interface
            SubwayApiService api = ServiceGenerator.getListApiService();

            /**
             * Calling JSON
             */
            Call<Station> call = api.getStationList(apikey, linenum);

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<Station>() {
                @Override
                public void onResponse(Call<Station> call, Response<Station> response) {

                    if(response.isSuccessful()) {
                        /**
                         * Got Successfully
                         */
                        //load line by line and append to total list
                        if(stationList == null) {
                            stationList = response.body().getLineList();
                        }
                        else{
                            tempList = response.body().getLineList();
                            stationList.addAll(tempList);
                            tempList = null;
                        }

                        /**
                         * Binding that List to Adapter
                         */
                        adapter = new StationListAdapter(SearchActivity.this, stationList);
                        adapter.sort();
                        listView.setAdapter(adapter);
                    } else {
                        Log.v("SearchActivity",linenum);
                        Toast.makeText(getApplicationContext(),linenum+"respons fail",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Station> call, Throwable t) {
                    Log.v("SearchActivity","onFailure"+linenum);
                    Toast.makeText(getApplicationContext(),linenum+"onFailure",Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(getApplicationContext(),"internet_connection_not_available",Toast.LENGTH_SHORT).show();

        }
    }

    private void setEvent(){

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //parameters are lineId, lineName, stationName
                //lineId for identifying line , lineName for next activity
                //stationName for Realtime API
                mSearchImp.onItemClick(adapter.getItem(position).getSubwayId(),
                        adapter.getItem(position).getSubwayNm(),
                        adapter.getItem(position).getStatnNm());
            }
        });
        //Edit Text renew textView by search result
        exInputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable edit) {
                adapter.getFilter().filter(edit.toString());
            }
        });
    }

}
