package com.example.owner.myapplication.presenter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by owner on 2016-12-14.
 * ServiceGenerator
 */
public class ServiceGenerator {
    private static final String SW_API_ROOT_URL = "http://swopenapi.seoul.go.kr/";
    /**
     * Get Retrofit Instance
     */
    //baseUrl must end in /
    //역 리스트 Instance
    private static Retrofit getStationListInstance() {
        return new Retrofit.Builder()
                .baseUrl(SW_API_ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static SubwayApiService getListApiService() {
        return getStationListInstance().create(SubwayApiService.class);
    }



}
