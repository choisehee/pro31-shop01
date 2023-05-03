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
public class KakaoController2 {
	
	@Autowired
	private ApiService01 apiService01;
	
	// ,method=RequestMethod.POST
	@RequestMapping(value= "/test/kakaoPay.do")
	public ModelAndView kakaoPay(@RequestParam Map<String, String> map, String URL) throws Exception{
	
		ModelAndView mav = new ModelAndView();
		
		//주문 DB에 저장하는게 원래 방식
		
		System.out.println("인증데이터 확인 : "+map.toString());
		
		//{ordr_idxx=20230502101839KK0640, good_name=?????????? ?????? ???????, good_mny=100, buyr_name=??????, site_cd=A8QOB, req_tx=pay, pay_method=100000000000, currency=410, kakaopay_direct=Y, module_type=01, ordr_chk=20230502101839KK0640|100, param_opt_1=, param_opt_2=, param_opt_3=, res_cd=0000, res_msg=????, enc_info=0-uFqjpelYKLTfQjFxmYcJm5N7po-w1xLVYAimHRAGRn1PJQlf7Ba1qTKRICrWuH-AoIphG77qwL9ruOF.ItW1YYObLh7l2HvCCGAXkuo3DTN4eeDwuI8F0htyTbTZ.00aJLVWWOfAntgLj58gMz8E5Wub9JtkIe-1pKHvVaelw6dXGYD.qQfrR3ux-CsPGCg2q.x.5Bq-t__, enc_data=4u6DomRIvFg0ETrhflLMuPrCr9gb0hs-o2NuCqWuo4pbrcdJUCOt2-RqCHnVa814RyiGeaRlYKSJnDYaffFOXAPy6SKILV4yY8MQxS1snolmiO5U3emhWEa7hVVZ1nH8TP.5WxshlaiIWnI5K1VSxwPp-Yy0ikSuBT0HtC-U8DTbnXku4WV87HMgQbAC7-Ul5Eg9oArERpzDQBFfv6uZvqF.773PieBmXGpJogFwfxRiz07yGcQJH-G3GG15hN5su8zRa8Ov4hhpPY-iw8rse8zzfbIzbfuGDvSe1msJ2TpF2b8IqufVIQarGf1I8u-bg1WgCNu-zNN-a2yuOyPODARx1R0CHjjDRKg7j8Ve25OGNs8B9aW6uU2y8cDoS24BAOrZMnsUwSBJaYJ.wxaDrN9sucob2ZtVvnpNDwMtpHUc2Jsbjq4K76TqxZszY2JOyavbpcLHU1HBR1a6HOR4vcoEIDKD6djNXpCxlwjNPhUPQnuUXKjIX12VF7AcHp.PZsol3B.B9xnWvjin4d09J66HkgsEql5n.-wS2TNihcqK4r7sF8BnWTeHU3r6Aq7qR5S.h794.1OVtfrvb.n3g.oP4hafo12.ZQdwugRdPkT1L9lxScIrgsmPKgS46FcAqO.Cqyyuwh4Le9ZPOwZKFoPPwl1gnT5KSJi3ClX.MQrtmzbqqeAVWCBegF82F75dO, ret_pay_method=CARD, tran_cd=00100000, use_pay_method=100000000000, card_pay_method=KAKAO_MONEY}
		
		
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
		String tran_cd=map.get("tran_cd");
		String card_pay_method=map.get("card_pay_method");
		String ordr_idxx=map.get("ordr_idxx");
		
		Map<String, String> map01 = new HashMap<String, String>();
		Map<String, Object> resultMap  = new HashMap<String, Object>();

		map01.put("id", id);
		map01.put("res_cd", res_cd);
		map01.put("enc_info", enc_info);
		map01.put("enc_data", enc_data);
		map01.put("tran_cd", tran_cd);
		map01.put("card_pay_method",card_pay_method);
		map01.put("ordr_idxx",ordr_idxx);
		
		String url = "https://api.testpayup.co.kr/ep/api/kakao/"+id+"/pay";
		
		
//		resultMap=apiService01.restApi(map01, url);
		
		//테스트 데이터를 임의로 넣어 확인 해볼 수 있다
		resultMap.put("responseCode","0000");
		resultMap.put("responseMasg","성공");
		resultMap.put("type", "NAVER_MONEY");
		resultMap.put("authDateTime", "20230502155500");
		
		System.out.println("카카오 결제 응답 = "+resultMap.toString());
		
//		apiService01.restApi(여기에 맵(요청값이 있어야 함), url(틀 URL, 데이터를 보내야되는 주소));
		
		//성공실패에 따른 view 설정
		// 결과값에 따른 프로세스 변경
		String responseCode = (String) resultMap.get("responseCode");
		
				if ("0000".equals(responseCode)) {
					// 성공
					mav.setViewName("/kakao/kakaoResult");
//					System.out.println("성공확인 : "+mav.toString());
					
					//화면에서 보여줄 값을 mav에 넣기 하나씩
					mav.addObject("resultMap",resultMap);
					

				} else {
					// 실패
					mav.setViewName("/kakao/kakaoResultFail");
					
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
