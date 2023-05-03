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
		// �α��� ���� üũ
		// ������ �α��� ������ ���� �ֹ����� ����
		// �α׾ƿ� ������ ��� �α��� ȭ������ �̵�
		if (isLogOn == null || isLogOn == false) {
			session.setAttribute("orderInfo", _orderVO);
			session.setAttribute("action", "/order/orderEachGoods.do");
			return new ModelAndView("redirect:/member/loginForm.do");
		} else {
			if (action != null && action.equals("/order/orderEachGoods.do")) {
				orderVO = (OrderVO) session.getAttribute("orderInfo");
				session.removeAttribute("action");
			} else {// �α��� �Ȼ����� ��� �ֹ�ó��
				orderVO = _orderVO;
			}
		}

		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		List myOrderList = new ArrayList<OrderVO>();// �ֹ� ������ ������ �ֹ� ArrayList�� ����
		myOrderList.add(orderVO);// �������� ���� , �ֹ������� ArrayList����

		MemberVO memberInfo = (MemberVO) session.getAttribute("memberInfo");

		// �ֹ� ������ �ֹ��� ������ ���ǿ� ���ε��� �� �ֹ�â���� ����
		session.setAttribute("myOrderList", myOrderList);
		session.setAttribute("orderer", memberInfo);
		return mav;
	}

	@RequestMapping(value = "/orderAllCartGoods.do", method = RequestMethod.POST)
	// �ֹ�â���� �Է��� ��ǰ ������ ������ ����� ���� Map�� ����
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

	// ���� ���� ��ư�� ��������
	@RequestMapping(value = "/payToOrderGoods.do", method = RequestMethod.POST)
	public ModelAndView payToOrderGoods(@RequestParam Map<String, String> receiverMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		System.out.println("viewName Ȯ�� = " + viewName);
		ModelAndView mav = new ModelAndView(viewName);

		// �����ϱ�
		// ī����� �Է� �κ� Ȯ��
		System.out.println("Ȯ�� : " + receiverMap.toString());
		// Ȯ�� : {receiver_name=, receiver_hp1=010, receiver_hp2=, receiver_hp3=,
		// receiver_tel1=02, receiver_tel2=, receiver_tel3=,
		// delivery_address=�����ȣ:<br>���θ� �ּ�:<br>[���� �ּ�:]<br>, delivery_message=,
		// delivery_method=�Ϲ��ù�, gift_wrapping=no,
		// pay_method=�ſ�ī��<Br>ī���:�Ｚ<br>�Һΰ�����:�Ͻú�, card_com_name=�Ｚ, card_pay_month=�Ͻú�,
		// pay_orderer_hp_num=�ش����, cardNo=1245646, expireMonth=54, expireYear=45,
		// birthday=11534156, cardPw=41}

		// 1. �����ϱ� ��

		// 2. �ſ�ī�� ���� ��û �� (������� ���������) (�������� �԰ݳ� �̸��� ��ġ�ϰ� �ۼ��ϴ°��� ����)

		String merchantId = ""; // ������ ���̵�
		String orderNumber = ""; // �ֹ���ȣ
		String cardNo = ""; // ī���ȣ
		String expireMonth = "";// ��ȿ�Ⱓ
		String expireYear = "";
		String birthday = "";
		String cardPw = "";
		String amount = "";
		String quota = ""; // �Һΰ��� 0~24
		String itemName = "";
		String userName = "";
		String signature = "";
		String timestamp = "";
		String apiCerKey = "";// api ����Ű

		// ���� ���� �� ����
		merchantId = "himedia";
		apiCerKey = "ac805b30517f4fd08e3e80490e559f8e";
		orderNumber = "TEST_choi1234"; // �ֹ���ȣ ����
		cardNo = receiverMap.get("cardNo");// ȭ�鿡�� ������
		expireMonth = receiverMap.get("expireMonth");// ȭ�鿡�� ���� ��
		expireYear = receiverMap.get("expireYear");// ȭ�鿡�� ���� ��
		birthday = receiverMap.get("birthday");// ȭ�鿡�� ���� ��
		cardPw = receiverMap.get("cardPw");// ȭ�鿡�� ���� ��
		amount = "1000";// ȭ�鿡�� ���� ��
		quota = "0"; // �Ͻú�
		itemName = "å";// ȭ�鿡�� ���� ��
		userName = receiverMap.get("receiver_name");// ȭ�鿡�� ���� ��
		timestamp = "20230501112700";
		// ����)sha256_hash({merchantId}|{orderNumber}|{amount}|{apiCertKey}|{timestamp})

		signature = apiService01
				.encrypt(merchantId + "|" + orderNumber + "|" + amount + "|" + apiCerKey + "|" + timestamp);// ����(������)

		// rest api�� ���̺귯�� �Ἥ ���
		// *���� ������ ����� httpURLconnection���� �ϴ� ��� (x)
		String url = "https://api.testpayup.co.kr/v2/api/payment/" + merchantId + "/keyin2";
		Map<String, String> map = new HashMap<String, String>();
		Map<String, Object> returnMap = new HashMap<String, Object>();

		// map�� �ٰ� ��û �����Ͱ��� �����ø� �˴ϴ�
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

		System.out.println("ī����� ���� ���� ������ =" + returnMap.toString());
		// ī����� ���� ���� ������ ={responseCode=6001, responseMsg=ī���ȣ(cardNo)�� �ʼ� �Դϴ�.}

		// ���䰪�� �� ������ ������ ���� ��

		// ���� ���� or ����

		// 3. ���� ���� ������������ ����/���п� ���� ���μ���¥��
		
		String responseCode = (String) returnMap.get("responseCode");

		if ("0000".equals(responseCode)) {
			// ����� ������ �������� ��

			// DB������ ������Ʈ (��ŵ)

			mav.setViewName("/order/payToOrderGoods");

			// �������� ������
			mav.addObject("responseCode", returnMap.get("responseCode"));
			mav.addObject("responseMsg", returnMap.get("responseMsg"));
			mav.addObject("cardName", returnMap.get("cardName"));// ī����
			mav.addObject("authNumber", returnMap.get("authNumber"));// ī����ι�ȣ
			mav.addObject("authDateTime", returnMap.get("authDateTime"));// ���νð�

		} else {
			// ����� ������ �������� ��

			// DB������ ������Ʈ (��ŵ)

			// JSP ����
			mav.setViewName("/order/orderResultFail");

			// ��� �ڵ�,�޽��� ������
			mav.addObject("responseCode", returnMap.get("responseCode"));
			mav.addObject("responseMsg", returnMap.get("responseMsg"));
		}
		// 3����



		// ������� ���� ���μ��� ����
		if ("0000".equals(responseCode)) {
			// ����
			mav.setViewName("/order/payToOrderGoodsKakao");
//			System.out.println("����Ȯ�� : "+mav.toString());

		} else {
			// ����
			mav.setViewName("/order/orderResultFail");
		}

		// �����ϱ� ��

		// 1. �ֹ� ������ ����
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("orderer");
		String member_id = memberVO.getMember_id();
		String orderer_name = memberVO.getMember_name();
		String orderer_hp = memberVO.getHp1() + "-" + memberVO.getHp2() + "-" + memberVO.getHp3();
		List<OrderVO> myOrderList = (List<OrderVO>) session.getAttribute("myOrderList");

		for (int i = 0; i < myOrderList.size(); i++) {// �ֹ�â���� �Է��� ������ ������ ����� ������ �ֹ� ��ǰ ���� ��ϰ� ��ħ
														// �� orderVO�� ������ ������ ���� �� �ٽ� myOrderList�� ����
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
			myOrderList.set(i, orderVO); // �� orderVO�� �ֹ��� ������ ������ �� �ٽ� myOrderList�� �����Ѵ�.
		} // end for

		orderService.addNewOrder(myOrderList);// �ֹ��� ������ sql������ ����
		mav.addObject("myOrderInfo", receiverMap);// OrderVO�� �ֹ���� �������� �ֹ��� ������ ǥ���Ѵ�.
		mav.addObject("myOrderList", myOrderList);// �ֹ��Ϸ� ���â�� �ֹ� ��ǰ ����� ǥ���ϵ��� ����
		return mav;
	}

}
