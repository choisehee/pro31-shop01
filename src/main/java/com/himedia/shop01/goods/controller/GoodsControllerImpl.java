package com.himedia.shop01.goods.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.himedia.shop01.common.base.BaseController;
import com.himedia.shop01.goods.service.GoodsService;
import com.himedia.shop01.goods.vo.GoodsVO;

import net.sf.json.JSONObject;

///goods URL ������ ó���ϴ� �޼ҵ带 ������ �ִ�
@Controller("goodsController")
@RequestMapping(value = "/goods")
public class GoodsControllerImpl extends BaseController implements GoodsController {

	@Autowired
	private GoodsService goodsService;

	//goods_id�� �Ķ���ͷ� �޾� �ش� ��ǰ�� �� ������ �����ͼ� ModelAndView ��ü�� ��� �ش��ϴ� �並 ��ȯ�մϴ�. ����, �ֱ� �� ��ǰ ����Ʈ�� �ش� ��ǰ�� �߰�
	@Override
	@RequestMapping(value = "/goodsDetail.do", method = RequestMethod.GET)
	public ModelAndView goodsDetail(@RequestParam("goods_id") String goods_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		System.out.println(viewName);
		HttpSession session = request.getSession();
		Map goodsMap = goodsService.goodsDetail(goods_id);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("goodsMap", goodsMap);
		GoodsVO goodsVO = (GoodsVO) goodsMap.get("goodsVO");
		addGoodsInQuick(goods_id, goodsVO, session);
		return mav;
	}

	// keyword�� �Ķ���ͷ� �޾� �ش��ϴ� ��ǰ�� Ű���� ����Ʈ�� �����ͼ� JSONObject �������� ��ȯ
	// produces : The producible media types of the mapped request, narrowing the
	// primary mapping.
	@RequestMapping(value = "/keywordSearch.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public String keywordSearch(@RequestParam("keyword") String keyword, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println(keyword);

		if (keyword == null || keyword.equals(""))
			return null;

		keyword = keyword.toUpperCase();
		List<String> keywordList = goodsService.keywordSearch(keyword);

		// ���� �ϼ��� JSONObject ����(��ü)
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("keyword", keywordList);

		String jsonInfo = jsonObject.toString();
		System.out.println(jsonInfo);
		return jsonInfo;

	}

	// searchWord�� �Ķ���ͷ� �޾� �ش��ϴ� ��ǰ ����Ʈ�� �����ͼ� ModelAndView ��ü�� ��� �ش��ϴ� �並 ��ȯ
	@Override
	@RequestMapping(value="/searchGoods.do" ,method = RequestMethod.GET)
	public ModelAndView searchGoods(@RequestParam("searchWord") String searchWord, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String viewName=(String)request.getAttribute("viewName");
		List<GoodsVO> goodsList=goodsService.searchGoods(searchWord);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("goodsList", goodsList);
		return mav;
	}

//	 �ֱ� �� ��ǰ ����Ʈ�� �ش� ��ǰ�� �߰�
	private void addGoodsInQuick(String goods_id, GoodsVO goodsVO, HttpSession session) {
		boolean already_existed = false;
		List<GoodsVO> quickGoodsList; // �ֱ� �� ��ǰ ���� ArrayList
		quickGoodsList = (ArrayList<GoodsVO>) session.getAttribute("quickGoodsList");

		if (quickGoodsList != null) {
			if (quickGoodsList.size() < 4) { // �̸��� ��ǰ ����Ʈ�� ��ǰ������ ���� ������ ���
				for (int i = 0; i < quickGoodsList.size(); i++) {
					GoodsVO _goodsBean = (GoodsVO) quickGoodsList.get(i);
					if (goods_id.equals(_goodsBean.getGoods_id())) {
						already_existed = true;
						break;
					}
				}
				if (already_existed == false) {
					quickGoodsList.add(goodsVO);
				}
			}

		} else {
			quickGoodsList = new ArrayList<GoodsVO>();
			quickGoodsList.add(goodsVO);

		}
		session.setAttribute("quickGoodsList", quickGoodsList);
		session.setAttribute("quickGoodsListNum", quickGoodsList.size());
	}

}
