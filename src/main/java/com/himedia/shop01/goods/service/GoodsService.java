package com.himedia.shop01.goods.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.himedia.shop01.goods.vo.GoodsVO;

public interface GoodsService {
	//���� �߻� ���ɼ��� �����Ƿ� throws Exceptionó���� �Ѵ�
	
	//Map���� Ű�� ������� ������ �ڷ��� ��Ʈ���� Ű List<GoodsVO>�� ����̴� 
	//List<GoodsVO>������ ������ ����Ʈ�������� ��ȸ�Ͽ� ī�װ� �̸��� Ű�� ���� Map ��ü�� ��� ��ȯ 
	public Map<String,List<GoodsVO>> listGoods() throws Exception;//��ǰ ���� ��ȸ
	
	//�Ű����� _goods_id�� ��ȸ�ϰ��� �ϴ� ��ǰ�� id ���� �޾� �� ���� ���� �ش� ��ǰ�� �� ������ ��ȸ �� ������ Map��ü�� ��� ��ȯ�Ѵ�
	public Map goodsDetail(String _goods_id) throws Exception;//��ǰ �� ���� ��ȸ
	
	//keyword��� �ܾ ���Ե� �ؽ�Ʈ�� �˻��ϰ� �ؽ�Ʈ�� ���Ե� ���ڿ��� ����� ��ȯ
	public List<String> keywordSearch(String keyword) throws Exception;//Ű����˻�
	
	//searchWord �˻�� �̿��Ͽ� ��ǰ ������ �˻��ϰ�, �˻��� ��ǰ ������ �����ϴ� GoodsVO ��ü�� ���(List<GoodsVO>)�� ��ȯ
	//��ȯ�ϴ� GoodsVO ��ü ��Ͽ��� �˻�� �����ϴ� ��ǰ ������ ����
	//�˻���� ��ġ�ϴ� ��ǰ ������ ã�� ���� �����ͺ��̽��� �ٸ� ����ҿ��� ��ǰ ������ �˻�
	//�˻��� ��ǰ ������ GoodsVO ��ü�� ��ȯ�Ǿ� ��Ͽ� �߰�
	public List<GoodsVO> searchGoods(String searchWord) throws Exception;//��ǰ��ϰ˻�
}
