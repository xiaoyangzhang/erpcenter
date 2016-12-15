package com.yimayhd.erpcenter.dal.sys.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.TypeUtils;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.sys.dao.PlatformEmployeeMapper;
import com.yimayhd.erpcenter.dal.sys.dao.PlatformOrgMapper;
import com.yimayhd.erpcenter.dal.sys.dao.SysDataRightMapper;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
import com.yimayhd.erpcenter.dal.sys.po.SysDataRight;
import com.yimayhd.erpcenter.dal.sys.service.PlatformEmployeeDal;
import com.yimayhd.erpcenter.dal.sys.utils.MD5Util;

public class PlatformEmployeeDalImpl implements PlatformEmployeeDal {

    private static final Logger logger = LoggerFactory.getLogger(PlatformEmployeeDalImpl.class);

    @Autowired
    private PlatformEmployeeMapper platformEmployeeMapper;
    @Autowired
    private PlatformOrgMapper orgMapper;
    @Autowired
    private SysDataRightMapper dataRightMapper;
    @Autowired
    TransactionTemplate transactionTemplateSys;

    @Override
    public PlatformEmployeePo findByEmployeeId(Integer employeeId) {
        return platformEmployeeMapper.findByEmployeeId(employeeId);
    }

    @Override
    public PageBean getEmployeeList(
            PlatformEmployeePo platformEmployeePo, Integer page) {
        PlatformOrgPo platformOrgPo = new PlatformOrgPo();
        if (platformEmployeePo.getOrgId() != null) {
            PlatformOrgPo orgPo = orgMapper.findByOrgId(platformEmployeePo.getOrgId());
            platformOrgPo.setOrgPath(orgPo != null ? orgPo.getOrgPath() : platformEmployeePo.getOrgId() + "");
        }
        platformEmployeePo.setPlatformOrgPo(platformOrgPo);
        PageBean pageBean = new PageBean();
        pageBean.setPage(platformEmployeePo.getPage());
        pageBean.setParameter(platformEmployeePo);
        pageBean.setPageSize(platformEmployeePo.getPageSize());
        List<PlatformEmployeePo> result = platformEmployeeMapper.getEmployeeListPage(pageBean);
        pageBean.setResult(result);
        return pageBean;
    }

   // @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int saveEmployee(final PlatformEmployeePo platformEmployeePo, final Integer bizId) throws ClientException {
        int result = 0;
        Boolean transactionResult = transactionTemplateSys.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    if ((platformEmployeePo.getEmployeeId() == null || platformEmployeePo.getEmployeeId() <= 0) &&
                            platformEmployeeMapper.getEmpListByLoginName(bizId, platformEmployeePo.getLoginName()).size() > 0) {
                        throw new ClientException("用户名已存在");
                    }

                    Integer orgId = platformEmployeePo.getOrgId();
                    //当为编辑的时候，没有密码，当新增的时候才有密码，判断一下。
                    if (platformEmployeePo.getPassword() != null) {
                        platformEmployeePo.setPassword(MD5Util.MD5(platformEmployeePo.getPassword()));
                    }
                    int result = 0;
                    if (platformEmployeePo.getEmployeeId() != null) {
                        platformEmployeePo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                        result = platformEmployeeMapper.updateEmployee(platformEmployeePo);
                        //删除
                        //platformEmployeeMapper.byEmpDelOrg(platformEmployeePo.getEmployeeId(), sysId);
                        platformEmployeeMapper.byEmpDelRole(platformEmployeePo.getEmployeeId(), bizId);
                    } else {
                        if (platformEmployeePo.getIsSuper() == null) {
                            platformEmployeePo.setIsSuper(0);
                        }
                        platformEmployeePo.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        platformEmployeePo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                        platformEmployeePo.setDelStatus(1);
                        result = platformEmployeeMapper.addEmployee(platformEmployeePo);
                    }

                    //插入用户、组织机构关联表
//		List<String> orgList=null;
//		if (orgIds!=null) {
//			orgList = StrUtils.strSplitList(orgIds);
//			for (String str : orgList) {
                    //platformEmployeeMapper.addEmpOrg(platformEmployeePo.getEmployeeId(), orgId);
//			}
//		}

                    //先删除在插入用户、角色关联表
                    if (platformEmployeePo.getRole() != null) {
                        for (String roleId : platformEmployeePo.getRole()) {
                            platformEmployeeMapper.addEmpRole(platformEmployeePo.getEmployeeId(), Integer.valueOf(roleId));
                        }
                    }
                    return true;
                } catch (Exception e) {
                    //logger.error("事务失败!", e);
                    status.setRollbackOnly();
                    return false;
                }
            }
        });
        if (transactionResult) {
            result = 1;
        }
        return result;
    }

    @Override
    public int delByEmployeeId(Integer employeeId) {
        int result = 0;
        result = platformEmployeeMapper.delByEmployeeId(employeeId);
        return result;
    }

    @Override
    public int deleteByEmployeeId(Integer employeeId) {
        int result = 0;
        result = platformEmployeeMapper.deleteByEmployeeId(employeeId);
        return result;
    }

    @Override
    public PlatformEmployeePo getEmployeeByLoginName(String loginName) {
        return platformEmployeeMapper.getEmployeeByLoginName(loginName);
    }

    @Override
    public PlatformEmployeePo getEmployeeByBizIdAndLoginName(Integer bizId, String loginName) {
        return platformEmployeeMapper.getEmployeeByBizIdAndLoginName(bizId, loginName);
    }

    public int updateEmployee(PlatformEmployeePo platformEmployeePo) {
        if (StringUtils.isNotEmpty(platformEmployeePo.getPassword())) {
            platformEmployeePo.setPassword(MD5Util.MD5(platformEmployeePo.getPassword()));
        }
        return platformEmployeeMapper.updateEmployee(platformEmployeePo);


    }

    /*@Override
    public int validationName(String name, Integer id) {
        return platformEmployeeMapper.validationName(name,id);

    }
*/
    @Override
    public int getEmployeeList(String loginName,
                               Integer employeeId, Integer bizId) {

        return platformEmployeeMapper.getEmployeeList(loginName, employeeId, bizId);
    }

    /**
     * 根据商家id获取组织机构与用户的树节点集合
     */
    @Override
    public List<Map<String, String>> getOrgUserTree(Integer bizId, Integer parentOrgId, String type) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        List<PlatformOrgPo> orgList = orgMapper.getOrgTree(bizId, parentOrgId);
        if (orgList != null && orgList.size() > 0) {
            Map<String, String> map = null;
            for (PlatformOrgPo org : orgList) {
                map = new HashMap<String, String>();
                //默认打开第一级节点
                if (org.getParentId() == 0) {
                    map.put("open", "true");
                }
                //单选：设置单位节点不可选中
                if (type.equals("single")) {
                    map.put("nocheck", "true");
                }
                map.put("id", "o_" + org.getOrgId());
                map.put("pId", "o_" + org.getParentId());
                map.put("name", org.getName());
                map.put("oid", org.getOrgId() + "");
                map.put("type", "org");
                list.add(map);
                List<PlatformEmployeePo> userList = platformEmployeeMapper.getListByOrgId(org.getOrgId());
                if (userList != null && userList.size() > 0) {
                    for (PlatformEmployeePo user : userList) {
                        map = new HashMap<String, String>();
                        map.put("id", "u_" + user.getEmployeeId());
                        map.put("pId", "o_" + user.getOrgId());
                        map.put("name", user.getName());
                        map.put("oid", user.getEmployeeId() + "");
                        map.put("pos", user.getPosition());
                        map.put("mobile", user.getMobile());
                        map.put("phone", user.getPhone());
                        map.put("fax", user.getFax());
                        map.put("type", "user");
                        list.add(map);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public List<Map<String, String>> getOrgUserTreeFuzzy(Integer bizId, String name, String type) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        //员工列表
        List<PlatformEmployeePo> empList = platformEmployeeMapper.getEmpListByEmpName(bizId, name);
        if (empList == null || empList.size() == 0) {
            return list;
        }
        //员工所在单位列表
        Set<Integer> orgIdList = new HashSet<Integer>();
        for (PlatformEmployeePo emp : empList) {
            orgIdList.add(emp.getOrgId());
        }
        //根据id列表查询单位信息
        List<PlatformOrgPo> orgList = orgMapper.getOrgListByOrgIdListAndBiz(bizId, orgIdList);
        if (orgList != null && orgList.size() > 0) {
            //单位及上级单位id列表
            orgIdList = new HashSet<Integer>();
            for (PlatformOrgPo org : orgList) {
                String orgPath = org.getOrgPath();
                if (StringUtils.isNotEmpty(orgPath)) {
                    String[] orgIdArr = orgPath.split("-");
                    if (orgIdArr.length > 0) {
                        for (String orgId : orgIdArr) {
                            orgIdList.add(Integer.valueOf(orgId));
                        }
                    }
                }
            }
            //单位及上级单位列表信息
            orgList = orgMapper.getOrgListByOrgIdListAndBiz(bizId, orgIdList);
            if (orgList != null && orgList.size() > 0) {
                Map<String, String> map = null;
                for (PlatformOrgPo org : orgList) {
                    map = new HashMap<String, String>();
                    //默认打开第一级节点
                    //if(org.getParentId()==0){
                    map.put("open", "true");
                    //}
                    //单选：设置单位节点不可选中
                    if (type.equals("single")) {
                        map.put("nocheck", "true");
                    }
                    map.put("id", "o_" + org.getOrgId());
                    map.put("pId", "o_" + org.getParentId());
                    map.put("name", org.getName());
                    map.put("oid", org.getOrgId() + "");
                    map.put("type", "org");
                    list.add(map);
                }
                for (PlatformEmployeePo user : empList) {
                    map = new HashMap<String, String>();
                    map.put("id", "u_" + user.getEmployeeId());
                    map.put("pId", "o_" + user.getOrgId());
                    map.put("name", user.getName());
                    map.put("oid", user.getEmployeeId() + "");
                    map.put("pos", user.getPosition());
                    map.put("mobile", user.getMobile());
                    map.put("phone", user.getPhone());
                    map.put("fax", user.getFax());
                    map.put("type", "user");
                    list.add(map);
                }
            }

        }
        return list;
    }


    /**
     * 根据商家id获取组织机构与用户的树节点集合
     */
    @Override
    public List<Map<String, String>> getOrgUserDateRightTree(Integer bizId, Integer parentOrgId, String type, Integer employeeId) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        List<PlatformOrgPo> orgList = orgMapper.getOrgTree(bizId, parentOrgId);
        if (orgList != null && orgList.size() > 0) {
            Map<String, String> map = null;
            List<SysDataRight> dataRightList = dataRightMapper.getSysDataRightByUserId(employeeId);
            Map<Integer, Boolean> dataRightMap = convertDataRightToMap(dataRightList);
            for (PlatformOrgPo org : orgList) {
                map = new HashMap<String, String>();
                //默认打开第一级节点
                map.put("open", "true");

                //如果有下级单位的话，则不可选择 
				/*  ouZongYing 注释
				if(hasSubOrg(org.getOrgId().intValue(),orgList)){
					map.put("nocheck", "true");
				}else{//如果没下级单位的话
					if(dataRightMap.containsKey(-org.getOrgId().intValue())){
						map.put("checked", "true");
					}					
				}
				*/
				// 以下由 ouZongYing修改， 改为所有组织机构节点都可以选
				if(dataRightMap.containsKey(-org.getOrgId().intValue())){
					map.put("checked", "true");
				}
                
                map.put("id", "o_" + org.getOrgId());
                map.put("pId", "o_" + org.getParentId());
                map.put("name", org.getName());
                map.put("oid", org.getOrgId() + "");
                map.put("type", "org");
                list.add(map);
                List<PlatformEmployeePo> userList = platformEmployeeMapper.getListByOrgId(org.getOrgId());
                if (userList != null && userList.size() > 0) {
                    for (PlatformEmployeePo user : userList) {
                        map = new HashMap<String, String>();
                        map.put("id", "u_" + user.getEmployeeId());
                        map.put("pId", "o_" + user.getOrgId());
                        map.put("name", user.getName());
                        map.put("employeeId", user.getEmployeeId() + "");
                        map.put("pos", user.getPosition());
                        map.put("mobile", user.getMobile());
                        map.put("phone", user.getPhone());
                        map.put("fax", user.getFax());
                        map.put("type", "user");

                        if (dataRightMap.containsKey(user.getEmployeeId().intValue())) {
                            map.put("checked", "true");
                        }

                        list.add(map);
                    }

                }
            }
        }
        return list;
    }

    /**
     * 判断当前部门是否有下级部门
     *
     * @param orgId
     * @param orgList
     * @return
     */
    private boolean hasSubOrg(int orgId, List<PlatformOrgPo> orgList) {
        for (PlatformOrgPo org : orgList) {
            if (orgId == org.getParentId().intValue()) {
                return true;
            }
        }
        return false;
    }

    private Map<Integer, Boolean> convertDataRightToMap(List<SysDataRight> dataRightList) {
        Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        for (SysDataRight right : dataRightList) {
            map.put(right.getViewUserId(), true);
        }
        return map;
    }

    @Override
    public List<Map<String, String>> getOrgUserDateRightTreeByByViewUsrId(
            Integer curBizId, Integer parentOrgId, String type, Integer employeeId) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        List<PlatformOrgPo> orgList = orgMapper.getOrgTree(curBizId, parentOrgId);
        if (orgList != null && orgList.size() > 0) {
            Map<String, String> map = null;
            for (PlatformOrgPo org : orgList) {
                map = new HashMap<String, String>();
                //默认打开第一级节点
                map.put("open", "true");

                //单选：设置单位节点不可选中
                if (type.equals("single")) {
                    map.put("nocheck", "true");
                }


                map.put("id", "o_" + org.getOrgId());
                map.put("pId", "o_" + org.getParentId());
                map.put("name", org.getName());
                map.put("oid", org.getOrgId() + "");
                map.put("type", "org");
                list.add(map);
                List<PlatformEmployeePo> userList = platformEmployeeMapper.getListByOrgId(org.getOrgId());
                List<SysDataRight> dataRightList = dataRightMapper.getSysDataRightByViewUserId(employeeId);
                logger.info(employeeId + "  " + dataRightList.size());
                if (userList != null && userList.size() > 0) {
                    for (PlatformEmployeePo user : userList) {
                        map = new HashMap<String, String>();
                        map.put("id", "u_" + user.getEmployeeId());
                        map.put("pId", "o_" + user.getOrgId());
                        map.put("name", user.getName());
                        map.put("employeeId", user.getEmployeeId() + "");
                        map.put("pos", user.getPosition());
                        map.put("mobile", user.getMobile());
                        map.put("phone", user.getPhone());
                        map.put("fax", user.getFax());
                        map.put("type", "user");
                        if (dataRightList != null && dataRightList.size() > 0) {
                            for (SysDataRight dataRight : dataRightList) {
                                if (user.getEmployeeId().equals(dataRight.getViewUserId())) {

                                    map.put("checked", "true");
                                }
                            }
                        }
                        list.add(map);
                    }

                }
            }
        }
        return list;
    }

    @Override
    public Set<Integer> getUserIdListByOrgId(Integer bizId, Integer orgId) {
        List<PlatformEmployeePo> list = platformEmployeeMapper.getUserIdListByOrgId(bizId, orgId);
        Set<Integer> set = new HashSet<Integer>();
        if (list != null && list.size() > 0) {
            for (PlatformEmployeePo user : list) {
                set.add(user.getEmployeeId());
            }
        }
        return set;
    }

    @Override
    public Set<Integer> getUserIdListByOrgIdList(Integer bizId, Set<Integer> orgIdSet) {
        List<PlatformEmployeePo> list = platformEmployeeMapper.getUserIdListByOrgIdList(bizId, orgIdSet);
        Set<Integer> set = new HashSet<Integer>();
        if (list != null && list.size() > 0) {
            for (PlatformEmployeePo user : list) {
                set.add(user.getEmployeeId());
            }
        }
        return set;
    }

    @Override
    public void delSysDataRight(SysDataRight dataRight) {
        dataRightMapper.deleteDataRight(dataRight);
    }

    @Override
    public int insertSysDataRight(List<PlatformEmployeePo> emplList, Integer employeeId) {
        SysDataRight sysDataRight = null;
        if (emplList != null && emplList.size() > 0) {
            List<SysDataRight> list = new ArrayList<SysDataRight>();
            for (PlatformEmployeePo po : emplList) {
                sysDataRight = new SysDataRight();
                sysDataRight.setUserId(employeeId);
                sysDataRight.setViewUserId(po.getEmployeeId());
                sysDataRight.setCreateTime(new Date().getTime());
                //dataRightMapper.insertSelective(sysDataRight);
                list.add(sysDataRight);
            }
            dataRightMapper.insertBatch(list);
        }
        return 0;
    }

    @Override
    public Set<Integer> getDataRightListByUserId(Integer bizId, Integer userId) {
        List<SysDataRight> list = dataRightMapper.getSysDataRightByUserId(userId);
        Set<Integer> set = new HashSet<Integer>();
        set.add(userId);
        if (list != null && list.size() > 0) {
            Set<Integer> orgSet = new HashSet<Integer>();
            for (SysDataRight right : list) {
                if (right.getViewUserId().intValue() > 0) {
                    set.add(right.getViewUserId());
                } else {
                    orgSet.add(-right.getViewUserId().intValue());
                }
            }
            if (orgSet.size() > 0) {
                List<PlatformEmployeePo> empList = platformEmployeeMapper.getUserIdListByOrgIdList(bizId, orgSet);
                if (empList != null && empList.size() > 0) {
                    for (PlatformEmployeePo emp : empList) {
                        set.add(emp.getEmployeeId());
                    }
                }
            }
        }
        return set;
    }

    @Override
    public String findByOrgPath(String orgPath) {
        StringBuilder sb = new StringBuilder();
        List<PlatformEmployeePo> pepList = platformEmployeeMapper.findByOrgPath(orgPath);
        for (int i = 0; i < pepList.size(); i++) {
            if ((i + 1) != pepList.size()) {
                sb.append(pepList.get(i).getEmployeeId() + ",");
            } else {
                sb.append(pepList.get(i).getEmployeeId());
            }
        }
        return sb.toString();
    }

    //@Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveDataRight(List<Map> list, Integer employeeId) {
        if (list == null) {
            return;
        }
        List<SysDataRight> dataRightList = new ArrayList<SysDataRight>();
        SysDataRight dataRight = new SysDataRight();
        SysDataRight model = null;
        dataRight.setUserId(employeeId);
        for (Map map : list) {
            model = new SysDataRight();
            model.setUserId(employeeId);
            if (map.get("type").toString().equals("user")) {
                model.setViewUserId(TypeUtils.castToInt(map.get("id")));
            } else {//部门id设置为负值
                model.setViewUserId(-TypeUtils.castToInt(map.get("id")));
            }
            dataRightList.add(model);
        }

        final SysDataRight dataRightt = dataRight;
        final List<SysDataRight> dataRightListt = dataRightList;
        Boolean transactionResult = transactionTemplateSys.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    dataRightMapper.deleteDataRight(dataRightt);
                    dataRightMapper.insertBatch(dataRightListt);
                    return true;
                } catch (Exception e) {
                    logger.error("事务失败!", e);
                    status.setRollbackOnly();
                    return false;
                }
            }
        });
    }

    @Override
    public List<PlatformEmployeePo> getEmployeeListByOrgId(Integer orgId) {
        return platformEmployeeMapper.getListByOrgId(orgId);
    }

    @Override
    public String getComponentOrgUserTreeJsonStr(Integer bizId) {
        List<PlatformOrgPo> orgList = orgMapper.getOrgTree(bizId, null);
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        for (PlatformOrgPo org : orgList) {
            map = new HashMap<String, String>();
            //if(org.getParentId()==0){
            map.put("open", "true");
            //}
            //map.put("nocheck", "true");
            map.put("id", "o_" + org.getOrgId());
            map.put("pId", "o_" + org.getParentId());
            map.put("name", org.getName());
            map.put("oid", org.getOrgId() + "");
            map.put("type", "org");
            list.add(map);

            List<PlatformEmployeePo> userList = platformEmployeeMapper.getListByOrgId(org.getOrgId());
            if (userList != null && userList.size() > 0) {
                for (PlatformEmployeePo user : userList) {
                    map = new HashMap<String, String>();
                    map.put("id", "u_" + user.getEmployeeId());
                    map.put("pId", "o_" + user.getOrgId());
                    map.put("name", user.getName());
                    map.put("oid", user.getEmployeeId() + "");
                    map.put("pos", user.getPosition());
                    map.put("mobile", user.getMobile());
                    map.put("phone", user.getPhone());
                    map.put("fax", user.getFax());
                    map.put("type", "user");
                    list.add(map);
                }
            }
        }
        return JSON.toJSONString(list);
    }

    @Override
    public List<PlatformEmployeePo> getEmployeeListByName(Integer bizId, String name) {
        return platformEmployeeMapper.getEmpListByEmpName(bizId, name);
    }

    @Override
    public String getComponentOrgUserTreeJsonStr2(String userIds, String orgIds) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();//收集放在map中的组织机构id，防重复
        Map<String, String> map = null;
        if (orgIds != null) {
            List<PlatformOrgPo> orgList1 = orgMapper.getOrgTree2(orgIds, null);
            for (PlatformOrgPo org : orgList1) {

                String[] orgIdArr = org.getOrgPath().split("-");
                for (String str : orgIdArr) {
                    sb.append(str + ",");
                }
            }
            List<PlatformOrgPo> orgList = orgMapper.getOrgTree2(
                    sb.substring(0, sb.length() - 1), null);
            for (PlatformOrgPo org : orgList) {
                map = new HashMap<String, String>();
                //if(org.getParentId()==0){
                map.put("open", "true");
                //}
                //map.put("nocheck", "true");
                map.put("id", "o_" + org.getOrgId());
                map.put("pId", "o_" + org.getParentId());
                map.put("name", org.getName());
                map.put("oid", org.getOrgId() + "");
                map.put("type", "org");
                //System.out.println(org.getName());
                sb2.append(org.getOrgId() + ",");
                list.add(map);

                List<PlatformEmployeePo> userList = platformEmployeeMapper
                        .getListByOrgId2(org.getOrgId(), null);
                if (userList != null && userList.size() > 0) {
                    for (PlatformEmployeePo user : userList) {
                        map = new HashMap<String, String>();
                        map.put("id", "u_" + user.getEmployeeId());
                        map.put("pId", "o_" + user.getOrgId());
                        map.put("name", user.getName());
                        map.put("oid", user.getEmployeeId() + "");
                        map.put("pos", user.getPosition());
                        map.put("mobile", user.getMobile());
                        map.put("phone", user.getPhone());
                        map.put("fax", user.getFax());
                        map.put("type", "user");
                        list.add(map);
                    }
                }
            }
        }
        if (userIds != null) {
            //StringBuilder sb=new StringBuilder();

            List<PlatformOrgPo> orgList2 = orgMapper.getOrgTree3(userIds);
            for (PlatformOrgPo org : orgList2) {

                String[] orgIdArr = org.getOrgPath().split("-");
                for (String str : orgIdArr) {
                    if (sb.indexOf(str) < 0) {

                        sb.append(str + ",");
                    }
                }
            }
            List<PlatformOrgPo> orgList3 = orgMapper.getOrgTree2(sb.substring(0, sb.length() - 1), null);
            for (PlatformOrgPo org : orgList3) {
                map = new HashMap<String, String>();
                if (sb2.indexOf(org.getOrgId() + "") < 0) {
                    //if(org.getParentId()==0){
                    map.put("open", "true");
                    //}
                    //map.put("nocheck", "true");
                    map.put("id", "o_" + org.getOrgId());
                    map.put("pId", "o_" + org.getParentId());
                    map.put("name", org.getName());
                    map.put("oid", org.getOrgId() + "");
                    map.put("type", "org");
                    //System.out.println(org.getName());
                    list.add(map);
                }
                List<PlatformEmployeePo> userList = platformEmployeeMapper
                        .getListByOrgId2(org.getOrgId(), userIds);
                if (userList != null && userList.size() > 0) {
                    for (PlatformEmployeePo user : userList) {
                        map = new HashMap<String, String>();
                        map.put("id", "u_" + user.getEmployeeId());
                        map.put("pId", "o_" + user.getOrgId());
                        map.put("name", user.getName());
                        map.put("oid", user.getEmployeeId() + "");
                        map.put("pos", user.getPosition());
                        map.put("mobile", user.getMobile());
                        map.put("phone", user.getPhone());
                        map.put("fax", user.getFax());
                        map.put("type", "user");
                        list.add(map);
                    }
                }
            }
        }
        return JSON.toJSONString(list);
    }

    @Override
    public List<PlatformEmployeePo> getEmpList(Integer bizId, Set<Integer> set) {
        List<PlatformEmployeePo> empList = platformEmployeeMapper.getEmpList(bizId, set);

        return empList;
    }
    
	/**
	 * 查询部门员工信息
	 */
	@Override
	public List<PlatformEmployeePo> getOrgIdListByEmployee(Integer bizId, Set<Integer> set) {
		List<PlatformEmployeePo> empBeanList = platformEmployeeMapper.getOrgIdListByEmployee(bizId, set);
		return empBeanList;
	}

	@Override
	public List<PlatformEmployeePo> getOrgEmployeeListPage(PageBean pageBean) {
		List<PlatformEmployeePo> empBeanList = platformEmployeeMapper.getOrgEmployeeListPage(pageBean);
		return empBeanList;
	}
}
