package com.himedia.shop01.cart.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.himedia.shop01.cart.vo.CartVO;
import com.himedia.shop01.goods.vo.GoodsVO;

public interface CartDAO {
	public List<CartVO> selectCartList(CartVO cartVO) throws DataAccessException;//CartVO ��ü�� ���޹޾� �ش� ����� ��ٱ��� ����� ��ȸ
	public List<GoodsVO> selectGoodsList(List<CartVO> cartList) throws DataAccessException;//��ٱ��Ͽ� ��� ��ǰ ������ ��ȸ ���̺��� �ش� ��ǰ�� ������ ��ȸ
	public boolean selectCountInCart(CartVO cartVO) throws DataAccessException;//����� ��ٱ��Ͽ� �ش� ��ǰ�� �ִ��� ���θ� ��ȸ
	public void insertGoodsInCart(CartVO cartVO) throws DataAccessException;// ����� ��ٱ��Ͽ� ��ǰ�� �߰�
	public void updateCartGoodsQty(CartVO cartVO) throws DataAccessException;// ����� ��ٱ��Ͽ��� ��ǰ ������ ����
	public void deleteCartGoods(int cart_id) throws DataAccessException;//��ٱ��� ��ǰ�� ����
	
	

}
