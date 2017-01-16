package com.example.owner.myapplication.model.stnlistapi;

/**
 *Created by owner on 2016-12-16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.Collator;
import java.util.Comparator;

/**
 * information of API data
 공통	list_total_count	총 데이터 건수 (정상조회 시 출력됨)
 공통	RESULT.CODE	요청결과 코드 (하단 메세지설명 참고)
 공통	RESULT.MESSAGE	요청결과 메시지 (하단 메세지설명 참고)
 subwayId	지하철호선ID
 subwayNm	지하철호선명
 statnId	지하철역ID
 statnNm	지하철역명
 statnSn	지하철역순번
 (역사 순번)
 */

public class LineList {

    @SerializedName("subwayId")
    @Expose
    private String subwayId;
    @SerializedName("subwayNm")
    @Expose
    private String subwayNm;
    @SerializedName("statnId")
    @Expose
    private String statnId;
    @SerializedName("statnNm")
    @Expose
    private String statnNm;


    public String getSubwayId() {
        return subwayId;
    }

    public void setSubwayId(String subwayId) {
        this.subwayId = subwayId;
    }

    public String getSubwayNm() {
        return subwayNm;
    }

    public void setSubwayNm(String subwayNm) {
        this.subwayNm = subwayNm;
    }

    public String getStatnId() {
        return statnId;
    }

    public void setStatnId(String statnId) {
        this.statnId = statnId;
    }

    public String getStatnNm() {
        return statnNm;
    }

    public void setStatnNm(String statnNm) {
        this.statnNm = statnNm;
    }

    public static final Comparator<LineList> comparator = new Comparator<LineList>() {
        private final Collator sCollator = Collator.getInstance();
        @Override
        public int compare(LineList mList_1, LineList mList_2) {
            return sCollator.compare(mList_1.getStatnId(),mList_2.getStatnId());
        }
    };

}