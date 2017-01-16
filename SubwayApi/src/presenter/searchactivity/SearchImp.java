package com.example.owner.myapplication.presenter.searchactivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.owner.myapplication.R;
import com.example.owner.myapplication.model.realtimestnapi.RealTimeArrivalInfo;
import com.example.owner.myapplication.model.realtimestnapi.RealTimeArrivalList;
import com.example.owner.myapplication.presenter.ServiceGenerator;
import com.example.owner.myapplication.presenter.SubwayApiService;
import com.example.owner.myapplication.utils.InternetConnection;
import com.example.owner.myapplication.utils.Logger;
import com.example.owner.myapplication.view.activities.UpDownTrainActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by owner on 2016-12-12.
 * SearchImp
 */
public class SearchImp implements Search {

    Activity activity;
    private ArrayList<RealTimeArrivalList> trainList;
    public SearchImp(Activity activity) {
        this.activity = activity;
    }
    //matching with selected line with information of realtimeAPI
    private String selectLineId;

    //Data for other Activity
    private String upDirection;
    private String downDirection;
    private String upTrainId;
    private String downTrainId;

    private Bundle searchExtras;
    //data from searchActivity
    private String subwayId;
    private String subwayNm;
    private String statnNm;

    @Override
    public void onItemClick(String subwayId, String subwayNm, String statnNm) {

        this.subwayId = subwayId;
        this.subwayNm = subwayNm;
        this.statnNm = statnNm;
        this.selectLineId = subwayId;
        trainList = null;
        //display smth, using other API or start next activity

    }
}
