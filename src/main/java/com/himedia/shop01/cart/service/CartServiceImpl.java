package com.himedia.shop01.cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.himedia.shop01.cart.dao.CartDAO;
import com.himedia.shop01.cart.vo.CartVO;
import com.himedia.shop01.goods.vo.GoodsVO;

@Service("cartService")
@Transactional(propagation=Propagation.REQUIRED)
public class CartServiceImpl  implements CartService{
	@Autowired
	private CartDAO cartDAO;
	
	public Map<String ,List> myCartList(CartVO cartVO) throws Exception{
		Map<String,List> cartMap=new HashMap<String,List>();
		List<CartVO> myCartList=cartDAO.selectCartList(cartVO);// ��ٱ��� �������� ǥ���� ��ٱ��� ������ ��ȸ
		if(myCartList.size()==0){ //īƮ�� ����� ��ǰ�̾��� ���
			return null;//��ٱ��Ͽ� ��ǰ�� ���°�� null�� ��ȯ
		}
		List<GoodsVO> myGoodsList=cartDAO.selectGoodsList(myCartList);//��ٱ��� �������� ǥ���� ��ǰ ���� ��ȸ
		cartMap.put("myCartList", myCartList);
		cartMap.put("myGoodsList",myGoodsList);
		return cartMap;//��ٱ��� ������ ��ǰ ������ cartMap�� �����Ͽ� ��ȯ
	}
	
	public boolean findCartGoods(CartVO cartVO) throws Exception{//���̺� �߰��ϱ� ���� ������ ��ǰ��ȣ�� ������ ��ȸ
		 return cartDAO.selectCountInCart(cartVO);
		
	}	
	public void addGoodsInCart(CartVO cartVO) throws Exception{//��ٱ��� �߰�
		cartDAO.insertGoodsInCart(cartVO);
	}
	
	public boolean modifyCartQty(CartVO cartVO) throws Exception{
		boolean result=true;
		cartDAO.updateCartGoodsQty(cartVO);
		return result;
	}
	public void removeCartGoods(int cart_id) throws Exception{
		cartDAO.deleteCartGoods(cart_id);
	}
	
}
