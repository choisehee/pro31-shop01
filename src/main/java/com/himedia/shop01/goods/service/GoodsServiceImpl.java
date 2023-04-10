package com.himedia.shop01.goods.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.himedia.shop01.goods.dao.GoodsDAO;
import com.himedia.shop01.goods.vo.GoodsVO;
import com.himedia.shop01.goods.vo.ImageFileVO;

@Service("goodsService")
//propagation 속성은 트랜잭션 전파 방식을 설정하는데 사용됩니다. REQUIRED는 기존 트랜잭션이 있으면 해당 트랜잭션에 참여하고, 없으면 새로운 트랜잭션을 생성
@Transactional(propagation=Propagation.REQUIRED)
public class GoodsServiceImpl implements GoodsService{
	
	@Autowired
	private GoodsDAO goodsDAO;

	public Map<String,List<GoodsVO>> listGoods() throws Exception {
		// Map 인터페이스를 구현한 HashMap 클래스의 인스턴스를 생성하는 코드
		Map<String,List<GoodsVO>> goodsMap=new HashMap<String,List<GoodsVO>>();
		//selectGoodsList 메서드를 호출하여, "bestseller" 상태인 상품 목록을 조회
		//결과값을 List<GoodsVO> 타입의 변수인 goodsList에 대입
		List<GoodsVO> goodsList=goodsDAO.selectGoodsList("bestseller");
		//put() 메소드는 Map 인터페이스에서 제공하는 메소드로, 주어진 키와 값 쌍을 Map 객체에 저장하는 역할
		goodsMap.put("bestseller",goodsList);
		goodsList=goodsDAO.selectGoodsList("newbook");
		goodsMap.put("newbook",goodsList);
		
		goodsList=goodsDAO.selectGoodsList("steadyseller");
		goodsMap.put("steadyseller",goodsList);
		return goodsMap;
	}

	@Override
	public Map goodsDetail(String _goods_id) throws Exception {
		Map goodsMap=new HashMap();
		GoodsVO goodsVO = goodsDAO.selectGoodsDetail(_goods_id);
		goodsMap.put("goodsVO", goodsVO);
		List<ImageFileVO> imageList =goodsDAO.selectGoodsDetailImage(_goods_id);
		goodsMap.put("imageList", imageList);
		return goodsMap;
	}

	@Override
	public List<String> keywordSearch(String keyword) throws Exception {
		List<String> list=goodsDAO.selectKeywordSearch(keyword);
		return list;
	}

	@Override
	public List<GoodsVO> searchGoods(String searchWord) throws Exception {
		List goodsList=goodsDAO.selectGoodsBySearchWord(searchWord);
		return goodsList;
	}
}
