package com.himedia.shop01.cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.himedia.shop01.cart.vo.CartVO;
import com.himedia.shop01.goods.vo.GoodsVO;

public interface CartService {
	public Map<String ,List> myCartList(CartVO cartVO) throws Exception;//장바구니 목록을 조회
	public boolean findCartGoods(CartVO cartVO) throws Exception;//장바구니에 특정 상품이 이미 담겨 있는지 확인
	public void addGoodsInCart(CartVO cartVO) throws Exception;//장바구니에 상품을 추가
	public boolean modifyCartQty(CartVO cartVO) throws Exception;// 장바구니에 담긴 상품의 수량을 수정
	public void removeCartGoods(int cart_id) throws Exception;//장바구니에서 상품을 제거
}
