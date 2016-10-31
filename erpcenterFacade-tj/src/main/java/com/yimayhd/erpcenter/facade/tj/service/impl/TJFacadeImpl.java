package com.yimayhd.erpcenter.facade.tj.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.tj.TJBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.dal.basic.constant.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;
import com.yimayhd.erpcenter.facade.tj.client.query.TjSearchDTO;
import com.yimayhd.erpcenter.facade.tj.client.result.SelectShopProjectListResult;
import com.yimayhd.erpcenter.facade.tj.client.result.SelectTJGroupShopListResult;
import com.yimayhd.erpcenter.facade.tj.client.result.ShopProjectResult;
import com.yimayhd.erpcenter.facade.tj.client.result.TjSearchResult;
import com.yimayhd.erpcenter.facade.tj.client.service.TJFacade;
import com.yimayhd.erpresource.biz.service.ContractBiz;
import com.yimayhd.erpresource.dal.po.SupplierContractPriceDateInfo;

/**
 * 财务统计
 * @author wj
 *
 */
public class TJFacadeImpl implements TJFacade{
	@Autowired
	private TJBiz tjBiz;
	@Autowired
	private DicBiz dicBiz;
	@Autowired
	private PlatformOrgBiz platformOrgBiz;
	@Autowired
	private PlatformEmployeeBiz platformEmployeeBiz;
	@Autowired
	private ContractBiz contractBiz;
	/**
	 * 购物项目统计
	 * 
	 */
	public TjSearchResult toShopProjectList(int bizId) {
		TjSearchResult result = new TjSearchResult();
		//产品品牌下拉选择数据
		List<DicInfo> pp = dicBiz.getListByTypeCode(BasicConstants.CPXL_PP,bizId);
		result.setPp(pp);
		result.setBizId(bizId);
		result.setOrgJsonStr(platformOrgBiz.getComponentOrgTreeJsonStr(bizId));
		result.setOrgUserJsonStr(platformEmployeeBiz.getComponentOrgUserTreeJsonStr(bizId));
		return result;
	}
	
	public SelectShopProjectListResult selectShopProjectList(TjSearchDTO tjSearchDTO) {
		SelectShopProjectListResult result = new SelectShopProjectListResult();
		int bizId = tjSearchDTO.getBizId();
		Integer page = tjSearchDTO.getPage();
		Integer pageSize = tjSearchDTO.getPageSize();
		TourGroupVO group = tjSearchDTO.getGroup();
		Set<Integer> dataUserIdSet = tjSearchDTO.getDataUserIdSet();
		Map<String, Object> pm = tjSearchDTO.getPm();
		PageBean pageBean = new PageBean();
		if (page == null) {
			pageBean.setPage(1);
		} else {
			pageBean.setPage(page);
		}
		if (pageSize == null) {
			pageBean.setPageSize(Constants.PAGESIZE);
		} else {
			pageBean.setPageSize(pageSize);
		}
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(group.getSaleOperatorIds()) && StringUtils.isNotBlank(group.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = group.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				group.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		if(null!=group.getSaleOperatorIds() && !"".equals(group.getSaleOperatorIds())){
			pm.put("operator_id", group.getSaleOperatorIds());
		}
		pm.put("set", dataUserIdSet);
		pageBean.setParameter(pm);
		pageBean = tjBiz.selectShopProjectListPage(pageBean,bizId);
		result.setPageBean(pageBean);
		Map<String,Object> map = tjBiz.selectCount(pageBean,bizId);
		Map<String,Object> mapDetail = tjBiz.selectDetailCount(pageBean,bizId);
		List<SupplierContractPriceDateInfo> priceList = contractBiz.getMultiOperatePriceByParams(bizId, BasicConstants.TJ_PROJECT_SHOP_BOOKING_SUPPLIER_TYPE,null, null,null, null,null, null);
		result.setPriceList(priceList);
		if(null!=map){
			result.setAllTotalPerson((String)map.get("all_total_person"));
			result.setAllTotalFace((String)map.get("all_total_face"));
		}
		if(null!=mapDetail){
			result.setAllBuyTotal((String)mapDetail.get("all_buy_total"));
			result.setAllRepayTotal((String)mapDetail.get("all_repay_total"));
		}
		return result;
	}
	
	/**
	 * 单团购物统计
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public TjSearchResult toGroupShopList(int bizId) {
		TjSearchResult result = new TjSearchResult();
		result.setBizId(bizId);
		result.setOrgJsonStr(platformOrgBiz.getComponentOrgTreeJsonStr(bizId));
		result.setOrgUserJsonStr(platformEmployeeBiz.getComponentOrgUserTreeJsonStr(bizId));
		return result;
	}
	
	public SelectTJGroupShopListResult SelectTJGroupShopListResult(TjSearchDTO tjSearchDTO) {
		SelectTJGroupShopListResult result = new SelectTJGroupShopListResult();
		Integer pageSize = tjSearchDTO.getPageSize();
		Integer page = tjSearchDTO.getPage();
		int bizId = tjSearchDTO.getBizId();
		TourGroupVO groupVO = tjSearchDTO.getGroup();
		Map<String, Object> pm = tjSearchDTO.getPm();
		Set<Integer> dataUserIdSet = tjSearchDTO.getDataUserIdSet();
		PageBean pageBean = new PageBean();
		if (page == null) {
			pageBean.setPage(1);
		} else {
			pageBean.setPage(page);
		}
		if (pageSize == null) {
			pageBean.setPageSize(Constants.PAGESIZE);
		} else {
			pageBean.setPageSize(pageSize);
		}
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(groupVO.getSaleOperatorIds()) && StringUtils.isNotBlank(groupVO.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = groupVO.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				groupVO.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		if(null!=groupVO.getSaleOperatorIds() && !"".equals(groupVO.getSaleOperatorIds())){
			pm.put("operator_id", groupVO.getSaleOperatorIds());
		}
		pm.put("set", dataUserIdSet);
		pageBean.setParameter(pm);
		pageBean = tjBiz.selectTJGroupListPage(pageBean);
		List<Map<String, Object>> groups = pageBean.getResult();
		if(groups != null){
			Map<String, Object> group = null;
			for(int i = 0; i < groups.size(); i++){
				group = groups.get(i);
				Integer groupId = group.get("group_id") != null ? Integer.parseInt(group.get("group_id").toString()) : 0;
				pm.put("groupId", groupId);
				List<Map<String, Object>> shops = tjBiz.selectTJShopOfGroup(pm);
				group.put("shops", shops);
			}
		}
		
		result.setPageBean(pageBean);
		Map<String, Object> totalMap = tjBiz.selectTJGroupShopTotal(pageBean);
		result.setTotalMap(totalMap);
		Map<String, Object> totalCommMap = tjBiz.selectTJGroupShopCommTotal(pageBean);
		result.setTotalCommMap(totalCommMap);
		return result;
	}
	
	/**
	 * 跳转到单团购物统计打印页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public PageBean toGroupShopListPrint(TjSearchDTO tjSearchDTO) {
		int bizId = tjSearchDTO.getBizId();
		TourGroupVO groupVO = tjSearchDTO.getGroup();
		Set<Integer> dataUserIdSet = tjSearchDTO.getDataUserIdSet();
		Map<String, Object> pm = tjSearchDTO.getPm();
		PageBean pageBean = new PageBean();
		pageBean.setPage(1);
		pageBean.setPageSize(1000000);
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(groupVO.getSaleOperatorIds()) && StringUtils.isNotBlank(groupVO.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = groupVO.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				groupVO.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		if(null!=groupVO.getSaleOperatorIds() && !"".equals(groupVO.getSaleOperatorIds())){
			pm.put("operator_id", groupVO.getSaleOperatorIds());
		}
		pm.put("set", dataUserIdSet);
		pageBean.setParameter(pm);
		pageBean = tjBiz.selectTJGroupListPage(pageBean);
		List<Map<String, Object>> groups = pageBean.getResult();
		if(groups != null){
			Map<String, Object> group = null;
			for(int i = 0; i < groups.size(); i++){
				group = groups.get(i);
				Integer groupId = group.get("group_id") != null ? Integer.parseInt(group.get("group_id").toString()) : 0;
				pm.put("groupId", groupId);
				List<Map<String, Object>> shops = tjBiz.selectTJShopOfGroup(pm);
				group.put("shops", shops);
			}
		}
//		String imgPath = bizSettingCommon.getMyBizLogo(request);
//		model.addAttribute("imgPath", imgPath);
//		model.addAttribute("printMsg", "打印人："+WebUtils.getCurUser(request).getName()+" <br/>打印时间："+DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return pageBean;
	}
	
	public PageBean selectTJGroupListPage(PageBean pageBean){
		return tjBiz.selectTJGroupListPage(pageBean);
	}
	public List<Map<String, Object>> selectTJShopOfGroup(Map<String,Object> pm){
		return tjBiz.selectTJShopOfGroup(pm);
	}
	
	/**
	 * 按购物店统计
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public TjSearchResult toShopList(int bizId) {
		return this.toShopProjectList(bizId);
	}
	
	public SelectTJGroupShopListResult selectShopList(TjSearchDTO tjSearchDTO) {
		SelectTJGroupShopListResult result = new SelectTJGroupShopListResult();
		Integer pageSize = tjSearchDTO.getPageSize();
		Integer page = tjSearchDTO.getPage();
		int bizId = tjSearchDTO.getBizId();
		TourGroupVO group = tjSearchDTO.getGroup();
		Map<String, Object> pm = tjSearchDTO.getPm();
		Set<Integer> dataUserIdSet = tjSearchDTO.getDataUserIdSet();
		
		PageBean pageBean = new PageBean();
		if (page == null) {
			pageBean.setPage(1);
		} else {
			pageBean.setPage(page);
		}
		if (pageSize == null) {
			pageBean.setPageSize(Constants.PAGESIZE);
		} else {
			pageBean.setPageSize(pageSize);
		}
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(group.getSaleOperatorIds()) && StringUtils.isNotBlank(group.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = group.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				group.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		if(null!=group.getSaleOperatorIds() && !"".equals(group.getSaleOperatorIds())){
			pm.put("operator_id", group.getSaleOperatorIds());
		}
		pm.put("set", dataUserIdSet);
		pageBean.setParameter(pm);
		pageBean = tjBiz.selectTJShopListPage(pageBean);
		result.setPageBean(pageBean);
		Map<String, Object> totalMap = tjBiz.selectTJShopTotal(pageBean);
		result.setTotalMap(totalMap);
		return result;
	}
	
	/**
	 * 跳转到购物店统计打印页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public PageBean toShopListPrint(TjSearchDTO tjSearchDTO) {
		int bizId = tjSearchDTO.getBizId();
		TourGroupVO group = tjSearchDTO.getGroup();
		Set<Integer> dataUserIdSet = tjSearchDTO.getDataUserIdSet();
		Map<String, Object> pm = tjSearchDTO.getPm();
		
		PageBean pageBean = new PageBean();
		pageBean.setPage(1);
		pageBean.setPageSize(1000000);
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(group.getSaleOperatorIds()) && StringUtils.isNotBlank(group.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = group.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				group.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		if(null!=group.getSaleOperatorIds() && !"".equals(group.getSaleOperatorIds())){
			pm.put("operator_id", group.getSaleOperatorIds());
		}
		pm.put("set", dataUserIdSet);
		pageBean.setParameter(pm);
		pageBean = tjBiz.selectTJShopListPage(pageBean);
//		String imgPath = bizSettingCommon.getMyBizLogo(request);
//		model.addAttribute("imgPath", imgPath);
//		model.addAttribute("pageBean", pageBean);
//		
//		model.addAttribute("printMsg", "打印人："+WebUtils.getCurUser(request).getName()+" <br/>打印时间："+DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return pageBean;
	}
	
	public Set<Integer> getUserIdListByOrgIdList(Set<Integer> set,int bizId){
		return platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
	}
	
	public PageBean selectTJShopListPage(PageBean pageBean){
		return tjBiz.selectTJShopListPage(pageBean);
	}
	
	public String findByOrgPath(String str){
		return platformEmployeeBiz.findByOrgPath(str);
	}
	
	/**
	 * 购物项目统计打印页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public SelectShopProjectListResult shopProjectPrint(TjSearchDTO tjSearchDTO) {
		SelectShopProjectListResult result = new SelectShopProjectListResult();
		int bizId = tjSearchDTO.getBizId();
		TourGroupVO group = tjSearchDTO.getGroup();
		Map<String, Object> pm = tjSearchDTO.getPm();
		Set<Integer> dataUserIdSet = tjSearchDTO.getDataUserIdSet();
		
		PageBean pageBean = new PageBean();
		pageBean.setPage(1);
		pageBean.setPageSize(1000000);
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(group.getSaleOperatorIds()) && StringUtils.isNotBlank(group.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = group.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				group.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		if(null!=group.getSaleOperatorIds() && !"".equals(group.getSaleOperatorIds())){
			pm.put("operator_id", group.getSaleOperatorIds());
		}
		pm.put("set", dataUserIdSet);
		pageBean.setParameter(pm);
		
		pageBean = tjBiz.selectShopProjectListPage(pageBean,bizId);
		
		Map<String,Object> map = tjBiz.selectCount(pageBean,bizId);
		Map<String,Object> mapDetail = tjBiz.selectDetailCount(pageBean,bizId);
		if(null!=map){
			result.setAllTotalPerson((String)map.get("all_total_person"));
			result.setAllTotalFace((String)map.get("all_total_face"));
		}
		if(null!=mapDetail){
			result.setAllBuyTotal((String)mapDetail.get("all_buy_total"));
			result.setAllRepayTotal((String)mapDetail.get("all_repay_total"));
		}
//		String imgPath = bizSettingCommon.getMyBizLogo(request);
//		model.addAttribute("imgPath", imgPath);
//		model.addAttribute("bizId", WebUtils.getCurBizId(request)); // 过滤B商家
		result.setPageBean(pageBean);
//		model.addAttribute("printMsg", "打印人："+WebUtils.getCurUser(request).getName()+" 打印时间："+DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return result;
	}
	
	public ShopProjectResult selectShopProject(int bizId,PageBean pageBean){
		ShopProjectResult result = new ShopProjectResult();
		pageBean = tjBiz.selectShopProjectListPage(pageBean,bizId);
		Map<String,Object> map = tjBiz.selectCount(pageBean,bizId);
		Map<String,Object> mapDetail = tjBiz.selectDetailCount(pageBean,bizId);
		result.setPageBean(pageBean);
		result.setMap(map);
		result.setMapDetail(mapDetail);
		return result;
	}
	
}
