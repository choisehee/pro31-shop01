package com.himedia.shop01.goods.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.himedia.shop01.goods.vo.GoodsVO;

public interface GoodsService {
	//예외 발생 가능성이 있으므로 throws Exception처리를 한다
	
	//Map으로 키와 밸류값의 형태의 자료형 스트링이 키 List<GoodsVO>가 밸류이다 
	//List<GoodsVO>형태의 정보를 리스트형식으로 조회하여 카테고리 이름을 키로 갖는 Map 객체에 담아 반환 
	public Map<String,List<GoodsVO>> listGoods() throws Exception;//상품 정보 조회
	
	//매개변수 _goods_id는 조회하고자 하는 상품의 id 값을 받아 그 값을 통해 해당 상품의 상세 정보를 조회 이 정보는 Map객체에 담아 반환한다
	public Map goodsDetail(String _goods_id) throws Exception;//상품 상세 정보 조회
	
	//keyword라는 단어가 포함된 텍스트를 검색하고 텍스트가 포함된 문자열의 목록을 반환
	public List<String> keywordSearch(String keyword) throws Exception;//키워드검색
	
	//searchWord 검색어를 이용하여 상품 정보를 검색하고, 검색된 상품 정보를 포함하는 GoodsVO 객체의 목록(List<GoodsVO>)을 반환
	//반환하는 GoodsVO 객체 목록에는 검색어를 포함하는 상품 정보가 포함
	//검색어와 일치하는 상품 정보를 찾기 위해 데이터베이스나 다른 저장소에서 상품 정보를 검색
	//검색된 상품 정보는 GoodsVO 객체로 변환되어 목록에 추가
	public List<GoodsVO> searchGoods(String searchWord) throws Exception;//상품목록검색
}
