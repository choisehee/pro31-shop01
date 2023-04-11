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

//��ǰ�� ���õ� ���  
@Service("goodsService")
//propagation �Ӽ��� Ʈ����� ���� ����� �����ϴµ� ���˴ϴ�. REQUIRED�� ���� Ʈ������� ������ �ش� Ʈ����ǿ� �����ϰ�, ������ ���ο� Ʈ������� ����
@Transactional(propagation=Propagation.REQUIRED)
public class GoodsServiceImpl implements GoodsService{
	
	@Autowired
	private GoodsDAO goodsDAO;

	//"bestseller", "newbook", "steadyseller" ������  ��ǰ����� ��ȸ�ϴ� ���
	public Map<String,List<GoodsVO>> listGoods() throws Exception {
		// Map �������̽��� ������ HashMap Ŭ������ �ν��Ͻ��� �����ϴ� �ڵ�
		Map<String,List<GoodsVO>> goodsMap=new HashMap<String,List<GoodsVO>>();
		//selectGoodsList �޼��带 ȣ���Ͽ�, "bestseller" ������ ��ǰ ����� ��ȸ
		//������� List<GoodsVO> Ÿ���� ������ goodsList�� ����
		List<GoodsVO> goodsList=goodsDAO.selectGoodsList("bestseller");
		//put() �޼ҵ�� Map �������̽����� �����ϴ� �޼ҵ��, �־��� Ű�� �� ���� Map ��ü�� �����ϴ� ����
		goodsMap.put("bestseller",goodsList);
		goodsList=goodsDAO.selectGoodsList("newbook");
		goodsMap.put("newbook",goodsList);
		
		goodsList=goodsDAO.selectGoodsList("steadyseller");
		goodsMap.put("steadyseller",goodsList);
		return goodsMap;
	}

	//Ư�� ��ǰ�� �� ������ �ش� ��ǰ�� ���õ� �̹��� ���� ������ ��ȸ�ϴ� ���
	@Override
	public Map goodsDetail(String _goods_id) throws Exception {
		Map goodsMap=new HashMap();
		GoodsVO goodsVO = goodsDAO.selectGoodsDetail(_goods_id);
		goodsMap.put("goodsVO", goodsVO);
		List<ImageFileVO> imageList =goodsDAO.selectGoodsDetailImage(_goods_id);
		goodsMap.put("imageList", imageList);
		return goodsMap;
	}

	//Ű���带 �̿��Ͽ� ��ǰ�� �˻��ϴ� ���
	@Override
	public List<String> keywordSearch(String keyword) throws Exception {
		List<String> list=goodsDAO.selectKeywordSearch(keyword);
		return list;
	}

	//�˻�� �̿��Ͽ� ��ǰ�� �˻��ϴ� ���
	@Override
	public List<GoodsVO> searchGoods(String searchWord) throws Exception {
		List goodsList=goodsDAO.selectGoodsBySearchWord(searchWord);
		return goodsList;
	}
}
