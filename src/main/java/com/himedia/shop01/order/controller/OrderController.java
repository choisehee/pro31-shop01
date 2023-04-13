package com.himedia.shop01.order.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.himedia.shop01.order.vo.OrderVO;

public interface OrderController {
	//ModelAndView 객체를 반환하여 주문 결과 화면으로 보여주는 기능
	public ModelAndView orderEachGoods(@ModelAttribute("orderVO") OrderVO _orderVO,HttpServletRequest request, HttpServletResponse response)  throws Exception;
	//장바구니에서 선택한 모든 상품에 대한 주문을 처리하는 기능
	public ModelAndView orderAllCartGoods(@RequestParam  String[] cart_goods_qty,HttpServletRequest request, HttpServletResponse response)  throws Exception;
	//결제 결과 화면으로 보여주는 기능
	public ModelAndView payToOrderGoods(@RequestParam Map<String, String> orderMap,HttpServletRequest request, HttpServletResponse response)  throws Exception;
}
