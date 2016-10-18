package com.yimayhd.erpcenter.dal.sys.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sys.dao.SysBizInfoMapper;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
import com.yimayhd.erpcenter.dal.sys.po.SettleApply;
import com.yimayhd.erpcenter.dal.sys.po.SysBizInfo;
import com.yimayhd.erpcenter.dal.sys.service.PlatformEmployeeDal;
import com.yimayhd.erpcenter.dal.sys.service.PlatformMenuDal;
import com.yimayhd.erpcenter.dal.sys.service.PlatformOrgDal;
import com.yimayhd.erpcenter.dal.sys.service.SettleApplyDal;
import com.yimayhd.erpcenter.dal.sys.service.SysBizInfoDal;

public class SysBizInfoDalImpl implements SysBizInfoDal {

	@Autowired
	private SysBizInfoMapper bizInfoDao;
	@Autowired
	private PlatformMenuDal platformMenuDal;
	@Autowired
	private PlatformEmployeeDal platformEmployeeDal;
	
	@Autowired
	private PlatformOrgDal platformOrgDal;
	@Autowired
	private SettleApplyDal settleApplyDal;
	
	@Override
	public SysBizInfo getBizInfoByCode(String code) {
		if(StringUtils.isNotBlank(code)){
			List<SysBizInfo> list = bizInfoDao.getListByCode(code);
			return list.size()>0 ? list.get(0):null; 
		}
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void addSysBizInfo(SysBizInfo sysBizInfo,SettleApply apply,PlatformEmployeePo empPo,String menuIds) {
			//1、保存商家信息
			sysBizInfo.setName(apply.getCorpFullName());
	
			sysBizInfo.setCreateTime(System.currentTimeMillis());
			bizInfoDao.insert(sysBizInfo);
			//2、保存组织机构
			PlatformOrgPo po=new PlatformOrgPo();
			//po.setOrgId(0);
			po.setBizId(sysBizInfo.getId());
			po.setCode(sysBizInfo.getCode());		
			po.setStatus(true);
			po.setName(apply.getCorpFullName());
			po.setParentId(0);
			po.setSysId(1);		
			po.setSeqNum(1);
			platformOrgDal.saveOrg(po);
			
			//3、保存管理员
			empPo.setBizId(sysBizInfo.getId());//插入sysBizInfo后可以获取其id值
			empPo.setIsSuper(1);
			empPo.setStatus(1);
			empPo.setOrgId(po.getOrgId());
			empPo.setName("管理员");
			
			platformEmployeeDal.saveEmployee(empPo, sysBizInfo.getId());
			//4、保存菜单权限
			platformMenuDal.addPlatformMenuAndBizLink(sysBizInfo.getId(), menuIds);
			
			//5、更新申请状态
			apply.setState(1);
			apply.setBizId(sysBizInfo.getId());
			settleApplyDal.updateApplyById(apply);
			
			//6、添加审核记录
			
			/*SettleApplyResult result=new SettleApplyResult();
			result.setApplyId(apply.getId());
			result.setState(1);
			result.setRemark("已开通");
			result.setIsApprove(1);
			settleApplyService.updateApplyResultById(result);*/
		
	}

	@Override
	public PageBean getSysBizInfoList(SysBizInfo biz,Integer page) {
		PageBean pageBean = new PageBean();
		pageBean.setPage(page);
		pageBean.setParameter(biz);
		pageBean.setPageSize(10);
		 List<SysBizInfo> result = bizInfoDao.getBizListPage(pageBean);
		 pageBean.setResult(result);
		 return pageBean;
	}

	@Override
	public SysBizInfo selectByPrimaryKey(Integer id) {
		return bizInfoDao.selectByPrimaryKey(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void configSysBizInfoById(SysBizInfo biz,String menuIds) {
		//List<String> menuIDs = StrUtils.strSplitList(menuIds);
		//1、删除掉之前的关系
		platformMenuDal.deleteSysMenuByBizId(biz.getId());
		if (menuIds!=null) {
			//2、插入
			platformMenuDal.addPlatformMenuAndBizLink(biz.getId(), menuIds);
		}
		/*for (int i = 0; i < menuIDs.size(); i++) {
			PlatformMenuPo menu=new PlatformMenuPo();
			menu.setBizId(biz.getId());
			menu.setMenuId(Integer.parseInt(menuIDs.get(i)));
//			menu.setCreateTime(System.currentTimeMillis());
			sysMenuService.updatePlatformMenuAndBizLink(menu);
		}*/
		//3、更新限制信息
		bizInfoDao.updateByPrimaryKeySelective(biz);
	}

	@Override
	public String updateStateById(SysBizInfo biz) {
		if (biz.getState()==0) {
			biz.setState((byte) 1);
		}
		else {
			biz.setState((byte) 0);
		}
		int row = bizInfoDao.updateByPrimaryKeySelective(biz);
		//System.out.println(row);
		if(row==1){
		return "success";
		}
		else{
			return "error";
		}
	}

	@Override
	public String chkExistByCode(String code) {
		int row = bizInfoDao.chkExistByCode(code);
		if(row==0){
			return "success";
		}
		else{
			return "error";
		}
	}

	@Override
	public void updateSysBizInfo(SysBizInfo biz) {
		// TODO Auto-generated method stub
		bizInfoDao.updateByPrimaryKeySelective(biz);
	}

	@Override
	public List<SysBizInfo> getAllBizs() {
		return bizInfoDao.getAllBizs();
	}

}
