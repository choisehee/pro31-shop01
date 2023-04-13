package com.himedia.shop01.order.service;

import java.util.List;
import java.util.Map;

import com.himedia.shop01.order.vo.OrderVO;

public interface OrderService {
	public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws Exception;//�ֹ��� ��ǰ ����� ��ȸ
	public void addNewOrder(List<OrderVO> myOrderList) throws Exception;//�ֹ� ������ �����ͺ��̽��� ����
	public OrderVO findMyOrder(String order_id) throws Exception;// �ֹ���ȣ�� �ش��ϴ� �ֹ� ������ ��ȸ
	
	
}
