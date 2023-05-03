package com.himedia.shop01.order.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.himedia.shop01.order.service.ApiService01;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


@Controller
public class NaverController2 {
	
	@Autowired
	private ApiService01 apiService01;
	
	// ,method=RequestMethod.POST
	@RequestMapping(value= "/test/naverPay.do")
	public ModelAndView naverPay(@RequestParam Map<String, String> map, String URL) throws Exception{
	
		ModelAndView mav = new ModelAndView();
		
		//주문 DB에 저장하는게 원래 방식
		
		System.out.println("인증데이터 확인 : "+map.toString());
		
		
		//4.인증데이터로 결제 요청하기(rest api사용)
		//요청 전문 구성
//		필드명 한글명칭 설명 길이 필수여부
//		res_cd 암호화 정보 인증완료 후 받은 값 (res_cd) - Y
//		enc_info 암호화 정보 인증완료 후 받은 값 (enc_info) - Y
//		enc_data 암호화 정보 인증완료 후 받은 값 (enc_data) - Y
//		tran_cd 인증 코드 인증완료 후 받은 값 (tran_cd)
//		card_pay_method 결제타입 인증완료 후 받은 값 (card_pay_method) - Y
//		ordr_idxx 거
		
		String id="himedia";
		String res_cd=map.get("res_cd");
		String enc_info=map.get("enc_info");
		String enc_data=map.get("enc_data");
//		String tran_cd=map.get("tran_cd");
//		String card_pay_method=map.get("card_pay_method");
		String ordr_idxx=map.get("ordr_idxx");
		String tran_cd=map.get("tran_cd");
		
		Map<String, String> map01 = new HashMap<String, String>();
		Map<String, Object> resultMap  = new HashMap<String, Object>();

		map01.put("id", id);
		map01.put("res_cd", res_cd);
		map01.put("enc_info", enc_info);
		map01.put("enc_data", enc_data);
//		map01.put("tran_cd", tran_cd);
//		map01.put("card_pay_method",card_pay_method);
		map01.put("ordr_idxx",ordr_idxx);
		map01.put("tran_cd",tran_cd);
		
		String url = "https://api.testpayup.co.kr/ep/api/naver/"+id+"/pay";
		
		
//		resultMap=apiService01.restApi(map01, url);
		
		//테스트 데이터를 임의로 넣어 확인 해볼 수 있다
		resultMap.put("responseCode","9999");
		resultMap.put("responseMasg","실패");
		resultMap.put("type", "NAVER_MONEY");
		resultMap.put("authDateTime", "20230502155500");
		
		System.out.println("네이버 결제 응답 = "+resultMap.toString());
		
//		apiService01.restApi(여기에 맵(요청값이 있어야 함), url(틀 URL, 데이터를 보내야되는 주소));
		
		//성공실패에 따른 view 설정
		// 결과값에 따른 프로세스 변경
		String responseCode = (String) resultMap.get("responseCode");
		
				if ("0000".equals(responseCode)) {
					// 성공
					mav.setViewName("/naver/naverResult");
//					System.out.println("성공확인 : "+mav.toString());
					
					//화면에서 보여줄 값을 mav에 넣기 하나씩
					mav.addObject("resultMap",resultMap);
					

				} else {
					// 실패
					mav.setViewName("/naver/naverResultFail");
					
					//결과
//					mav.addObject("responseCode", returnMap.get("responseCode"));
//					mav.addObject("responseMsg", returnMap.get("responseMsg"));
				
					mav.addObject("resultMap",resultMap);
				}

				// 결제하기 끝

		
		
		//이게 실행되면 당연히 오류가 난다 ... view설정이 제대로 안되어있기 때문에
		
		return mav;
	}
}
