package com.ck.springboot.service;

import java.io.IOException;
import java.util.List;

import com.ck.springboot.pojo.AuditCenter;
import com.ck.springboot.pojo.District;
import com.ck.springboot.pojo.UnitName;

public interface AuditCenterService {
	
	public List<AuditCenter> selectAllAuditCenter();
	
	public List<District> selectDistrict() throws IOException ;//获取所有审方区域
	
	public int insertDistrict(District district)  throws IOException;//增加区域
//	
	public int deleteDistrict(Integer districtId);//删除审方区域
	
	public int updateDistrict(District district) throws IOException;//更新区域
	
	public List<UnitName> selectAllUnitName(Integer id);//获取所有审方医院
	
	public List<AuditCenter> selectAuditCenter(Integer id);//获取医院审方中心
	
	public int insertHospital(UnitName unitname) throws IOException;//增加医院
	
//	public boolean selectHospitalCounts(UnitName unitname);//查询医院数量**
	
	public int deleteHospital(Integer id);//删除审方医院
	
	public int updateHospital(UnitName unitname) throws IOException;//更新医院
	
	public int insertAuditCenter(AuditCenter center) throws IOException;//添加审方中心
	
	public int deleteCenterByPrimaryKey(Integer id);//删除审方中心
	
	public int updateAuditCenter(AuditCenter center) throws IOException;//更新审方中心
	
	public int insertCenter(AuditCenter center);
	
	public List<String> selectAllAuditArea();
	
	
}
