package com.example.owner.myapplication.presenter;


import com.example.owner.myapplication.model.realtimeposapi.RealTimePositionInfo;
import com.example.owner.myapplication.model.realtimestnapi.RealTimeArrivalInfo;
import com.example.owner.myapplication.model.stnlistapi.Station;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SubwayApiService {
    //지하철 노선별 역사 경유 정보(역 리스트 출력, stationByLine)
    @GET("api/subway/{apikey}/json/stationByLine/1/100/{linenum}")
    Call<Station> getStationList(
            @Path("apikey") String apikey,
            @Path("linenum") String linenum
    );


}

//지하철 노선별 역사 경유 정보(역 리스트 출력, stationByLine)
//http://swopenapi.seoul.go.kr/api/subway/(인증키)/json/stationByLine/1/5/1호선
// 변수명		타입				변수설명		                     값설명
//KEY			String(필수)		인증키(authkey)	                 OpenAPI 에서 발급된 인증키
//TYPE		    String(필수)		요청파일타입(request file type)   xml : xml, xml파일 : xmlf, 엑셀파일 : xls, json파일 : json
//SERVICE		String(필수)		서비스명(service name)            stationByLine
//START_INDEX	INTEGER(필수)	요청시작위치	                     정수 입력 (페이징 시작번호 입니다 : 데이터 행 시작번호)
//END_INDEX	    INTEGER(필수)	요청종료위치  	                정수 입력 (페이징 끝번호 입니다 : 데이터 행 끝번호)
//subwayNm	    STRING(필수)		지하철호선명(line name)   	    지하철호선명
