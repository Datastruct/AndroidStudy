package com.example.owner.myapplication.model.stnlistapi;

/**
 * Created by owner on 2016-12-16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Station {

    @SerializedName("lineList")
    @Expose
    private ArrayList<LineList> lineList=null;
    @SerializedName("errorMessage")
    @Expose
    private ErrorMessage errorMessage;

    public ArrayList<LineList> getLineList() {
        return lineList;
    }

    public void setLineList(ArrayList<LineList> lineList) {
        this.lineList = lineList;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

}