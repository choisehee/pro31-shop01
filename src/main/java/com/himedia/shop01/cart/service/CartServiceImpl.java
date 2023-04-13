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
		List<CartVO> myCartList=cartDAO.selectCartList(cartVO);// 장바구니 페이지에 표시할 장바구니 정보를 조회
		if(myCartList.size()==0){ //카트에 저장된 상품이없는 경우
			return null;//장바구니에 상품이 없는경우 null을 반환
		}
		List<GoodsVO> myGoodsList=cartDAO.selectGoodsList(myCartList);//장바구니 페이지에 표시할 상품 정보 조회
		cartMap.put("myCartList", myCartList);
		cartMap.put("myGoodsList",myGoodsList);
		return cartMap;//장바구니 정보와 상품 정보를 cartMap에 저장하여 반환
	}
	
	public boolean findCartGoods(CartVO cartVO) throws Exception{//테이블에 추가하기 전에 동일한 상품번호의 개수를 조회
		 return cartDAO.selectCountInCart(cartVO);
		
	}	
	public void addGoodsInCart(CartVO cartVO) throws Exception{//장바구니 추가
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
