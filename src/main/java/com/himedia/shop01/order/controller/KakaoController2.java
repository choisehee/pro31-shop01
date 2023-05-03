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
		
		//�ֹ� DB�� �����ϴ°� ���� ���
		
		System.out.println("���������� Ȯ�� : "+map.toString());
		
		//{ordr_idxx=20230502101839KK0640, good_name=?????????? ?????? ???????, good_mny=100, buyr_name=??????, site_cd=A8QOB, req_tx=pay, pay_method=100000000000, currency=410, kakaopay_direct=Y, module_type=01, ordr_chk=20230502101839KK0640|100, param_opt_1=, param_opt_2=, param_opt_3=, res_cd=0000, res_msg=????, enc_info=0-uFqjpelYKLTfQjFxmYcJm5N7po-w1xLVYAimHRAGRn1PJQlf7Ba1qTKRICrWuH-AoIphG77qwL9ruOF.ItW1YYObLh7l2HvCCGAXkuo3DTN4eeDwuI8F0htyTbTZ.00aJLVWWOfAntgLj58gMz8E5Wub9JtkIe-1pKHvVaelw6dXGYD.qQfrR3ux-CsPGCg2q.x.5Bq-t__, enc_data=4u6DomRIvFg0ETrhflLMuPrCr9gb0hs-o2NuCqWuo4pbrcdJUCOt2-RqCHnVa814RyiGeaRlYKSJnDYaffFOXAPy6SKILV4yY8MQxS1snolmiO5U3emhWEa7hVVZ1nH8TP.5WxshlaiIWnI5K1VSxwPp-Yy0ikSuBT0HtC-U8DTbnXku4WV87HMgQbAC7-Ul5Eg9oArERpzDQBFfv6uZvqF.773PieBmXGpJogFwfxRiz07yGcQJH-G3GG15hN5su8zRa8Ov4hhpPY-iw8rse8zzfbIzbfuGDvSe1msJ2TpF2b8IqufVIQarGf1I8u-bg1WgCNu-zNN-a2yuOyPODARx1R0CHjjDRKg7j8Ve25OGNs8B9aW6uU2y8cDoS24BAOrZMnsUwSBJaYJ.wxaDrN9sucob2ZtVvnpNDwMtpHUc2Jsbjq4K76TqxZszY2JOyavbpcLHU1HBR1a6HOR4vcoEIDKD6djNXpCxlwjNPhUPQnuUXKjIX12VF7AcHp.PZsol3B.B9xnWvjin4d09J66HkgsEql5n.-wS2TNihcqK4r7sF8BnWTeHU3r6Aq7qR5S.h794.1OVtfrvb.n3g.oP4hafo12.ZQdwugRdPkT1L9lxScIrgsmPKgS46FcAqO.Cqyyuwh4Le9ZPOwZKFoPPwl1gnT5KSJi3ClX.MQrtmzbqqeAVWCBegF82F75dO, ret_pay_method=CARD, tran_cd=00100000, use_pay_method=100000000000, card_pay_method=KAKAO_MONEY}
		
		
		//4.���������ͷ� ���� ��û�ϱ�(rest api���)
		//��û ���� ����
//		�ʵ�� �ѱ۸�Ī ���� ���� �ʼ�����
//		res_cd ��ȣȭ ���� �����Ϸ� �� ���� �� (res_cd) - Y
//		enc_info ��ȣȭ ���� �����Ϸ� �� ���� �� (enc_info) - Y
//		enc_data ��ȣȭ ���� �����Ϸ� �� ���� �� (enc_data) - Y
//		tran_cd ���� �ڵ� �����Ϸ� �� ���� �� (tran_cd)
//		card_pay_method ����Ÿ�� �����Ϸ� �� ���� �� (card_pay_method) - Y
//		ordr_idxx ��
		
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
		
		//�׽�Ʈ �����͸� ���Ƿ� �־� Ȯ�� �غ� �� �ִ�
		resultMap.put("responseCode","0000");
		resultMap.put("responseMasg","����");
		resultMap.put("type", "NAVER_MONEY");
		resultMap.put("authDateTime", "20230502155500");
		
		System.out.println("īī�� ���� ���� = "+resultMap.toString());
		
//		apiService01.restApi(���⿡ ��(��û���� �־�� ��), url(Ʋ URL, �����͸� �����ߵǴ� �ּ�));
		
		//�������п� ���� view ����
		// ������� ���� ���μ��� ����
		String responseCode = (String) resultMap.get("responseCode");
		
				if ("0000".equals(responseCode)) {
					// ����
					mav.setViewName("/kakao/kakaoResult");
//					System.out.println("����Ȯ�� : "+mav.toString());
					
					//ȭ�鿡�� ������ ���� mav�� �ֱ� �ϳ���
					mav.addObject("resultMap",resultMap);
					

				} else {
					// ����
					mav.setViewName("/kakao/kakaoResultFail");
					
					//���
//					mav.addObject("responseCode", returnMap.get("responseCode"));
//					mav.addObject("responseMsg", returnMap.get("responseMsg"));
				
					mav.addObject("resultMap",resultMap);
				}

				// �����ϱ� ��

		
		
		//�̰� ����Ǹ� �翬�� ������ ���� ... view������ ����� �ȵǾ��ֱ� ������
		
		return mav;
	}
}
