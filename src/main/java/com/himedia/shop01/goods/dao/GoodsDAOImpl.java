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

//MyBatis를 사용하여 데이터베이스와 상품 정보를 상호작용
@Repository("goodsDAO")
public class GoodsDAOImpl implements GoodsDAO{
	
	@Autowired
	private SqlSession sqlSession;

	//goodsStatus를 받고 GoodsVO 객체 목록을 반환
	//  데이터베이스를MyBatis 프레임워크를 사용하여 상품 상태 (예: "베스트셀러")에 따라 상품 목록을 검색
	@Override
	public List<GoodsVO> selectGoodsList(String goodsStatus) throws DataAccessException {
		//"bestseller" 상태인 상품 목록을 조회하는 코드
		List<GoodsVO> goodsList=(ArrayList)sqlSession.selectList("mapper.goods.selectGoodsList",goodsStatus);
		   return goodsList;
	}

	//매개변수 keyword를 받고 문자열 객체 목록을 반환
	//데이터베이스를 MyBatis 프레임워크를 사용하여 입력 키워드와 일치하는 키워드 목록을 검색
	@Override
	public List<String> selectKeywordSearch(String keyword) throws DataAccessException {
		 List<String> list=(ArrayList)sqlSession.selectList("mapper.goods.selectKeywordSearch",keyword);
		   return list;
	}

	//상품 상세 정보를 조회하는 기능
	@Override
	public GoodsVO selectGoodsDetail(String goods_id) throws DataAccessException {
		GoodsVO goodsVO=(GoodsVO)sqlSession.selectOne("mapper.goods.selectGoodsDetail",goods_id);
		return goodsVO;
	}

	// 상품 상세 정보에 대한 이미지 파일 정보를 조회
	@Override
	public List<ImageFileVO> selectGoodsDetailImage(String goods_id) throws DataAccessException {
		List<ImageFileVO> imageList=(ArrayList)sqlSession.selectList("mapper.goods.selectGoodsDetailImage",goods_id);
		return imageList;
	}

	//검색어를 포함하는 상품 정보를 조회하는 기능
	//입력 검색어와 일치하는 상품 목록을 검색
	@Override
	public List<GoodsVO> selectGoodsBySearchWord(String searchWord) throws DataAccessException {
		ArrayList list=(ArrayList)sqlSession.selectList("mapper.goods.selectGoodsBySearchWord",searchWord);
		 return list;
	}
	
	

}
