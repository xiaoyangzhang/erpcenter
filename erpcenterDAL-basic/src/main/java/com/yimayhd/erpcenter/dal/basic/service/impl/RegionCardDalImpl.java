package com.yimayhd.erpcenter.dal.basic.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.yimayhd.erpcenter.dal.basic.po.SysRegionCard;
import com.yimayhd.erpcenter.dal.basic.service.RegionCardDal;


public class RegionCardDalImpl implements RegionCardDal {

	//@Autowired  
	//TODO 改成mapper的方式
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<SysRegionCard> getFirstList() {
		List<SysRegionCard> list = jdbcTemplate.query("SELECT id,cname,ccode,pid FROM sys_region_card WHERE pid=''", new RowMapper<SysRegionCard>(){
			@Override
			public SysRegionCard mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				SysRegionCard card = new SysRegionCard();
				card.setId(rs.getString("id"));
				card.setCname(rs.getString("cname"));
				card.setCcode(rs.getString("ccode"));
				card.setPid(rs.getString("pid"));
				return card;
			}
			
		});
		return list;
	}

	@Override
	public List<SysRegionCard> getSecondList() {
		List<SysRegionCard> list = jdbcTemplate.query("SELECT id,cname,ccode,pid FROM sys_region_card WHERE pid IN (SELECT id FROM sys_region_card WHERE pid='')", new RowMapper<SysRegionCard>(){
			@Override
			public SysRegionCard mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				SysRegionCard card = new SysRegionCard();
				card.setId(rs.getString("id"));
				card.setCname(rs.getString("cname"));
				card.setCcode(rs.getString("ccode"));
				card.setPid(rs.getString("pid"));
				return card;
			}
			
		});
		return list;
	}

	@Override
	public List<SysRegionCard> getThirdList() {
		List<SysRegionCard> list = jdbcTemplate.query("SELECT id,cname,ccode,pid FROM sys_region_card WHERE pid IN ( SELECT id FROM sys_region_card WHERE pid IN (SELECT id FROM sys_region_card WHERE pid=''))", new RowMapper<SysRegionCard>(){
			@Override
			public SysRegionCard mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				SysRegionCard card = new SysRegionCard();
				card.setId(rs.getString("id"));
				card.setCname(rs.getString("cname"));
				card.setCcode(rs.getString("ccode"));
				card.setPid(rs.getString("pid"));
				return card;
			}
			
		});
		return list;
	}

}
