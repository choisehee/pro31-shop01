package com.himedia.shop01.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.himedia.shop01.common.base.BaseController;
import com.himedia.shop01.goods.vo.GoodsVO;
import com.himedia.shop01.member.vo.MemberVO;
import com.himedia.shop01.order.service.ApiService01;
import com.himedia.shop01.order.service.OrderService;
import com.himedia.shop01.order.vo.OrderVO;

@Controller("orderController")
@RequestMapping(value = "/order")
public class OrderControllerImpl extends BaseController implements OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderVO orderVO;

	@Autowired
	private ApiService01 apiService01;

	@RequestMapping(value = "/orderEachGoods.do", method = RequestMethod.POST)
	public ModelAndView orderEachGoods(@ModelAttribute("orderVO") OrderVO _orderVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		session = request.getSession();

		Boolean isLogOn = (Boolean) session.getAttribute("isLogOn");
		String action = (String) session.getAttribute("action");
		// 로그인 여부 체크
		// 이전에 로그인 상태인 경우는 주문과정 진행
		// 로그아웃 상태인 경우 로그인 화면으로 이동
		if (isLogOn == null || isLogOn == false) {
			session.setAttribute("orderInfo", _orderVO);
			session.setAttribute("action", "/order/orderEachGoods.do");
			return new ModelAndView("redirect:/member/loginForm.do");
		} else {
			if (action != null && action.equals("/order/orderEachGoods.do")) {
				orderVO = (OrderVO) session.getAttribute("orderInfo");
				session.removeAttribute("action");
			} else {// 로그인 된상태일 경우 주문처리
				orderVO = _orderVO;
			}
		}

		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		List myOrderList = new ArrayList<OrderVO>();// 주문 정보를 저장할 주문 ArrayList를 생성
		myOrderList.add(orderVO);// 브라우저에 전달 , 주문정보를 ArrayList저장

		MemberVO memberInfo = (MemberVO) session.getAttribute("memberInfo");

		// 주문 정보와 주문자 정보를 세션에 바인딩한 후 주문창으로 전달
		session.setAttribute("myOrderList", myOrderList);
		session.setAttribute("orderer", memberInfo);
		return mav;
	}

	@RequestMapping(value = "/orderAllCartGoods.do", method = RequestMethod.POST)
	// 주문창에서 입력한 상품 수령자 정보와 배송지 정모를 Map에 저장
	public ModelAndView orderAllCartGoods(@RequestParam("cart_goods_qty") String[] cart_goods_qty,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		HttpSession session = request.getSession();
		Map cartMap = (Map) session.getAttribute("cartMap");
		List myOrderList = new ArrayList<OrderVO>();

		List<GoodsVO> myGoodsList = (List<GoodsVO>) cartMap.get("myGoodsList");
		MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");

		for (int i = 0; i < cart_goods_qty.length; i++) {
			String[] cart_goods = cart_goods_qty[i].split(":");
			for (int j = 0; j < myGoodsList.size(); j++) {
				GoodsVO goodsVO = myGoodsList.get(j);
				int goods_id = goodsVO.getGoods_id();
				if (goods_id == Integer.parseInt(cart_goods[0])) {
					OrderVO _orderVO = new OrderVO();
					String goods_title = goodsVO.getGoods_title();
					int goods_sales_price = goodsVO.getGoods_sales_price();
					String goods_fileName = goodsVO.getGoods_fileName();
					_orderVO.setGoods_id(goods_id);
					_orderVO.setGoods_title(goods_title);
					_orderVO.setGoods_sales_price(goods_sales_price);
					_orderVO.setGoods_fileName(goods_fileName);
					_orderVO.setOrder_goods_qty(Integer.parseInt(cart_goods[1]));
					myOrderList.add(_orderVO);
					break;
				}
			}
		}
		session.setAttribute("myOrderList", myOrderList);
		session.setAttribute("orderer", memberVO);
		return mav;
	}

	// 최종 결제 버튼을 눌렀을때
	@RequestMapping(value = "/payToOrderGoods.do", method = RequestMethod.POST)
	public ModelAndView payToOrderGoods(@RequestParam Map<String, String> receiverMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		System.out.println("viewName 확인 = " + viewName);
		ModelAndView mav = new ModelAndView(viewName);

		// 결제하기
		// 카드결제 입력 부분 확인
		System.out.println("확인 : " + receiverMap.toString());
		// 확인 : {receiver_name=, receiver_hp1=010, receiver_hp2=, receiver_hp3=,
		// receiver_tel1=02, receiver_tel2=, receiver_tel3=,
		// delivery_address=우편번호:<br>도로명 주소:<br>[지번 주소:]<br>, delivery_message=,
		// delivery_method=일반택배, gift_wrapping=no,
		// pay_method=신용카드<Br>카드사:삼성<br>할부개월수:일시불, card_com_name=삼성, card_pay_month=일시불,
		// pay_orderer_hp_num=해당없음, cardNo=1245646, expireMonth=54, expireYear=45,
		// birthday=11534156, cardPw=41}

		// 1. 결제하기 끝

		// 2. 신용카드 결제 요청 값 (수기결제 구인증방식) (변수명은 규격내 이름과 일치하게 작성하는것이 좋다)

		String merchantId = ""; // 강맹점 아이디
		String orderNumber = ""; // 주문번호
		String cardNo = ""; // 카드번호
		String expireMonth = "";// 유효기간
		String expireYear = "";
		String birthday = "";
		String cardPw = "";
		String amount = "";
		String quota = ""; // 할부개월 0~24
		String itemName = "";
		String userName = "";
		String signature = "";
		String timestamp = "";
		String apiCerKey = "";// api 인증키

		// 만든 변수 값 세팅
		merchantId = "himedia";
		apiCerKey = "ac805b30517f4fd08e3e80490e559f8e";
		orderNumber = "TEST_choi1234"; // 주문번호 생성
		cardNo = receiverMap.get("cardNo");// 화면에서 받은값
		expireMonth = receiverMap.get("expireMonth");// 화면에서 받은 값
		expireYear = receiverMap.get("expireYear");// 화면에서 받은 값
		birthday = receiverMap.get("birthday");// 화면에서 받은 값
		cardPw = receiverMap.get("cardPw");// 화면에서 받은 값
		amount = "1000";// 화면에서 받은 값
		quota = "0"; // 일시불
		itemName = "책";// 화면에서 받은 값
		userName = receiverMap.get("receiver_name");// 화면에서 받은 값
		timestamp = "20230501112700";
		// 예시)sha256_hash({merchantId}|{orderNumber}|{amount}|{apiCertKey}|{timestamp})

		signature = apiService01
				.encrypt(merchantId + "|" + orderNumber + "|" + amount + "|" + apiCerKey + "|" + timestamp);// 서명값(설명필)

		// rest api를 라이브러리 써서 사용
		// *가장 간단한 통신은 httpURLconnection으로 하는 통신 (x)
		String url = "https://api.testpayup.co.kr/v2/api/payment/" + merchantId + "/keyin2";
		Map<String, String> map = new HashMap<String, String>();
		Map<String, Object> returnMap = new HashMap<String, Object>();

		// map에 다가 요청 데이터값을 넣으시면 됩니다
		map.put("merchantId", merchantId);
		map.put("orderNumber", orderNumber);
		map.put("cardNo", cardNo);
		map.put("expireMonth", expireMonth);
		map.put("expireYear", expireYear);
		map.put("birthday", birthday);
		map.put("cardPw", cardPw);
		map.put("amount", amount);
		map.put("quota", quota);
		map.put("itemName", itemName);
		map.put("userName", userName);
		map.put("signature", signature);
		map.put("timestamp", timestamp);

		System.out.println(map.toString());
		returnMap = apiService01.restApi(map, url);

		System.out.println("카드결제 승인 응답 데이터 =" + returnMap.toString());
		// 카드결제 승인 응답 데이터 ={responseCode=6001, responseMsg=카드번호(cardNo)는 필수 입니다.}

		// 응답값을 잘 받으면 구인증 연동 끝

		// 승인 성공 or 실패

		// 3. 결제 응답 정보를가지고 성공/실패에 따른 프로세스짜기
		
		String responseCode = (String) returnMap.get("responseCode");

		if ("0000".equals(responseCode)) {
			// 여기는 결제가 성공했을 때

			// DB데이터 업데이트 (스킵)

			mav.setViewName("/order/payToOrderGoods");

			// 결제정보 보내기
			mav.addObject("responseCode", returnMap.get("responseCode"));
			mav.addObject("responseMsg", returnMap.get("responseMsg"));
			mav.addObject("cardName", returnMap.get("cardName"));// 카드사명
			mav.addObject("authNumber", returnMap.get("authNumber"));// 카드승인번호
			mav.addObject("authDateTime", returnMap.get("authDateTime"));// 승인시간

		} else {
			// 여기는 결제가 실패했을 때

			// DB데이터 업데이트 (스킵)

			// JSP 변경
			mav.setViewName("/order/orderResultFail");

			// 결과 코드,메시지 보내기
			mav.addObject("responseCode", returnMap.get("responseCode"));
			mav.addObject("responseMsg", returnMap.get("responseMsg"));
		}
		// 3번끝



		// 결과값에 따른 프로세스 변경
		if ("0000".equals(responseCode)) {
			// 성공
			mav.setViewName("/order/payToOrderGoodsKakao");
//			System.out.println("성공확인 : "+mav.toString());

		} else {
			// 실패
			mav.setViewName("/order/orderResultFail");
		}

		// 결제하기 끝

		// 1. 주문 데이터 생성
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("orderer");
		String member_id = memberVO.getMember_id();
		String orderer_name = memberVO.getMember_name();
		String orderer_hp = memberVO.getHp1() + "-" + memberVO.getHp2() + "-" + memberVO.getHp3();
		List<OrderVO> myOrderList = (List<OrderVO>) session.getAttribute("myOrderList");

		for (int i = 0; i < myOrderList.size(); i++) {// 주문창에서 입력한 수령자 정보와 배송지 정보를 주문 상품 정보 목록과 합침
														// 각 orderVO에 수령자 정보를 설정 후 다시 myOrderList에 저장
			OrderVO orderVO = (OrderVO) myOrderList.get(i);
			orderVO.setMember_id(member_id);
			orderVO.setOrderer_name(orderer_name);
			orderVO.setReceiver_name(receiverMap.get("receiver_name"));

			orderVO.setReceiver_hp1(receiverMap.get("receiver_hp1"));
			orderVO.setReceiver_hp2(receiverMap.get("receiver_hp2"));
			orderVO.setReceiver_hp3(receiverMap.get("receiver_hp3"));
			orderVO.setReceiver_tel1(receiverMap.get("receiver_tel1"));
			orderVO.setReceiver_tel2(receiverMap.get("receiver_tel2"));
			orderVO.setReceiver_tel3(receiverMap.get("receiver_tel3"));

			orderVO.setDelivery_address(receiverMap.get("delivery_address"));
			orderVO.setDelivery_message(receiverMap.get("delivery_message"));
			orderVO.setDelivery_method(receiverMap.get("delivery_method"));
			orderVO.setGift_wrapping(receiverMap.get("gift_wrapping"));
			orderVO.setPay_method(receiverMap.get("pay_method"));
			orderVO.setCard_com_name(receiverMap.get("card_com_name"));
			orderVO.setCard_pay_month(receiverMap.get("card_pay_month"));
			orderVO.setPay_orderer_hp_num(receiverMap.get("pay_orderer_hp_num"));
			orderVO.setOrderer_hp(orderer_hp);
			myOrderList.set(i, orderVO); // 각 orderVO에 주문자 정보를 세팅한 후 다시 myOrderList에 저장한다.
		} // end for

		orderService.addNewOrder(myOrderList);// 주문자 정볼를 sql문으로 전달
		mav.addObject("myOrderInfo", receiverMap);// OrderVO로 주문결과 페이지에 주문자 정보를 표시한다.
		mav.addObject("myOrderList", myOrderList);// 주문완료 결과창에 주문 상품 목록을 표시하도록 전달
		return mav;
	}

}
