package com.himedia.shop01.cart.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public interface CartController {
	public ModelAndView myCartMain(HttpServletRequest request, HttpServletResponse response)  throws Exception;//상품 리스트 조회
	public @ResponseBody String addGoodsInCart(@RequestParam("goods_id") int goods_id,HttpServletRequest request, HttpServletResponse response)  throws Exception;// 장바구니에 상품을 추가
	public  @ResponseBody String modifyCartQty(@RequestParam("goods_id") int goods_id,@RequestParam("cart_goods_qty") int cart_goods_qty,
			                  HttpServletRequest request, HttpServletResponse response)  throws Exception;//장바구니에 있는 상품의 수량을 수정
	public ModelAndView removeCartGoods(@RequestParam("cart_id") int cart_id,HttpServletRequest request, HttpServletResponse response)  throws Exception;//장바구니에서 상품을 삭제
	
	

}
