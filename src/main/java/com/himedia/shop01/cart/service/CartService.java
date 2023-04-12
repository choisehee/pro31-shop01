package com.himedia.shop01.cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.himedia.shop01.cart.vo.CartVO;
import com.himedia.shop01.goods.vo.GoodsVO;

public interface CartService {
	public Map<String ,List> myCartList(CartVO cartVO) throws Exception;//��ٱ��� ����� ��ȸ
	public boolean findCartGoods(CartVO cartVO) throws Exception;//��ٱ��Ͽ� Ư�� ��ǰ�� �̹� ��� �ִ��� Ȯ��
	public void addGoodsInCart(CartVO cartVO) throws Exception;//��ٱ��Ͽ� ��ǰ�� �߰�
	public boolean modifyCartQty(CartVO cartVO) throws Exception;// ��ٱ��Ͽ� ��� ��ǰ�� ������ ����
	public void removeCartGoods(int cart_id) throws Exception;//��ٱ��Ͽ��� ��ǰ�� ����
}
