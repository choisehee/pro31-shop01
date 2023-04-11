package com.himedia.shop01.goods.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.himedia.shop01.goods.vo.GoodsVO;
import com.himedia.shop01.goods.vo.ImageFileVO;

//MyBatis�� ����Ͽ� �����ͺ��̽��� ��ǰ ������ ��ȣ�ۿ�
@Repository("goodsDAO")
public class GoodsDAOImpl implements GoodsDAO{
	
	@Autowired
	private SqlSession sqlSession;

	//goodsStatus�� �ް� GoodsVO ��ü ����� ��ȯ
	//  �����ͺ��̽���MyBatis �����ӿ�ũ�� ����Ͽ� ��ǰ ���� (��: "����Ʈ����")�� ���� ��ǰ ����� �˻�
	@Override
	public List<GoodsVO> selectGoodsList(String goodsStatus) throws DataAccessException {
		//"bestseller" ������ ��ǰ ����� ��ȸ�ϴ� �ڵ�
		List<GoodsVO> goodsList=(ArrayList)sqlSession.selectList("mapper.goods.selectGoodsList",goodsStatus);
		   return goodsList;
	}

	//�Ű����� keyword�� �ް� ���ڿ� ��ü ����� ��ȯ
	//�����ͺ��̽��� MyBatis �����ӿ�ũ�� ����Ͽ� �Է� Ű����� ��ġ�ϴ� Ű���� ����� �˻�
	@Override
	public List<String> selectKeywordSearch(String keyword) throws DataAccessException {
		 List<String> list=(ArrayList)sqlSession.selectList("mapper.goods.selectKeywordSearch",keyword);
		   return list;
	}

	//��ǰ �� ������ ��ȸ�ϴ� ���
	@Override
	public GoodsVO selectGoodsDetail(String goods_id) throws DataAccessException {
		GoodsVO goodsVO=(GoodsVO)sqlSession.selectOne("mapper.goods.selectGoodsDetail",goods_id);
		return goodsVO;
	}

	// ��ǰ �� ������ ���� �̹��� ���� ������ ��ȸ
	@Override
	public List<ImageFileVO> selectGoodsDetailImage(String goods_id) throws DataAccessException {
		List<ImageFileVO> imageList=(ArrayList)sqlSession.selectList("mapper.goods.selectGoodsDetailImage",goods_id);
		return imageList;
	}

	//�˻�� �����ϴ� ��ǰ ������ ��ȸ�ϴ� ���
	//�Է� �˻���� ��ġ�ϴ� ��ǰ ����� �˻�
	@Override
	public List<GoodsVO> selectGoodsBySearchWord(String searchWord) throws DataAccessException {
		ArrayList list=(ArrayList)sqlSession.selectList("mapper.goods.selectGoodsBySearchWord",searchWord);
		 return list;
	}
	
	

}
