package com.yimayhd.erpcenter.dal.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sys.dao.PlatformOrgMapper;
import com.yimayhd.erpcenter.dal.sys.dao.SysDataRightMapper;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
import com.yimayhd.erpcenter.dal.sys.po.SysDataRight;
import com.yimayhd.erpcenter.dal.sys.service.PlatformOrgDal;
import com.yimayhd.erpcenter.dal.sys.utils.DateUtils;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;


public class PlatformOrgDalImpl implements PlatformOrgDal {
    private static final Logger logger = LoggerFactory.getLogger(PlatformOrgDalImpl.class);

    @Autowired
    private PlatformOrgMapper orgMapper;
    @Autowired
    private SysDataRightMapper dataRightMapper;
    @Autowired
    TransactionTemplate transactionTemplateSys;

    @Override
    public List<PlatformOrgPo> findByPid(Integer pid, Integer sysId) {
        return orgMapper.findByPid(pid, sysId);
    }

    @Override
    public PlatformOrgPo findByOrgId(Integer orgId) {
        return orgMapper.findByOrgId(orgId);
    }

   // @Transactional(propagation = Propagation.REQUIRED)
    public int saveOrg(final PlatformOrgPo po) {
        Boolean transactionResult = transactionTemplateSys.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    int i = 0;
                    //不为空且不为0的才更新
                    if (po.getOrgId() != null && !po.getOrgId().equals(0)) {
                        po.setUpdateTime(DateUtils.getNow());

                        i = orgMapper.update(po);
                    } else {
                        po.setCreateTime(DateUtils.getNow());
                        po.setUpdateTime(DateUtils.getNow());
                        po.setDelStatus(true);

                        i = orgMapper.add(po);
                    }
                    //更新orgPath
                    if (po.getParentId() != 0) {
                        PlatformOrgPo parOrg = orgMapper.findByOrgId(po.getParentId());
                        String parOrgPath = parOrg.getOrgPath();
                        po.setOrgPath(parOrgPath + po.getOrgId() + "-");
                    } else {
                        po.setOrgPath(po.getOrgId() + "-");
                    }
                    i = orgMapper.update(po);
                    return true;
                } catch (Exception e) {
                    //logger.error("事务失败!", e);
                    status.setRollbackOnly();
                    return false;
                }
            }
        });
        return po.getOrgId();
    }

    @Override
    public int delOrg(Integer orgId) {

        return orgMapper.del(orgId);
    }

    /*@Override
    public List<PlatformOrgPo> getOrgList(Integer sysId, Integer employeeId,Integer isSuper) {
        if(isSuper==1){
            return orgMapper.getAllOrgList(sysId);
        }
        return orgMapper.getOrgList(sysId,employeeId);
    }*/
    @Override
    public List<PlatformOrgPo> getOrgList(Integer sysId, Integer orgId, Integer isSuper) {
        List<PlatformOrgPo> list = new ArrayList<PlatformOrgPo>();
        if (isSuper == 1) {
            return list;
        }
        PlatformOrgPo org = orgMapper.findByOrgId(orgId);
        list.add(org);
        return list;
    }
    /*@Override
	public List<PlatformOrgPo> getOrgTree(Integer sysId, Integer parentId) {
		
		return orgMapper.getOrgTree(sysId,parentId);
	}*/

    @Override
    public List<PlatformOrgPo> getOrgTree(Integer bizId, Integer parentId) {

        return orgMapper.getOrgTree(bizId, parentId);
    }

    @Override
    public List<PlatformOrgPo> getOrgList(String orgName, int exceptOrgId) {
        return orgMapper.getOrgListExceptId(orgName, exceptOrgId);
    }

    @Override
    public List<SysDataRight> getSysDataRightByEmployeeId(Integer employeeId) {
        return dataRightMapper.getSysDataRightByUserId(employeeId);
    }

    @Override
    public List<PlatformOrgPo> getOrgList(Integer sysId, Integer employeeId) {
        return orgMapper.getOrgList(sysId, employeeId);
    }

    @Override
    public String getLogoByOrgId(Integer bizId, Integer orgId) {
        String logo = orgMapper.getLogoByOrgId(bizId, orgId);
        if (StringUtils.isNotEmpty(logo)) {
            return logo;
        }
        PlatformOrgPo orgPo = orgMapper.findByOrgId(orgId);
        Integer pid = orgPo.getParentId();
        while (orgPo != null) {
            orgPo = orgMapper.findByOrgId(pid);
            if (orgPo != null) {
                logo = orgMapper.getLogoByOrgId(bizId, pid);
                if (StringUtils.isNotEmpty(logo)) {
                    return logo;
                }
                pid = orgPo.getParentId();
                if (pid.equals(0)) {
                    orgPo = null;
                }
            }
        }

        return logo;
    }

    @Override
    public String getCompanyCodeByOrgId(Integer bizId, Integer orgId) {
        String code = orgMapper.getCompanyCodeByOrgId(orgId, bizId);
        if (StringUtils.isNotEmpty(code)) {
            return code;
        }
        PlatformOrgPo orgPo = orgMapper.findByOrgId(orgId);
        Integer pid = orgPo.getParentId();
        while (orgPo != null) {
            orgPo = orgMapper.findByOrgId(pid);
            if (orgPo != null) {
                code = orgMapper.getCompanyCodeByOrgId(pid, bizId);
                if (StringUtils.isNotEmpty(code)) {
                    return code;
                }
                pid = orgPo.getParentId();
                if (pid.equals(0)) {
                    orgPo = null;
                }
            }
        }
        return code;
    }

    @Override
    public String getComponentOrgTreeJsonStr(Integer bizId) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        List<PlatformOrgPo> orgList = orgMapper.getOrgTree(bizId, null);
        if (orgList != null && orgList.size() > 0) {
            for (PlatformOrgPo org : orgList) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", org.getOrgId() + "");
                map.put("pId", org.getParentId() + "");
                map.put("name", org.getName());
                if (org.getParentId() == 0) {
                    map.put("open", "true");
                }
                list.add(map);
            }
        }
        return JSON.toJSONString(list);
    }

    @Override
    public PlatformOrgPo getOrgInfo(int bizId, Integer orgId) {
        return orgMapper.findByOrgId(orgId);
    }

    @Override
    public PlatformOrgPo getCompanyByEmployeeId(Integer bizId,
                                                Integer employeeId) {

        PlatformOrgPo platformOrgPo = orgMapper.getCompanyByEmployeeId(bizId, employeeId);
        if (null == platformOrgPo) {
            platformOrgPo = orgMapper.getCompanyByEmployeeId2(bizId, employeeId);
        }
        return platformOrgPo;
    }

    //根据当前登录用户的id获取其数据权限
    @Override
    public List<SysDataRight> getViewUserId(Integer userId) {
        return dataRightMapper.getSysDataRightByUserId(userId);
    }

    @Override
    public String getComponentOrgTreeJsonStr2(String orgIds, String userIds) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        StringBuilder sb = new StringBuilder();
        if (orgIds != null) {
            List<PlatformOrgPo> orgList1 = orgMapper.getOrgTree2(orgIds, null);

            for (PlatformOrgPo org : orgList1) {

                String[] orgIdArr = org.getOrgPath().split("-");
                for (String str : orgIdArr) {
                    sb.append(str + ",");
                }
            }
        }
        if (userIds != null) {
            List<PlatformOrgPo> orgList2 = orgMapper.getOrgTree3(userIds);
            for (PlatformOrgPo org : orgList2) {

                String[] orgIdArr = org.getOrgPath().split("-");
                for (String str : orgIdArr) {
                    if (sb.indexOf(str) < 0) {

                        sb.append(str + ",");
                    }
                }
            }
        }
        //System.out.println(sb);
        List<PlatformOrgPo> orgList = orgMapper.getOrgTree2(sb.substring(0, sb.length() - 1), null);
        if (orgList != null && orgList.size() > 0) {
            for (PlatformOrgPo org : orgList) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", org.getOrgId() + "");
                map.put("pId", org.getParentId() + "");
                map.put("name", org.getName());
                if (org.getParentId() == 0) {
                    map.put("open", "true");
                }
                list.add(map);
            }
        }
        return JSON.toJSONString(list);
    }

    @Override
    public List<PlatformOrgPo> getSubLevelOrgList(Integer bizId,
                                                  List<Integer> parentIdList) {
        return orgMapper.selectSubLevelOrgList(bizId, parentIdList);
    }

    @Override
    public List<PlatformOrgPo> getSecLevelOrgList(Integer bizId) {
        return orgMapper.selectSecLevelOrgList(bizId);
    }

    @Override
    public List<PlatformOrgPo> getOrgListByIdSet(Integer bizId,
                                                 Set<Integer> sets) {
        return orgMapper.getOrgListByIdSet(bizId, sets);
    }


}
