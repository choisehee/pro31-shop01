package com.himedia.shop01.order.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.himedia.shop01.order.vo.OrderVO;

public interface OrderDAO {
	public List<OrderVO> listMyOrderGoods(OrderVO orderBean) throws DataAccessException;//�ֹ� ��ǰ ����� ��ȸ
	public void insertNewOrder(List<OrderVO> myOrderList) throws DataAccessException;//���ο� �ֹ��� �߰�
	public OrderVO findMyOrder(String order_id) throws DataAccessException;//Ư�� �ֹ��� ��ȸ�ϴ� ���
	public void removeGoodsFromCart(List<OrderVO> myOrderList)throws DataAccessException;//��ٱ��Ͽ��� ��ǰ�� ����
}
