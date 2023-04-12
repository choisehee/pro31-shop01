package com.himedia.shop01.cart.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.himedia.shop01.cart.vo.CartVO;
import com.himedia.shop01.goods.vo.GoodsVO;

public interface CartDAO {
	public List<CartVO> selectCartList(CartVO cartVO) throws DataAccessException;//CartVO 객체를 전달받아 해당 멤버의 장바구니 목록을 조회
	public List<GoodsVO> selectGoodsList(List<CartVO> cartList) throws DataAccessException;//장바구니에 담긴 상품 정보를 조회 테이블에서 해당 상품의 정보를 조회
	public boolean selectCountInCart(CartVO cartVO) throws DataAccessException;//멤버의 장바구니에 해당 상품이 있는지 여부를 조회
	public void insertGoodsInCart(CartVO cartVO) throws DataAccessException;// 멤버의 장바구니에 상품을 추가
	public void updateCartGoodsQty(CartVO cartVO) throws DataAccessException;// 멤버의 장바구니에서 상품 수량을 수정
	public void deleteCartGoods(int cart_id) throws DataAccessException;//장바구니 상품을 삭제
	
	

}
