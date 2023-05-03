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
		
		//�ֹ� DB�� �����ϴ°� ���� ���
		
		System.out.println("���������� Ȯ�� : "+map.toString());
		
		
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
		
		//�׽�Ʈ �����͸� ���Ƿ� �־� Ȯ�� �غ� �� �ִ�
		resultMap.put("responseCode","9999");
		resultMap.put("responseMasg","����");
		resultMap.put("type", "NAVER_MONEY");
		resultMap.put("authDateTime", "20230502155500");
		
		System.out.println("���̹� ���� ���� = "+resultMap.toString());
		
//		apiService01.restApi(���⿡ ��(��û���� �־�� ��), url(Ʋ URL, �����͸� �����ߵǴ� �ּ�));
		
		//�������п� ���� view ����
		// ������� ���� ���μ��� ����
		String responseCode = (String) resultMap.get("responseCode");
		
				if ("0000".equals(responseCode)) {
					// ����
					mav.setViewName("/naver/naverResult");
//					System.out.println("����Ȯ�� : "+mav.toString());
					
					//ȭ�鿡�� ������ ���� mav�� �ֱ� �ϳ���
					mav.addObject("resultMap",resultMap);
					

				} else {
					// ����
					mav.setViewName("/naver/naverResultFail");
					
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
