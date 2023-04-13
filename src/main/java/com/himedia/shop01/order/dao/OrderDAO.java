package com.himedia.shop01.order.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.himedia.shop01.order.vo.OrderVO;

public interface OrderDAO {
	public List<OrderVO> listMyOrderGoods(OrderVO orderBean) throws DataAccessException;//주문 상품 목록을 조회
	public void insertNewOrder(List<OrderVO> myOrderList) throws DataAccessException;//새로운 주문을 추가
	public OrderVO findMyOrder(String order_id) throws DataAccessException;//특정 주문을 조회하는 기능
	public void removeGoodsFromCart(List<OrderVO> myOrderList)throws DataAccessException;//장바구니에서 상품을 제거
}
