package com.himedia.shop01.order.service;

import java.util.List;
import java.util.Map;

import com.himedia.shop01.order.vo.OrderVO;

public interface OrderService {
	public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws Exception;//주문한 상품 목록을 조회
	public void addNewOrder(List<OrderVO> myOrderList) throws Exception;//주문 정보를 데이터베이스에 저장
	public OrderVO findMyOrder(String order_id) throws Exception;// 주문번호에 해당하는 주문 정보를 조회
	
	
}
