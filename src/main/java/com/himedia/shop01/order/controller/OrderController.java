package com.himedia.shop01.order.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.himedia.shop01.order.vo.OrderVO;

public interface OrderController {
	//ModelAndView ��ü�� ��ȯ�Ͽ� �ֹ� ��� ȭ������ �����ִ� ���
	public ModelAndView orderEachGoods(@ModelAttribute("orderVO") OrderVO _orderVO,HttpServletRequest request, HttpServletResponse response)  throws Exception;
	//��ٱ��Ͽ��� ������ ��� ��ǰ�� ���� �ֹ��� ó���ϴ� ���
	public ModelAndView orderAllCartGoods(@RequestParam  String[] cart_goods_qty,HttpServletRequest request, HttpServletResponse response)  throws Exception;
	//���� ��� ȭ������ �����ִ� ���
	public ModelAndView payToOrderGoods(@RequestParam Map<String, String> orderMap,HttpServletRequest request, HttpServletResponse response)  throws Exception;
}
